package com.agilearchitect.domain.party;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.agilearchitect.ui.party.ApplicationState;

public class PartyRelationshipTest
{
   @Test
   public void createRelationship()
   {
      ApplicationState state = new ApplicationState();

      Organisation party1 = new Organisation("ABC Company Ltd");
      Person party2 = new Person("John", "Doe", LocalDate.of(1970, Month.MARCH, 1), null);

      PartyRelationship relationship = new PartyRelationship(LocalDate.now(), null, party1, party2,
            RoleRelationshipKind.EMPLOYS);

      state.addOrganisation(party1);
      state.addPerson(party2);
      state.addRelationship(relationship);

      try {
         JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationState.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         // marshal to a file
         jaxbMarshaller.marshal(state, new File("state.xml"));

         Unmarshaller u = jaxbContext.createUnmarshaller();

         File xmlFile = new File("state.xml");
         assertTrue(xmlFile.exists());

         state = (ApplicationState) u.unmarshal(xmlFile);

         assertSame("should have one relationship", state.getRelationships().size(), 1);
         assertEquals("first party role should be employer", RoleRelationshipKind.EMPLOYS.getFrom(),
               RoleKind.EMPLOYER);
         assertEquals("second party role should be employee", RoleRelationshipKind.EMPLOYS.getTo(),
               RoleKind.EMPLOYEE);

      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }
}
