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
import com.agilearchitect.domain.party.PartyConfig;
import com.agilearchitect.domain.party.PartyRelationship;
import com.agilearchitect.domain.party.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



@XmlRootElement(name = "applicationstate")
@XmlAccessorType(XmlAccessType.NONE)
public class ApplicationState
{
   @XmlElement(name = "config")
   private PartyConfig config;

   @XmlElementWrapper(name = "people")
   @XmlElement(name = "person")
   private final ObservableList<Person> people = FXCollections.observableArrayList();

   @XmlElementWrapper(name = "organisations")
   @XmlElement(name = "organisation")
   private final ObservableList<Organisation> organisations = FXCollections.observableArrayList();

   @XmlElementWrapper(name = "relationships")
   @XmlElement(name = "relationship")
   private final ObservableList<PartyRelationship> relationships = FXCollections.observableArrayList();

   public ApplicationState()
   {

   }

   public PartyConfig getConfig()
   {
      return config;
   }

   public void setConfig(PartyConfig config)
   {
      this.config = config;
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
      return load("applicationstate.xml");
   }

   public void save()
   {
      try {
         File xmlFile = new File("applicationstate.xml");
         JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationState.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         // marshal to file as xml
         jaxbMarshaller.marshal(this, xmlFile);

         // marshal to file as json
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("applicationstate.json"), this);

      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   private static ApplicationState load(String filename)
   {
      ApplicationState state;

      try {
         JAXBContext jc = JAXBContext.newInstance(ApplicationState.class);
         Unmarshaller u = jc.createUnmarshaller();

         File xmlFile = new File(filename);
         if (xmlFile.exists()) {
            state = (ApplicationState) u.unmarshal(xmlFile);
         } else {
            state = new ApplicationState();
            PartyConfig config = PartyConfig.loadConfig();
            state.setConfig(config);
            state.save();
         }

      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
      return state;
   }
}
