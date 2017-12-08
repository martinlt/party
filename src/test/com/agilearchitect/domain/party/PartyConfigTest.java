package com.agilearchitect.domain.party;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class PartyConfigTest
{
   @Test
   public void createConfig()
   {

      try {
         JAXBContext jc = JAXBContext.newInstance(PartyConfig.class);
         Unmarshaller u = jc.createUnmarshaller();

         PartyConfig config = new PartyConfig();

         PartyType organisation = new PartyType("Organisation");
         PartyType person = new PartyType("Person");

         config.addPartyType(organisation);
         config.addPartyType(person);

         RoleType employer = new RoleType("Employer", organisation);
         RoleType employee = new RoleType("Employee", person);

         config.addRoleType(employer);
         config.addRoleType(employee);

         config.addRoleTypeRelationship(new RoleTypeRelationship(employer, employee, "Employs"));

         JAXBContext jaxbContext = JAXBContext.newInstance(PartyConfig.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         // marshal to a file
         jaxbMarshaller.marshal(config, new File("config.xml"));

         File xmlFile = new File("config.xml");
         assertTrue(xmlFile.exists());

         config = (PartyConfig) u.unmarshal(xmlFile);

         assertEquals("should have 2 party types", config.getPartyTypes().size(), 2);
         assertEquals("should have 2 role types", config.getRoleTypes().size(), 2);
         assertEquals("should have 1 role type relationship", config.getRoleTypeRelationships().size(), 1);

         assertSame("party type organisation should match role type organisation #1", config.getPartyTypes().get(0), config.getRoleTypes().get(0).getPartyType());
         assertSame("party type organisation should match role type organisation #2", config.getPartyTypes().get(1), config.getRoleTypes().get(1).getPartyType());


      } catch (Exception e) {
         throw new IllegalStateException(e);
      }

   }

}
