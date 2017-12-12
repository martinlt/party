package com.agilearchitect.domain.party;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;

public class RoleTypeRelationship extends IdentifiableObject
{
   private RoleType from;
   private RoleType to;
   private String description;

   public RoleTypeRelationship()
   {

   }

   public RoleTypeRelationship(RoleType from, RoleType to, String description)
   {
      super();
      this.from = from;
      this.to = to;
      this.description = description;
   }

   @XmlIDREF
   @XmlElement(name="from", type=RoleType.class)
   public RoleType getFrom()
   {
      return from;
   }

   public void setFrom(RoleType from)
   {
      this.from = from;
   }

   @XmlIDREF
   @XmlElement(name="to", type=RoleType.class)
   public RoleType getTo()
   {
      return to;
   }

   public void setTo(RoleType to)
   {
      this.to = to;
   }

   @XmlElement
   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   @Override
   public String toString() {
      if((from == null) || (to == null))
            return "";

      return from.getDescription() + " to " + to.getDescription();
   }
}
