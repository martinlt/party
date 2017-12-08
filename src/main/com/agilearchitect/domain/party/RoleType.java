package com.agilearchitect.domain.party;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({PartyType.class})
@XmlRootElement(name = "roletype")
@XmlAccessorType(XmlAccessType.NONE)
public class RoleType extends IdentifiableObject
{
   private String description;

   @XmlIDREF
   @XmlElement(name="partyType", type=PartyType.class)
   private PartyType partyType;

   public RoleType() {

   }

   public RoleType(String description, PartyType partyType)
   {
      this.description = description;
      this.partyType = partyType;
   }

   @XmlElement
   public String getDescription()
   {
      return this.description;
   }

   public PartyType getPartyType()
   {
      return this.partyType;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public void setPartyType(PartyType partyType)
   {
      this.partyType = partyType;
   }


}
