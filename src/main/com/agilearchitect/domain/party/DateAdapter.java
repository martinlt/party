package com.agilearchitect.domain.party;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, LocalDate>
{
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

   @Override
   public LocalDate unmarshal(String xml) throws Exception
   {
      return LocalDate.parse(xml, formatter);
   }

   @Override
   public String marshal(LocalDate object) throws Exception
   {
      return object.format(formatter);
   }
}
