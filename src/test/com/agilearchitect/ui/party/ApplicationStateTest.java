package com.agilearchitect.ui.party;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.agilearchitect.domain.party.Organisation;
import com.agilearchitect.domain.party.Person;
import com.agilearchitect.domain.party.PartyRelationship;
import com.agilearchitect.domain.party.RoleRelationshipKind;

public class ApplicationStateTest
{
   private Organisation organisation;
   private Person person;
   private PartyRelationship relationship;
   private File file;

   @Rule
   public TemporaryFolder testFolder = new TemporaryFolder();

   @Before
   public void setUp() throws Exception
   {
      file = testFolder.newFile();

      // create an employment relationship between a person and an organisation
      organisation = new Organisation("Acme Incorporated");
      person = new Person("John", "Doe", LocalDate.of(1970, Month.MARCH, 1), null);
      relationship = new PartyRelationship(LocalDate.of(2015, Month.FEBRUARY, 14), null,
            organisation, person, RoleRelationshipKind.EMPLOYMENT);
   }

   @Test
   public void unmarshalEmptyXML()
   {
      ApplicationState state = ApplicationState.unmarshalXml(file);
      assertNotNull(state);
   }

   @Test
   public void unmarshalEmptyJson()
   {
      ApplicationState state = ApplicationState.unmarshalJson(file);
      assertNotNull(state);
   }

   @Test
   public void marshalAndUnmarshalXml()
   {
      ApplicationState state = new ApplicationState();

      state.addPerson(person);
      state.addOrganisation(organisation);
      state.addRelationship(relationship);

      state.marshalToXml(file);

      ApplicationState state2 = ApplicationState.unmarshalXml(file);

      assertEquals(state2.getPeople().size(), 1);
      assertEquals(state2.getOrganisations().size(), 1);
      assertEquals(state2.getRelationships().size(), 1);
   }

   @Test
   public void marshalAndUnmarshalJson()
   {
      ApplicationState state = new ApplicationState();

      state.addPerson(person);
      state.addOrganisation(organisation);
      state.addRelationship(relationship);

      state.marshalToJson(file);

      ApplicationState state2 = ApplicationState.unmarshalJson(file);

      assertEquals(state2.getPeople().size(), 1);
      assertEquals(state2.getOrganisations().size(), 1);
      assertEquals(state2.getRelationships().size(), 1);
   }
}
