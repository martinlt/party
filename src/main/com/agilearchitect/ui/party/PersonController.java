package com.agilearchitect.ui.party;

import com.agilearchitect.domain.party.Person;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonController
{
   @FXML
   private TextField givenNameField;
   @FXML
   private TextField familyNameField;
   @FXML
   private DatePicker birthDateField;
   @FXML
   private DatePicker deathDateField;

   private boolean okClicked = false;
   private Person person;
   private Stage dialogStage;

   /**
    * Initializes the controller class. This method is automatically called
    * after the fxml file has been loaded.
    */
   @FXML
   private void initialize()
   {
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
    * Sets the person to be used in this dialog.
    *
    * @param person
    */
   public void setPerson(Person person)
   {
      this.person = person;
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
         person.setGivenName(givenNameField.getText());
         person.setFamilyName(familyNameField.getText());
         person.setDateOfBirth(birthDateField.getValue());
         person.setDateOfDeath(deathDateField.getValue());

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
      return true;
   }
}
