package com.agilearchitect.domain.party;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "organisation")
@XmlAccessorType(XmlAccessType.NONE)
public class Organisation extends Party
{
   private String organisationName = "";
   private LocalDate dateOfInception = LocalDate.now();
   private LocalDate dateOfCessation;

   public Organisation()
   {
      super.setPartyType(PartyKind.ORGANISATION);
   }

   public Organisation(String organisationName)
   {
      super.setPartyType(PartyKind.ORGANISATION);

      this.organisationName = organisationName;
   }

   @XmlElement
   public String getOrganisationName()
   {
      return this.organisationName;
   }

   public void setOrganisationName(String organisationName)
   {
      this.organisationName = organisationName;
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateAdapter.class)
   public LocalDate getDateOfInception()
   {
      return dateOfInception;
   }

   public String getDateOfInceptionString()
   {
      if(dateOfInception == null)
         return "";
      else
         return dateOfInception.toString();
   }

   public void setDateOfInception(LocalDate dateOfInception)
   {
      this.dateOfInception = dateOfInception;
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateAdapter.class)
   public LocalDate getDateOfCessation()
   {
      return dateOfCessation;
   }

   public String getDateOfCessationString()
   {
      if(dateOfCessation == null)
         return "";
      else
         return dateOfCessation.toString();
   }

   public void setDateOfCessation(LocalDate dateOfCessation)
   {
      this.dateOfCessation = dateOfCessation;
   }
}
