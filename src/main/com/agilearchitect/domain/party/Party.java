package com.agilearchitect.domain.party;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;

abstract public class Party extends IdentifiableObject
{
   private PartyType type;

   private List<PartyRelationship> fromRelationships = new ArrayList<PartyRelationship>();
   private List<PartyRelationship> toRelationships = new ArrayList<PartyRelationship>();

   public Party()
   {

   }

   @XmlIDREF
   @XmlElementWrapper(name = "fromRelationships")
   @XmlElement(name = "fromRelationship", type = PartyRelationship.class)
   public List<PartyRelationship> getFromRelationships() {
      return this.fromRelationships;
   }

   @XmlIDREF
   @XmlElementWrapper(name = "toRelationships")
   @XmlElement(name = "toRelationship", type = PartyRelationship.class)
   public List<PartyRelationship> getToRelationships() {
      return this.toRelationships;
   }

   public void addFromRelationship(PartyRelationship relationship)
   {
      if(!fromRelationships.contains(relationship))
         fromRelationships.add(relationship);
   }

   public void addToRelationship(PartyRelationship relationship)
   {
      if(!toRelationships.contains(relationship))
         toRelationships.add(relationship);
   }

   public boolean hasToRelationship(Party fromParty) {
      for(PartyRelationship pr : toRelationships) {
         if(pr.getFrom() == fromParty)
            return true;
      }
      return false;
   }

   public boolean hasFromRelationship(Party toParty) {
      for(PartyRelationship pr : fromRelationships) {
         if(pr.getTo() == toParty)
            return true;
      }
      return false;
   }

   public Party(PartyType type)
   {
      this.type = type;
   }

   public PartyType getType()
   {
      return this.type;
   }

   public void setType(PartyType type)
   {
      this.type = type;
   }

   @Override
   public String toString() {
      if(this instanceof Organisation) {
         Organisation o = (Organisation) this;
         return o.getOrganisationName();
      }
      if(this instanceof Person) {
         Person p = (Person) this;
         return p.getGivenName() + " " + p.getFamilyName();
      }
      return "";
   }
}
