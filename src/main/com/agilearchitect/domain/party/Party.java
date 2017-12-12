package com.agilearchitect.domain.party;

abstract public class Party extends IdentifiableObject
{
   private PartyType type;

   public Party()
   {

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
