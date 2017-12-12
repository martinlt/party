package com.agilearchitect.domain.party;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.NONE)
public class Person extends Party
{
   private String givenName = "";
   private String familyName = "";
   private LocalDate dateOfBirth;
   private LocalDate dateOfDeath;

   public Person()
   {
   }

   public Person(PartyType type, String givenName, String familyName, LocalDate dateOfBirth,
         LocalDate dateOfDeath)
   {
      super(type);

      this.familyName = familyName;
      this.givenName = givenName;
      this.dateOfBirth = dateOfBirth;
      this.dateOfDeath = dateOfDeath;
   }

   @XmlElement
   public String getGivenName()
   {
      return givenName;
   }

   public void setGivenName(String givenName)
   {
      this.givenName = givenName;
   }

   @XmlElement
   public String getFamilyName()
   {
      return familyName;
   }

   public void setFamilyName(String familyName)
   {
      this.familyName = familyName;
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateAdapter.class)
   public LocalDate getDateOfBirth()
   {
      return dateOfBirth;
   }

   @JsonIgnore
   public String getDateOfBirthString()
   {
      if(dateOfBirth == null)
         return "";
      else
         return dateOfBirth.toString();
   }

   public void setDateOfBirth(LocalDate dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateAdapter.class)
   public LocalDate getDateOfDeath()
   {
      return dateOfDeath;
   }

   @JsonIgnore
   public String getDateOfDeathString()
   {
      if(dateOfDeath == null)
         return "";
      else
         return dateOfDeath.toString();
   }

   public void setDateOfDeath(LocalDate dateOfDeath)
   {
      this.dateOfDeath = dateOfDeath;
   }
}
