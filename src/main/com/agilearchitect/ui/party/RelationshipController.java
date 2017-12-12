package com.agilearchitect.ui.party;

import com.agilearchitect.domain.party.Party;
import com.agilearchitect.domain.party.PartyRelationship;
import com.agilearchitect.domain.party.Person;
import com.agilearchitect.domain.party.RoleType;
import com.agilearchitect.domain.party.RoleTypeRelationship;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RelationshipController
{
   @FXML
   private ComboBox<RoleTypeRelationship> relationshipTypeField;
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
      RoleTypeRelationship r = relationshipTypeField.getValue();
      RoleType from = r.getFrom();

      if (from.getPartyType().getDescription().equals("Organisation") == true) {
         partyFromField.setItems(FXCollections.observableArrayList(state.getOrganisations()));
      }
      if (from.getPartyType().getDescription().equals("Person") == true) {
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
      RoleTypeRelationship r = relationshipTypeField.getValue();
      RoleType to = r.getTo();
      List<Party> filteredList;

      if (to.getPartyType().getDescription().equals("Organisation") == true) {
         filteredList = state.getOrganisations().stream()
               .filter(p -> !p.hasToRelationship(partyFromField.getValue()))
               .collect(Collectors.toList());
      } else { // Person
         filteredList = state.getPeople().stream()
               .filter(p -> !p.hasToRelationship(partyFromField.getValue()))
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
         relationship.setRelationshipType(relationshipTypeField.getValue());
         relationship.setFrom(partyFromField.getValue());
         relationship.setTo(partyToField.getValue());
         relationship.setEffectiveFrom(effectiveFromField.getValue());
         relationship.setEffectiveTo(effectiveToField.getValue());
         okClicked = true;
         dialogStage.close();
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

      relationshipTypeField.setItems(
            FXCollections.observableArrayList(state.getConfig().getRoleTypeRelationships()));
   }
}
