package com.agilearchitect.ui.party;

import com.agilearchitect.domain.party.Organisation;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrganisationController
{
   @FXML
   private TextField nameField;
   @FXML
   private DatePicker inceptionDateField;
   @FXML
   private DatePicker cessationDateField;

   private boolean okClicked = false;
   private Organisation organisation;
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
    * Sets the organisation to be used in this dialog.
    *
    * @param organisation
    */
   public void setOrganisation(Organisation organisation)
   {
      this.organisation = organisation;
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
         organisation.setOrganisationName(nameField.getText());
         organisation.setDateOfInception(inceptionDateField.getValue());
         organisation.setDateOfCessation(cessationDateField.getValue());

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
