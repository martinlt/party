package com.agilearchitect.domain.party;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum RoleKind {
   EMPLOYER(PartyKind.ORGANISATION, "Employer"),
   EMPLOYEE(PartyKind.PERSON, "Employee"),
   TEACHER(PartyKind.PERSON, "Teacher"),
   STUDENT(PartyKind.PERSON, "Student"),
   HUSBAND(PartyKind.PERSON, "Husband"),
   WIFE(PartyKind.PERSON, "Wife");

   private PartyKind partyKind;
   private String description;

   private RoleKind(PartyKind partyKind, String description) {
      this.partyKind = partyKind;
       this.description = description;
   }

   @Override
   public String toString() {
       return description;
   }

   public PartyKind getPartyKind() {
      return this.partyKind;
   }
}
