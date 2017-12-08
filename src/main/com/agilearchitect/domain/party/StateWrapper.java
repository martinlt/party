package com.agilearchitect.domain.party;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "state")
@XmlAccessorType(XmlAccessType.NONE)
public class StateWrapper
{
   @XmlElement(name = "config")
   private PartyConfig config;

   @XmlElementWrapper(name = "people")
   @XmlElement(name = "person")
   private final List<Person> people = new ArrayList<Person>();

   @XmlElementWrapper(name = "organisations")
   @XmlElement(name = "organisation")
   private final List<Organisation> organisations = new ArrayList<Organisation>();

   @XmlElementWrapper(name = "relationships")
   @XmlElement(name = "relationship")
   private final List<PartyRelationship> relationships = new ArrayList<PartyRelationship>();

   public StateWrapper()
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

   public List<Person> getPeople()
   {
      return people;
   }

   public List<Organisation> getOrganisations()
   {
      return organisations;
   }

   public List<PartyRelationship> getRelationships()
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
}
