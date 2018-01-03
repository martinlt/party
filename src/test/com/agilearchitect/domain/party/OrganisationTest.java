package com.agilearchitect.domain.party;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class OrganisationTest
{

   @Rule
   public TemporaryFolder testFolder = new TemporaryFolder();

   @Test
   public void createOrganisation()
   {
      Organisation party = new Organisation("ABC Company Ltd");

      try {
         JAXBContext jaxbContext = JAXBContext.newInstance(Organisation.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         // marshal to a file
         File file = testFolder.newFile();
         jaxbMarshaller.marshal(party, file);

         Unmarshaller u = jaxbContext.createUnmarshaller();

         assertTrue(file.exists());

         party = (Organisation) u.unmarshal(file);

         assertEquals("organisation name should match", party.getOrganisationName(),
               "ABC Company Ltd");

      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

}
