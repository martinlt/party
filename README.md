# Party
This is an implementation of the agile architect's pattern for party, addressing the universal theme of relationships between people and organisations.

Code here is based on the following class diagram:

![Party Class Model](/doc/party.png)

This model allows for a flexible set of relationships that are controlled by creating instances of PartyType, RoleType and RoleTypeRelationship as follows:

## Identifiable Object
This abstract class provides a mechanism for allocating unique ids to instances that require them. This removes the dependency on an external system (such as a database) to generate unique ids and allows the object model to be marshalled to XML with valid references.

## Party
This is an abstract class used to represent either a Person or an Organisation. A Party can have relationships with other Partys and this is managed through the PartyRelationship class.

## Organisation
Pretty self-explanatory, a placeholder for information held about an organisation.

## Person
Also self-explantatory, this is a placeholder for information held about a person.

## PartyType
This is the first of the "configuration" type classes and holds the valid types of party - in this example, these are "Organisation" and "Person".

## RoleType
This class defines which roles are valid for Parties to assume as part of a relationship to another Party. Some examples of possible roles include:
- Customer
- Suppler
- Teacher
- Learner
- Employer
- Employee

The roles could be expressed as generic or as specific as a particular problem domain dictates. More specific roles may be:
 - Public Limited Company
 - Limited Company
 - HM Revenue and Customs (HMRC)
 - Chief Executive Officer (CEO)
 - Software Engineer

 Note also that the RoleType is specific to a PartyType, e.g. "Employer" may be specific to "Organisation".

## RoleTypeRelationship
This class defines what relationships are valid between roles and hence available as PartyRelationships.

## PartyConfig
This class acts as a wrapper for the configuration classes, providing a simple means to serialize or marshal the data.

## PartyRelationship
This is the core class for this model, and effectively becomes an "instance" of a RoleTypeRelationship. 