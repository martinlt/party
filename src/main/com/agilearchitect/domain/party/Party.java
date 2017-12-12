package com.agilearchitect.domain.party;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;

import com.fasterxml.jackson.annotation.JsonBackReference;

abstract public class Party extends IdentifiableObject
{
   private List<PartyRelationship> fromRelationships = new ArrayList<PartyRelationship>();
   private List<PartyRelationship> toRelationships = new ArrayList<PartyRelationship>();

   public Party()
   {

   }

   @XmlIDREF
   @XmlElementWrapper(name = "fromRelationships")
   @XmlElement(name = "fromRelationship", type = PartyRelationship.class)
   @JsonBackReference
   public List<PartyRelationship> getFromRelationships() {
      return this.fromRelationships;
   }

   @XmlIDREF
   @XmlElementWrapper(name = "toRelationships")
   @XmlElement(name = "toRelationship", type = PartyRelationship.class)
   @JsonBackReference
   public List<PartyRelationship> getToRelationships() {
      return this.toRelationships;
   }

   protected void addFromRelationship(PartyRelationship relationship)
   {
      if(!fromRelationships.contains(relationship))
         fromRelationships.add(relationship);
   }

   protected void addToRelationship(PartyRelationship relationship)
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
