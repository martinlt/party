package com.agilearchitect.domain.party;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class PartyRelationshipTest
{
   @Test
   public void createRelationship() throws InvalidRelationshipTarget
   {
      Organisation party1 = new Organisation("ABC Company Ltd");
      Person party2 = new Person("John", "Doe", LocalDate.of(1970, Month.MARCH, 1), null);

      PartyRelationship relationship = new PartyRelationship(LocalDate.now(), null, party1, party2,
            RoleRelationshipKind.EMPLOYMENT);

      assertEquals(relationship.getFrom(), party1);
      assertEquals(relationship.getTo(), party2);
      assertEquals(relationship.getRelationshipType(), RoleRelationshipKind.EMPLOYMENT);
   }

   @Test(expected = InvalidRelationshipTarget.class)
   public void createInvalidRelationship() throws InvalidRelationshipTarget
   {
      Organisation party1 = new Organisation("Organisation 1");
      Organisation party2 = new Organisation("Organisation 2");

      // try to create an employment relationship between two organisations
      new PartyRelationship(LocalDate.now(), null, party1, party2, RoleRelationshipKind.EMPLOYMENT);
   }
}
