package com.agilearchitect.domain.party;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class OrganisationTest
{

   @Test
   public void createOrganisation()
   {
      PartyConfig config = PartyConfig.loadConfig();

      Organisation party = new Organisation(config.getPartyTypes().get(0), "ABC Company Ltd");

      try {
         JAXBContext jaxbContext = JAXBContext.newInstance(Organisation.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         // marshal to a file
         jaxbMarshaller.marshal(party, new File("organisation.xml"));

         Unmarshaller u = jaxbContext.createUnmarshaller();

         File xmlFile = new File("organisation.xml");
         assertTrue(xmlFile.exists());

         party = (Organisation) u.unmarshal(xmlFile);

         assertEquals("organisation name should match", party.getOrganisationName(), "ABC Company Ltd");

      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

}
