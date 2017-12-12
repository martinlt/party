package com.agilearchitect.domain.party;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "partyconfig")
@XmlAccessorType(XmlAccessType.NONE)
public class PartyConfig
{
   @XmlElementWrapper(name = "partyTypes")
   @XmlElement(name = "partyType")
   private final List<PartyType> partyTypes = new ArrayList<PartyType>();

   @XmlElementWrapper(name = "roleTypes")
   @XmlElement(name = "roleType")
   private final List<RoleType> roleTypes = new ArrayList<RoleType>();

   @XmlElementWrapper(name = "roleTypeRelationships")
   @XmlElement(name = "roleTypeRelationships")
   private final List<RoleTypeRelationship> roleTypeRelationships = new ArrayList<RoleTypeRelationship>();

   public void addPartyType(PartyType type)
   {
      this.partyTypes.add(type);
   }

   public void addRoleType(RoleType type)
   {
      this.roleTypes.add(type);
   }

   public void addRoleTypeRelationship(RoleTypeRelationship relationship)
   {
      this.roleTypeRelationships.add(relationship);
   }

   public List<PartyType> getPartyTypes()
   {
      return this.partyTypes;
   }

   public List<RoleType> getRoleTypes()
   {
      return this.roleTypes;
   }

   public List<RoleTypeRelationship> getRoleTypeRelationships()
   {
      return this.roleTypeRelationships;
   }

   public static PartyConfig loadConfig()
   {
      return loadConfig("config.xml");
   }

   private static PartyConfig loadConfig(String filename)
   {
      PartyConfig config;

      try {
         JAXBContext jc = JAXBContext.newInstance(PartyConfig.class);
         Unmarshaller u = jc.createUnmarshaller();

         File xmlFile = new File(filename);
         if (xmlFile.exists()) {
            config = (PartyConfig) u.unmarshal(xmlFile);
         } else {
            config = new PartyConfig();

            PartyType organisation = new PartyType("Organisation");
            PartyType person = new PartyType("Person");

            config.addPartyType(organisation);
            config.addPartyType(person);

            RoleType employer = new RoleType("Employer", organisation);
            RoleType employee = new RoleType("Employee", person);
            RoleType teacher = new RoleType("Teacher", person);
            RoleType student = new RoleType("Student", person);

            config.addRoleType(employer);
            config.addRoleType(employee);
            config.addRoleType(teacher);;
            config.addRoleType(student);

            config.addRoleTypeRelationship(new RoleTypeRelationship(employer, employee, "Employs"));
            config.addRoleTypeRelationship(new RoleTypeRelationship(teacher, student, "Teaches"));

            JAXBContext jaxbContext = JAXBContext.newInstance(PartyConfig.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // marshal to a file
            jaxbMarshaller.marshal(config, xmlFile);
         }

      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
      return config;
   }
}
