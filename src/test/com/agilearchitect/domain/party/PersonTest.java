package com.agilearchitect.domain.party;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class PersonTest
{
   @Test
   public void createPerson()
   {
      PartyConfig config = PartyConfig.loadConfig();

      Person party = new Person(config.getPartyTypes().get(1), "John", "Doe",
            LocalDate.of(1970, Month.MARCH, 1), null);

      try {
         JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         // marshal to a file
         jaxbMarshaller.marshal(party, new File("person.xml"));

         Unmarshaller u = jaxbContext.createUnmarshaller();

         File xmlFile = new File("person.xml");
         assertTrue(xmlFile.exists());

         party = (Person) u.unmarshal(xmlFile);

         assertEquals("family name should match", party.getFamilyName(), "Doe");

      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }
}
