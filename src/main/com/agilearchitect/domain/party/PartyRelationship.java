package com.agilearchitect.domain.party;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "partyRelationship")
@XmlAccessorType(XmlAccessType.NONE)
public class PartyRelationship extends IdentifiableObject
{
   private LocalDate effectiveFrom = LocalDate.now();
   private LocalDate effectiveTo;
   private Party from;
   private Party to;
   private RoleRelationshipKind relationshipType;

   public PartyRelationship()
   {

   }

   public PartyRelationship(LocalDate effectiveFrom, LocalDate effectiveTo, Party from, Party to,
         RoleRelationshipKind relationshipType) throws InvalidRelationshipTarget
   {
      super();
      this.effectiveFrom = effectiveFrom;
      this.effectiveTo = effectiveTo;
      this.relationshipType = relationshipType;
      this.setFrom(from);
      this.setTo(to);

   }

   @XmlElement
   @XmlJavaTypeAdapter(DateAdapter.class)
   @JsonIgnore
   public LocalDate getEffectiveFrom()
   {
      return effectiveFrom;
   }

   public void setEffectiveFrom(LocalDate effectiveFrom)
   {
      this.effectiveFrom = effectiveFrom;
   }

   @JsonProperty("effectiveFrom")
   public String getEffectiveFromString()
   {
      if (effectiveFrom == null)
         return "";
      else
         return effectiveFrom.toString();
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateAdapter.class)
   @JsonIgnore
   public LocalDate getEffectiveTo()
   {
      return effectiveTo;
   }

   public void setEffectiveTo(LocalDate effectiveTo)
   {
      this.effectiveTo = effectiveTo;
   }

   @JsonProperty("effectiveTo")
   public String getEffectiveToString()
   {
      if (effectiveTo == null)
         return "";
      else
         return effectiveTo.toString();
   }

   @XmlIDREF
   @XmlElement(name = "from", type = Party.class)
   @JsonManagedReference
   public Party getFrom()
   {
      return from;
   }

   public void setFrom(Party from) throws InvalidRelationshipTarget
   {
      this.from = from;

      if (from != null) {
         if(from.getType() != relationshipType.getFrom().getPartyKind())
            throw new InvalidRelationshipTarget(relationshipType);

         from.addFromRelationship(this);
      }
   }

   @XmlIDREF
   @XmlElement(name = "to", type = Party.class)
   @JsonManagedReference
   public Party getTo()
   {
      return to;
   }

   public void setTo(Party to) throws InvalidRelationshipTarget
   {
      this.to = to;

      if (to != null) {
         if(to.getType() != relationshipType.getTo().getPartyKind())
            throw new InvalidRelationshipTarget(relationshipType);

         to.addToRelationship(this);
      }
   }

   @XmlElement(name = "relationshipType", type = RoleRelationshipKind.class)
   public RoleRelationshipKind getRelationshipType()
   {
      return relationshipType;
   }

   public void setRelationshipType(RoleRelationshipKind relationshipType)
   {
      this.relationshipType = relationshipType;
   }
}
