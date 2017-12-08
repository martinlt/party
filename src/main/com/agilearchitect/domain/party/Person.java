package com.agilearchitect.domain.party;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.NONE)
public class Person extends Party
{
   private String givenName = "";
   private String familyName = "";
   private Date dateOfBirth;
   private Date dateOfDeath;

   public Person()
   {
   }

   public Person(PartyType type, String givenName, String familyName, Date dateOfBirth,
         Date dateOfDeath)
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
   @XmlJavaTypeAdapter(DateTimeAdapter.class)
   public Date getDateOfBirth()
   {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateTimeAdapter.class)
   public Date getDateOfDeath()
   {
      return dateOfDeath;
   }

   public void setDateOfDeath(Date dateOfDeath)
   {
      this.dateOfDeath = dateOfDeath;
   }
}
