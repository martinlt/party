package com.agilearchitect.domain.party;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;

abstract public class IdentifiableObject
{
   @XmlAttribute
   @XmlID
   private String id = UUID.randomUUID().toString();

   public String getId()
   {
      return this.id;
   }
}
