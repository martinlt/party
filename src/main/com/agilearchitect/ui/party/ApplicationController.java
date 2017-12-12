package com.agilearchitect.ui.party;

import com.agilearchitect.domain.party.Organisation;
import com.agilearchitect.domain.party.PartyRelationship;
import com.agilearchitect.domain.party.Person;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ApplicationController
{
   private SampleApplication appl;
   private ApplicationState state;

   @FXML
   private TableView<Organisation> organisationsTable;
   @FXML
   private TableColumn<Organisation, String> nameColumn;
   @FXML
   private TableColumn<Organisation, String> dateInceptionColumn;
   @FXML
   private TableColumn<Organisation, String> dateCessationColumn;

   @FXML
   private TableView<Person> peopleTable;
   @FXML
   private TableColumn<Person, String> givenNameColumn;
   @FXML
   private TableColumn<Person, String> familyNameColumn;
   @FXML
   private TableColumn<Person, String> dateBirthColumn;
   @FXML
   private TableColumn<Person, String> dateDeathColumn;

   @FXML
   private TableView<PartyRelationship> relationshipTable;
   @FXML
   private TableColumn<PartyRelationship, String> fromColumn;
   @FXML
   private TableColumn<PartyRelationship, String> typeColumn;
   @FXML
   private TableColumn<PartyRelationship, String> toColumn;
   @FXML
   private TableColumn<PartyRelationship, String> dateFromColumn;
   @FXML
   private TableColumn<PartyRelationship, String> dateToColumn;

   public void init(SampleApplication appl, ApplicationState state)
   {
      this.appl = appl;
      this.state = state;

      if (state != null) {
         organisationsTable.setItems(state.getOrganisations());
         peopleTable.setItems(state.getPeople());
         relationshipTable.setItems(state.getRelationships());
      }
   }

   /**
    * Initializes the controller class. This method is automatically called
    * after the fxml file has been loaded.
    */
   @FXML
   private void initialize()
   {
      // bind the table fields for the Organisations tab
      nameColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getOrganisationName()));
      dateInceptionColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getDateOfInceptionString()));
      dateCessationColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getDateOfCessationString()));

      // bind the table fields for the People tab
      givenNameColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getGivenName()));
      familyNameColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getFamilyName()));
      dateBirthColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getDateOfBirthString()));
      dateDeathColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getDateOfDeathString()));

      // bind the table fields for the Relationships tab
      fromColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getFrom().toString()));
      typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getRelationshipType().getDescription()));
      toColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getTo().toString()));
      dateFromColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getEffectiveFromString()));
      dateToColumn.setCellValueFactory(
            cellData -> new SimpleStringProperty(cellData.getValue().getEffectiveToString()));

   }

   /**
    * Called when the user clicks the new button. Opens a dialog to edit details
    * for a new organisation.
    */
   @FXML
   private void handleNewOrganisation()
   {
      Organisation organisation = new Organisation();

      boolean okClicked = appl.showOrganisationDialog(organisation);
      if (okClicked) {
         state.addOrganisation(organisation);
      }
   }

   /**
    * Called when the user clicks the new button. Opens a dialog to edit details
    * for a new person.
    */
   @FXML
   private void handleNewPerson()
   {
      Person person = new Person();

      boolean okClicked = appl.showPersonDialog(person);
      if (okClicked) {
         state.addPerson(person);
      }
   }

   /**
    * Called when the user clicks the new button. Opens a dialog to edit details
    * for a new relationship.
    */
   @FXML
   private void handleNewRelationship()
   {
      PartyRelationship relationship = new PartyRelationship();
      boolean okClicked = appl.showRelationshipDialog(relationship);
      if (okClicked) {
         state.addRelationship(relationship);
      }
   }
}
