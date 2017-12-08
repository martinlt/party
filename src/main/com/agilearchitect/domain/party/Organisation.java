package com.agilearchitect.domain.party;

import java.util.Date;

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
   private Date dateOfInception = new java.util.Date();
   private Date dateOfCessation;

   public Organisation()
   {
   }

   public Organisation(PartyType type, String organisationName)
   {
      super(type);

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
   @XmlJavaTypeAdapter(DateTimeAdapter.class)
   public Date getDateOfInception()
   {
      return dateOfInception;
   }

   public void setDateOfInception(Date dateOfInception)
   {
      this.dateOfInception = dateOfInception;
   }

   @XmlElement
   @XmlJavaTypeAdapter(DateTimeAdapter.class)
   public Date getDateOfCessation()
   {
      return dateOfCessation;
   }

   public void setDateOfCessation(Date dateOfCessation)
   {
      this.dateOfCessation = dateOfCessation;
   }
}
