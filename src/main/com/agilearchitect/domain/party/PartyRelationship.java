package com.agilearchitect.domain.party;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "partyRelationship")
@XmlAccessorType(XmlAccessType.NONE)
public class PartyRelationship extends IdentifiableObject
{
   private LocalDate effectiveFrom = LocalDate.now();
   private LocalDate effectiveTo;
   private Party from;
   private Party to;
   private RoleTypeRelationship relationshipType;

   public PartyRelationship()
   {

   }

   public PartyRelationship(LocalDate effectiveFrom, LocalDate effectiveTo, Party from, Party to,
         RoleTypeRelationship relationshipType)
   {
      super();
      this.effectiveFrom = effectiveFrom;
      this.effectiveTo = effectiveTo;
      this.from = from;
      this.to = to;
      this.relationshipType = relationshipType;
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateAdapter.class)
   public LocalDate getEffectiveFrom()
   {
      return effectiveFrom;
   }

   public void setEffectiveFrom(LocalDate effectiveFrom)
   {
      this.effectiveFrom = effectiveFrom;
   }

   public String getEffectiveFromString()
   {
      if(effectiveFrom == null)
         return "";
      else
         return effectiveFrom.toString();
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateAdapter.class)
   public LocalDate getEffectiveTo()
   {
      return effectiveTo;
   }

   public void setEffectiveTo(LocalDate effectiveTo)
   {
      this.effectiveTo = effectiveTo;
   }

   public String getEffectiveToString()
   {
      if(effectiveTo == null)
         return "";
      else
         return effectiveTo.toString();
   }

   @XmlIDREF
   @XmlElement(name = "from", type = Party.class)
   public Party getFrom()
   {
      return from;
   }

   public void setFrom(Party from)
   {
      this.from = from;
   }

   @XmlIDREF
   @XmlElement(name = "to", type = Party.class)
   public Party getTo()
   {
      return to;
   }

   public void setTo(Party to)
   {
      this.to = to;
   }

   @XmlIDREF
   @XmlElement(name = "relationshipType", type = RoleTypeRelationship.class)
   public RoleTypeRelationship getRelationshipType()
   {
      return relationshipType;
   }

   public void setRelationshipType(RoleTypeRelationship relationshipType)
   {
      this.relationshipType = relationshipType;
   }
}
