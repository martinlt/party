package com.agilearchitect.domain.party;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PersonTest
{
   @Rule
   public TemporaryFolder testFolder = new TemporaryFolder();

   @Test
   public void createPerson()
   {
      Person party = new Person("John", "Doe", LocalDate.of(1970, Month.MARCH, 1), null);

      try {
         JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         // marshal to a file
         File file = testFolder.newFile();
         jaxbMarshaller.marshal(party, file);

         Unmarshaller u = jaxbContext.createUnmarshaller();

         assertTrue(file.exists());

         party = (Person) u.unmarshal(file);

         assertEquals("family name should match", party.getFamilyName(), "Doe");

      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }
}
