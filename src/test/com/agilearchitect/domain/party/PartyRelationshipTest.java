package com.agilearchitect.domain.party;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class PartyRelationshipTest
{
   @Test
   public void createRelationship() {
      PartyConfig config = PartyConfig.loadConfig();

      StateWrapper state = new StateWrapper();

      Organisation party1 = new Organisation(config.getPartyTypes().get(0), "ABC Company Ltd");
      Person party2 = new Person(config.getPartyTypes().get(1), "John", "Doe",
            new GregorianCalendar(1970, Calendar.MARCH, 01, 18, 30).getTime(), null);

      PartyRelationship relationship = new PartyRelationship(new java.util.Date(), null, party1,
            party2, config.getRoleTypeRelationships().get(0));

      state.setConfig(config);
      state.addOrganisation(party1);
      state.addPerson(party2);
      state.addRelationship(relationship);

      try {
         JAXBContext jaxbContext = JAXBContext.newInstance(StateWrapper.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         // marshal to a file
         jaxbMarshaller.marshal(state, new File("state.xml"));

         Unmarshaller u = jaxbContext.createUnmarshaller();

         File xmlFile = new File("state.xml");
         assertTrue(xmlFile.exists());

         state = (StateWrapper) u.unmarshal(xmlFile);

         assertSame("should have one relationship", state.getRelationships().size(), 1);
         assertEquals("first party role should be employer", state.getRelationships().get(0).getRelationshipType().getFrom().getDescription(),"Employer");
         assertEquals("second party role should be employee", state.getRelationships().get(0).getRelationshipType().getTo().getDescription(),"Employee");


      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }
}
