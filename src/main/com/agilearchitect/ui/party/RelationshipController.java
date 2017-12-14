package com.agilearchitect.ui.party;

import com.agilearchitect.domain.party.InvalidRelationshipTarget;
import com.agilearchitect.domain.party.Party;
import com.agilearchitect.domain.party.PartyKind;
import com.agilearchitect.domain.party.PartyRelationship;
import com.agilearchitect.domain.party.RoleKind;
import com.agilearchitect.domain.party.RoleRelationshipKind;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RelationshipController
{
   @FXML
   private ComboBox<RoleRelationshipKind> relationshipTypeField;
   @FXML
   private ComboBox<Party> partyFromField;
   @FXML
   private ComboBox<Party> partyToField;
   @FXML
   private DatePicker effectiveFromField;
   @FXML
   private DatePicker effectiveToField;

   private boolean okClicked = false;
   private PartyRelationship relationship;
   private Stage dialogStage;
   private ApplicationState state;

   /**
    * Initializes the controller class. This method is automatically called
    * after the fxml file has been loaded.
    */
   @FXML
   private void initialize()
   {
      // set a listener for when the user changes the relationship
      // type. this allows us to dynamically set the 'from party' list
      // appropriate to the party types in the relationship.
      relationshipTypeField.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> handleSelectRelationship());

      // set a listener for when the user changes the 'from party'
      // field. this allows us to dynamically set the 'to party' list
      // filtering out relationships that already exist.
      partyFromField.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> handleSelectFromParty());

      // bind the 'from' party disable property to ensure it can only
      // be selected when a relationship type has been chosen
      partyFromField.disableProperty().bind(relationshipTypeField.valueProperty().isNull());

      // bind the 'to' party disable property to ensure it can only
      // be selected when a relationship type has been chosen
      partyToField.disableProperty().bind(partyFromField.valueProperty().isNull());
   }

   /**
    * Set the 'from party' combobox list to the appropriate party type for the
    * relationship that has been selected.
    */
   @FXML
   private void handleSelectRelationship()
   {
      RoleRelationshipKind r = relationshipTypeField.getValue();
      RoleKind from = r.getFrom();

      if (from.getPartyKind() == PartyKind.ORGANISATION) {
         partyFromField.setItems(FXCollections.observableArrayList(state.getOrganisations()));
      }
      if (from.getPartyKind() == PartyKind.PERSON) {
         partyFromField.setItems(FXCollections.observableArrayList(state.getPeople()));
      }
   }

   /**
    * Set the 'to party' combobox list filtering out relationships that already
    * exist.
    */
   @FXML
   private void handleSelectFromParty()
   {
      RoleRelationshipKind r = relationshipTypeField.getValue();
      RoleKind to = r.getTo();
      List<Party> filteredList;

      if (to.getPartyKind() == PartyKind.ORGANISATION) {
         filteredList = state.getOrganisations().stream()
               .filter(p -> (p != partyFromField.getValue())
                     && !p.hasToRelationship(partyFromField.getValue()))
               .collect(Collectors.toList());
      } else { // Person
         filteredList = state.getPeople().stream()
               .filter(p -> (p != partyFromField.getValue())
                     && !p.hasToRelationship(partyFromField.getValue()))
               .collect(Collectors.toList());

      }
      partyToField.setItems(FXCollections.observableArrayList(filteredList));
   }

   /**
    * Sets the stage of this dialog.
    *
    * @param dialogStage
    */
   public void setDialogStage(Stage dialogStage)
   {
      this.dialogStage = dialogStage;
   }

   /**
    * Sets the relationship of this dialog.
    *
    * @param dialogStage
    */
   public void setRelationship(PartyRelationship relationship)
   {
      this.relationship = relationship;
   }

   /**
    * Returns true if the user clicked OK, false otherwise.
    *
    * @return
    */
   public boolean isOkClicked()
   {
      return okClicked;
   }

   /**
    * Called when the user clicks ok.
    */
   @FXML
   private void handleOk()
   {
      if (isInputValid()) {
         try {
            relationship.setRelationshipType(relationshipTypeField.getValue());
            relationship.setFrom(partyFromField.getValue());
            relationship.setTo(partyToField.getValue());
            relationship.setEffectiveFrom(effectiveFromField.getValue());
            relationship.setEffectiveTo(effectiveToField.getValue());
            okClicked = true;
            dialogStage.close();
         } catch (InvalidRelationshipTarget e) {
            Logger.getLogger(RelationshipController.class.getName()).log(Level.SEVERE, null, e);
         }
      }
   }

   /**
    * Called when the user clicks cancel.
    */
   @FXML
   private void handleCancel()
   {
      dialogStage.close();
   }

   /**
    * Validates the user input in the text fields.
    *
    * @return true if the input is valid
    */
   private boolean isInputValid()
   {
      if (relationshipTypeField.getValue() == null)
         return false;

      if (partyFromField.getValue() == null)
         return false;

      if (partyToField.getValue() == null)
         return false;

      if (effectiveFromField.getValue() == null)
         return false;

      return true;
   }

   /**
    * Store a reference to the current application state and set the
    * relationship type combobox with it's values.
    *
    * @param state
    */
   public void setState(ApplicationState state)
   {
      this.state = state;

      relationshipTypeField.setItems(FXCollections.observableArrayList(
            new ArrayList<RoleRelationshipKind>(EnumSet.allOf(RoleRelationshipKind.class))));
   }
}
