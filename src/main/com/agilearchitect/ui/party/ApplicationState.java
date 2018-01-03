package com.agilearchitect.ui.party;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.agilearchitect.domain.party.Organisation;
import com.agilearchitect.domain.party.PartyRelationship;
import com.agilearchitect.domain.party.Person;
//import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.persistence.jaxb.MarshallerProperties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement(name = "applicationstate")
@XmlAccessorType(XmlAccessType.NONE)
public class ApplicationState
{
   @XmlElementWrapper(name = "people")
   @XmlElement(name = "person")
   private final ObservableList<Person> people = FXCollections.observableArrayList();

   @XmlElementWrapper(name = "organisations")
   @XmlElement(name = "organisation")
   private final ObservableList<Organisation> organisations = FXCollections.observableArrayList();

   @XmlElementWrapper(name = "relationships")
   @XmlElement(name = "relationship")
   private final ObservableList<PartyRelationship> relationships = FXCollections
         .observableArrayList();

   public ApplicationState()
   {

   }

   public ObservableList<Person> getPeople()
   {
      return people;
   }

   public ObservableList<Organisation> getOrganisations()
   {
      return organisations;
   }

   public ObservableList<PartyRelationship> getRelationships()
   {
      return relationships;
   }

   public void addPerson(Person person)
   {
      this.people.add(person);
   }

   public void addOrganisation(Organisation organisation)
   {
      this.organisations.add(organisation);
   }

   public void addRelationship(PartyRelationship relationship)
   {
      this.relationships.add(relationship);
   }

   public static ApplicationState load()
   {
      return unmarshalXml(new File("applicationstate.xml"));
   }

   public void save()
   {
      this.marshalToXml(new File("applicationstate.xml"));
      this.marshalToJson(new File("applicationstate.json"));
   }

   public void marshalToXml(File file)
   {
      try {
         JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationState.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         jaxbMarshaller.marshal(this, file);
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   public void marshalToJson(File file)
   {
      try {
         JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationState.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
         jaxbMarshaller.marshal(this, file);
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   public static ApplicationState unmarshalXml(File file)
   {
      ApplicationState state;

      try {
         JAXBContext jc = JAXBContext.newInstance(ApplicationState.class);
         Unmarshaller u = jc.createUnmarshaller();

         if (file.length() != 0) {
            state = (ApplicationState) u.unmarshal(file);
         } else {
            state = new ApplicationState();
         }

      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
      return state;
   }

   public static ApplicationState unmarshalJson(File file)
   {
      ApplicationState state = new ApplicationState();

      try {
         JAXBContext jc = JAXBContext.newInstance(ApplicationState.class);
         Unmarshaller u = jc.createUnmarshaller();
         u.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

         if (file.length() != 0) {
            state = (ApplicationState) u.unmarshal(file);
         } else {
            state = new ApplicationState();
         }
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
      return state;
   }
}
