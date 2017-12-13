package com.agilearchitect.domain.party;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum PartyKind {
   ORGANISATION("Organisation"),
   PERSON("Person");

   private String description;

   private PartyKind(String description) {
       this.description = description;
   }

   @Override
   public String toString() {
       return description;
   }
}
