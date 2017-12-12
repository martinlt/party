package com.agilearchitect.ui.party;

import com.agilearchitect.domain.party.Party;
import com.agilearchitect.domain.party.PartyRelationship;
import com.agilearchitect.domain.party.RoleType;
import com.agilearchitect.domain.party.RoleTypeRelationship;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

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
      // type. this allows us to dynamically set the from/to lists
      // appropriate to the party types in the relationship.
      relationshipTypeField.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> handleSelectRelationship());

      // bind the party from / to disable property to ensure they can only
      // be selected when a relationship type has been chosen
      partyFromField.disableProperty().bind(relationshipTypeField.valueProperty().isNull());
      partyToField.disableProperty().bind(relationshipTypeField.valueProperty().isNull());
   }

   /**
    * Set the from/to party combobox lists to the appropriate party type for the
    * relationship that has been selected.
    */
   @FXML
   private void handleSelectRelationship()
   {
      RoleTypeRelationship r = relationshipTypeField.getValue();
      RoleType from = r.getFrom();
      RoleType to = r.getTo();

      if (from.getPartyType().getDescription().equals("Organisation") == true) {
         partyFromField.setItems(FXCollections.observableArrayList(state.getOrganisations()));
      }
      if (from.getPartyType().getDescription().equals("Person") == true) {
         partyFromField.setItems(FXCollections.observableArrayList(state.getPeople()));
      }
      if (to.getPartyType().getDescription().equals("Organisation") == true) {
         partyToField.setItems(FXCollections.observableArrayList(state.getOrganisations()));
      }
      if (to.getPartyType().getDescription().equals("Person") == true) {
         partyToField.setItems(FXCollections.observableArrayList(state.getPeople()));
      }
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
    * Store a reference to the current application state and set
    * the relationship type combobox with it's values.
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
