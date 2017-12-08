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
}
