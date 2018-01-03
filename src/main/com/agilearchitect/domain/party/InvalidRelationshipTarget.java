package com.agilearchitect.domain.party;

public class InvalidRelationshipTarget extends Exception
{
   private static final long serialVersionUID = 1L;

   private RoleRelationshipKind relationshipType;

   public InvalidRelationshipTarget(RoleRelationshipKind relationshipType)
   {
      this.relationshipType = relationshipType;
   }
   public RoleRelationshipKind getRelationshipType()
   {
      return relationshipType;
   }
}
