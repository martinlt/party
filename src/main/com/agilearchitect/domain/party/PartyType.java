package com.agilearchitect.domain.party;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({RoleType.class})
@XmlRootElement(name = "partytype")
@XmlAccessorType(XmlAccessType.NONE)
public class PartyType extends IdentifiableObject
{
   private String description;

   public PartyType() {
   }

   public PartyType(String description)
   {
      this.description = description;
   }

   @XmlElement
   public String getDescription()
   {
      return this.description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }
}
