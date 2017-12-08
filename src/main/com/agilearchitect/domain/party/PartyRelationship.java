package com.agilearchitect.domain.party;

import java.util.Date;

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
   private Date effectiveFrom = new java.util.Date();
   private Date effectiveTo;
   private Party from;
   private Party to;
   private RoleTypeRelationship relationshipType;

   public PartyRelationship()
   {

   }

   public PartyRelationship(Date effectiveFrom, Date effectiveTo, Party from, Party to,
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
   @XmlJavaTypeAdapter(DateTimeAdapter.class)
   public Date getEffectiveFrom()
   {
      return effectiveFrom;
   }

   public void setEffectiveFrom(Date effectiveFrom)
   {
      this.effectiveFrom = effectiveFrom;
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateTimeAdapter.class)
   public Date getEffectiveTo()
   {
      return effectiveTo;
   }

   public void setEffectiveTo(Date effectiveTo)
   {
      this.effectiveTo = effectiveTo;
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
