package com.agilearchitect.ui.party;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.agilearchitect.domain.party.Organisation;
import com.agilearchitect.domain.party.PartyRelationship;
import com.agilearchitect.domain.party.Person;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class launches a sample JavaFX GUI application to exercise the party class model.
 *
 * The JavaFX runtime does the following, in order, whenever an application is launched:
 *
 * 1. Constructs an instance of the specified Application class
 * 2. Calls the init() method
 * 3. Calls the start(javafx.stage.Stage) method
 * 4. Waits for the application to finish, which happens when either of the following occur:
 *    - the application calls Platform.exit()
 *    - the last window has been closed and the implicit Exit attribute on Platform is true
 * 5. Calls the stop() method (which has been overridden here to save the current state)
 *
 * @author Martin Thomas
 *
 */
public class SampleApplication extends Application
{
   private final ApplicationState state;

   private Stage primaryStage;
   private AnchorPane applicationOverview;

   /*
    * In the constructor, load the previously saved state of the application.
    */
   public SampleApplication()
   {
      state = ApplicationState.load();
   }

   @Override
   public void start(Stage stage)
   {
      this.primaryStage = stage;
      stage.setTitle("Sample Application : Party Management");

      this.primaryStage.getIcons().add(new Image("file:resources/images/party.png"));

      try {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(SampleApplication.class.getResource("ApplicationOverview.fxml"));
         applicationOverview = (AnchorPane) loader.load();

         // Get the controller and pass it a reference to this
         ApplicationController applController = loader.getController();
         applController.init(this, state);

         // Show the scene containing the application overview.
         Scene scene = new Scene(applicationOverview);
         primaryStage.setScene(scene);
         primaryStage.show();
     } catch (IOException e) {
        Logger.getLogger(SampleApplication.class.getName()).log(Level.SEVERE, null, e);
     }
   }

   public static void main(String[] args)
   {
      launch(args);
   }


   /*
    * The stop() method is called when the JavaFX application finishes. By overriding
    * this method, we automatically save the application's state.
    *
    * @see javafx.application.Application#stop()
    */
   @Override
   public void stop()
   {
      state.save();
   }

   /**
    * Returns the main stage.
    * @return
    */
   public Stage getPrimaryStage()
   {
      return primaryStage;
   }

   public boolean showOrganisationDialog(Organisation organisation)
   {
      try {
         // Load the fxml file and create a new stage for the popup dialog.
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(SampleApplication.class.getResource("OrganisationDialog.fxml"));
         AnchorPane page = (AnchorPane) loader.load();

         // Create the dialog Stage.
         Stage dialogStage = new Stage();
         dialogStage.setTitle("Add Organisation");
         dialogStage.initModality(Modality.WINDOW_MODAL);
         dialogStage.initOwner(primaryStage);
         Scene scene = new Scene(page);
         dialogStage.setScene(scene);

         // Set the party into the controller.
         OrganisationController controller = loader.getController();
         controller.setDialogStage(dialogStage);
         controller.setOrganisation(organisation);

         // Show the dialog and wait until the user closes it
         dialogStage.showAndWait();

         return controller.isOkClicked();
      } catch (IOException e) {
         Logger.getLogger(SampleApplication.class.getName()).log(Level.SEVERE, null, e);
         return false;
      }
   }

   public boolean showPersonDialog(Person person)
   {
      try {
         // Load the fxml file and create a new stage for the popup dialog.
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(SampleApplication.class.getResource("PersonDialog.fxml"));
         AnchorPane page = (AnchorPane) loader.load();

         // Create the dialog Stage.
         Stage dialogStage = new Stage();
         dialogStage.setTitle("Add Person");
         dialogStage.initModality(Modality.WINDOW_MODAL);
         dialogStage.initOwner(primaryStage);
         Scene scene = new Scene(page);
         dialogStage.setScene(scene);

         // Set the party into the controller.
         PersonController controller = loader.getController();
         controller.setDialogStage(dialogStage);
         controller.setPerson(person);

         // Show the dialog and wait until the user closes it
         dialogStage.showAndWait();

         return controller.isOkClicked();
      } catch (IOException e) {
         Logger.getLogger(SampleApplication.class.getName()).log(Level.SEVERE, null, e);
         return false;
      }
   }

   public boolean showRelationshipDialog(PartyRelationship relationship)
   {
      try {
         // Load the fxml file and create a new stage for the popup dialog.
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(SampleApplication.class.getResource("RelationshipDialog.fxml"));
         AnchorPane page = (AnchorPane) loader.load();

         // Create the dialog Stage.
         Stage dialogStage = new Stage();
         dialogStage.setTitle("Add Relationship");
         dialogStage.initModality(Modality.WINDOW_MODAL);
         dialogStage.initOwner(primaryStage);
         Scene scene = new Scene(page);
         dialogStage.setScene(scene);

         // Set the party into the controller.
         RelationshipController controller = loader.getController();
         controller.setDialogStage(dialogStage);
         controller.setRelationship(relationship);
         controller.setState(state);

         // Show the dialog and wait until the user closes it
         dialogStage.showAndWait();

         return controller.isOkClicked();
      } catch (IOException e) {
         Logger.getLogger(SampleApplication.class.getName()).log(Level.SEVERE, null, e);
         return false;
      }
   }
}
