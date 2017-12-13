package com.agilearchitect.domain.party;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum RoleRelationshipKind {
   EMPLOYMENT(RoleKind.EMPLOYER, RoleKind.EMPLOYEE, "Employs"),
   EDUCATION(RoleKind.TEACHER, RoleKind.STUDENT, "Teaches"),
   MARRIAGE(RoleKind.HUSBAND, RoleKind.WIFE, "Married");

   private RoleKind from;
   private RoleKind to;
   private String description;

   private RoleRelationshipKind(RoleKind from, RoleKind to, String description)
   {
      this.from = from;
      this.to = to;
      this.description = description;
   }

   @Override
   public String toString()
   {
      return description;
   }

   public RoleKind getFrom()
   {
      return this.from;
   }

   public RoleKind getTo()
   {
      return this.to;
   }
}
