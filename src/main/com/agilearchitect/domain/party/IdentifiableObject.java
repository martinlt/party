package com.agilearchitect.domain.party;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
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
