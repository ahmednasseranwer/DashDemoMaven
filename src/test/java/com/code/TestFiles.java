package com.code;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.Persons.Person;
import com.PersonsOpertions.PersonCSVFile;
import com.PersonsOpertions.PersonJsonFile;
import com.PersonsOpertions.PersonsOpertions;


public class TestFiles {

	protected PersonsOpertions personsOpertionsCSV = new PersonCSVFile();
	protected PersonsOpertions personsOpertionsJSON = new PersonJsonFile();
	
	public List<Person> expected_person ; 	
		
	@Before
	public void setUp() 
	{		
		expected_person = new ArrayList();	
	
	/*0*/	expected_person.add(new Person("Ahmed","Nasser","Developer","01141612615","24","ahmednasser@gmail"));
	/*1*/	expected_person.add(new Person("Hossam","Hassan","Player","012","41","mossamhassan@gmail"));
	/*2*/	expected_person.add(new Person("Khaled","Hassan","Banker","012","41","mossamhassan@gmail"));		
	/*3*/	expected_person.add(new Person("Khaled","Osama","Doctor","011232434453","24","khaledOsama@gmail.com"));
	/*4*/	expected_person.add(new Person("Khaled","Ahmed","Doctor","011111111111","24","KhaledAhmed@gmail.com"));
	/*5*/	expected_person.add(new Person("Samy","Ali","engineer","01234567","24","samyali.com"));		
	/*6*/	expected_person.add(new Person("Khaled","Said","Doctor","010","21","khaledsaid@gmail"));		
	/*7*/   expected_person.add(new Person("Mohamed","Salah","Player","013","21","mohamedsalah@gmail"));
	
	}
	public boolean checkEquality(List<Person> P1,List<Person> P2)
	{
		if(P1.size()!=P2.size())
			return false;
		for(int i=0;i<P1.size();i++)
		{
			if(	!P1.get(i).getFirstName().equals(P2.get(i).getFirstName()) ||
				!P1.get(i).getLastName().equals(P2.get(i).getLastName()) ||
				!P1.get(i).getTitle().equals(P2.get(i).getTitle()) ||
				!P1.get(i).getPhone().equals(P2.get(i).getPhone()) ||
				!P1.get(i).getAge().equals(P2.get(i).getAge()) ||
				!P1.get(i).getMail().equals(P2.get(i).getMail()) )
				{
					return false;
				}
		}
		return true;
	}
	public List<Person> listPersonTest() throws JsonParseException, JsonMappingException, IOException
	{
		System.out.println("\nCSV");
		List<Person> outputCsv = personsOpertionsCSV.ListPerson();
		System.out.println("JSON");
		List<Person> outputJson = personsOpertionsJSON.ListPerson();
		
		if(outputCsv==null && outputJson==null)
			return null;
		
		return checkEquality(outputCsv, outputJson) ? outputJson : null ;		
	}	
	public String deletePersonTest(String selectedMail) throws JsonParseException, JsonMappingException, IOException
	{
		System.out.println("\nCSV");
		String outputCsv = personsOpertionsCSV.DeletePerson(selectedMail);
		System.out.println("JSON");
		String outputJson = personsOpertionsJSON.DeletePerson(selectedMail);
		
		if(outputCsv==null && outputJson==null)
			return null;
		
		return outputCsv.equals(outputJson)? outputJson : null ;		
	}
	public List<Person> filterByAnyFieldTest(String filterField, String filterValue) throws ParseException, IOException
	{
		System.out.println("\nCSV");
		List<Person> outputCsv = personsOpertionsCSV.FilterByAnyField(filterField, filterValue);
		System.out.println("JSON");
		List<Person> outputJson = personsOpertionsJSON.FilterByAnyField(filterField, filterValue);

		if(outputCsv==null && outputJson==null)
			return null;

		return checkEquality(outputCsv,outputJson)? outputJson : null ;		
	}
	public List<Person> sortOnAnyFieldTest(String selectedField) throws JsonParseException, JsonMappingException, IOException
	{
		System.out.println("\nCSV");
		List<Person> outputCsv = personsOpertionsCSV.SortOnAnyField(selectedField);
		System.out.println("JSON");
		List<Person> outputJson = personsOpertionsJSON.SortOnAnyField(selectedField);

		if(outputCsv==null && outputJson==null)
			return null;
		
		return checkEquality(outputCsv,outputJson)? outputJson : null ;		
	}
	public Person updatePersonInformationTest(String selectedMail, Person updatedPerson) throws JsonParseException, JsonMappingException, IOException
	{
		System.out.println("\nCSV");
		Person outputCsv = personsOpertionsCSV.UpdatePersonInformation(selectedMail, updatedPerson);
		System.out.println("JSON");
		Person outputJson = personsOpertionsJSON.UpdatePersonInformation(selectedMail, updatedPerson);

		if(outputCsv==null && outputJson==null)
			return null;
		
		return outputCsv.equals(outputJson)? outputJson : null ;		
	}	
	public Person addPersonTest(Person newPerson) throws JsonParseException, JsonMappingException, IOException
	{
		System.out.println("\nCSV");
		Person outputCsv = personsOpertionsCSV.AddPerson(newPerson);
		System.out.println("JSON");
		Person outputJson = personsOpertionsJSON.AddPerson(newPerson);

		if(outputCsv==null && outputJson==null)
			return null;
		
		return outputCsv.equals(outputJson) ? outputJson : null ;		
	}
	
	@Test
	public void testFile() throws JsonParseException, JsonMappingException, IOException, ParseException
	{
		System.out.println("1-[[List All Persons]]");		
		assertNull(listPersonTest());
	
		System.out.println("2-[[Delete Specific Person]]  selectedMail = \"ahmedali@gmailcom\" ");		
		assertNull(deletePersonTest("ahmedali@gmailcom"));
		
		System.out.println("3-[[Filter by any Field]] field= \"mail\" value= \"Player\" ");
		assertNull(filterByAnyFieldTest("mail", "Player"));
	
		System.out.println("4-[[Sort on any Field]] selectedField= \"age");
		assertNull(sortOnAnyFieldTest("age"));
	
		System.out.println("5-[[Update Person's information]] selectedMail=\"ahmednasser@gmail , updatedPerson= \"Ahmed\",\"Nasser\",\"Developer\",\"01141612615\",\"24\",\"ahmednasser@gmail\"");
		assertNull(updatePersonInformationTest("ahmednasser@gmail",expected_person.get(0)));
		
		System.out.println("6-[[Add Person]] newPerson=\"Ahmed\",\"Nasser\",\"Developer\",\"01141612615\",\"24\",\"ahmednasser@gmail\" ");
		assertEquals(expected_person.get(0),addPersonTest(expected_person.get(0)));	
			
		System.out.println("7-[[Add Person]] \"Hossam\",\"Hassan\",\"Player\",\"012\",\"41\",\"mossamhassan@gmail\"");
		assertEquals(expected_person.get(1),addPersonTest(expected_person.get(1)));
		
		System.out.println("8-[[Add Person]] \"Khaled\",\"Hassan\",\"Banker\",\"012\",\"41\",\"mossamhassan@gmail\"");
		assertNull(addPersonTest(expected_person.get(2)));	
			
		System.out.println("9-[[List All Persons]]");	
		assertTrue(checkEquality(expected_person.subList(0, 2),listPersonTest()));
	
		System.out.println("10-[[Delete Specific Person]] selectedMail= \"khaledsalah@gmail\" ");
		assertNull(deletePersonTest("khaledsalah@gmail"));
		
		System.out.println("11-[[Delete Specific Person]] selectedMail= \"mossamhassan@gmail\"");
		assertEquals("mossamhassan@gmail",deletePersonTest("mossamhassan@gmail"));	
				
		System.out.println("12-[[List All Persons]]"); 
		assertTrue(checkEquality(expected_person.subList(0,1),listPersonTest()));
	
		System.out.println("13-[[Delete Specific Person]] selectedMail= \"ahmednasser@gmail\"");
		assertEquals("ahmednasser@gmail",deletePersonTest("ahmednasser@gmail"));	
		
		System.out.println("14-[[List All Persons]]");
		assertNull(listPersonTest());
		
		System.out.println("15-[[Add Person]] newPerson= \"Khaled\",\"Osama\",\"Doctor\",\"011232434453\",\"24\",\"khaledOsama@gmail.com\""); 
		assertEquals(expected_person.get(3),addPersonTest(expected_person.get(3)));
		
		System.out.println("16-[[Update Person's information]] selectedMail=\"khaledOsama@gmail.com\" updatedPerson= \"Khaled\",\"Ahmed\",\"Doctor\",\"011111111111\",\"24\",\"KhaledAhmed@gmail.com\""); 
		assertEquals(expected_person.get(4),updatePersonInformationTest("khaledOsama@gmail.com",expected_person.get(4)));	
	
		System.out.println("17-[[Update Person's information]] selectedMail= \"SamyAli@gmail.com\" , updatedPerson= \"Samy\",\"Ali\",\"engineer\",\"01234567\",\"24\",\"samyali.com\"");
		assertNull(updatePersonInformationTest("SamyAli@gmail.com",expected_person.get(5)));
	
		System.out.println("18-[[Delete Specific Person]] selectedMail= \"KhaledAhmed@gmail.com\" ");
		assertEquals("KhaledAhmed@gmail.com",deletePersonTest("KhaledAhmed@gmail.com"));	
		
		System.out.println("19-[[Update Person's information]] selectedMail= \"AhmedNasser@gmail.com\" ,upadtedPerson =\"Ahmed\",\"Nasser\",\"Developer\",\"01141612615\",\"24\",\"ahmednasser@gmail\"");
		assertNull(updatePersonInformationTest("AhmedNasser@gmail.com",expected_person.get(0)));
		
		System.out.println("20-[[Sort on any Field]] selectedField= \"firstName\" ");
		assertNull(sortOnAnyFieldTest("firstName"));
	
		System.out.println("21-[[Filter by any Field]] field= \"firstName\" , value=\"ali\" "); 
		assertNull(filterByAnyFieldTest("firstName", "ali"));
		
		System.out.println("22-[[Add Person]] newPerson=\"Ahmed\",\"Nasser\",\"Developer\",\"01141612615\",\"24\",\"ahmednasser@gmail\"");
		assertEquals(expected_person.get(0),addPersonTest(expected_person.get(0)));
		
		System.out.println("23-[[Add Person]] newPerson= \"Hossam\",\"Hassan\",\"Player\",\"012\",\"41\",\"mossamhassan@gmail\"");
		assertEquals(expected_person.get(1),addPersonTest(expected_person.get(1)));		
		
		System.out.println("24-[[Add Person]] newPerson= \"Khaled\",\"Said\",\"Doctor\",\"010\",\"21\",\"khaledsaid@gmail\"");
		assertEquals(expected_person.get(6),addPersonTest(expected_person.get(6)));		
		
		System.out.println("25-[[Sort on any Field]] selectedField= \"location\"");
		assertNull(sortOnAnyFieldTest("location"));
		
		System.out.println("26-[[Sort on any Field]] selectedField= \"age\"");
		assertTrue(checkEquality(Arrays.asList( new Person[] {expected_person.get(6),expected_person.get(0),expected_person.get(1)}),sortOnAnyFieldTest("age")));
	
		System.out.println("27-[[Add Person]] newPerson= \"Mohamed\",\"Salah\",\"Player\",\"013\",\"21\",\"mohamedsalah@gmail\""); 
		assertEquals(expected_person.get(7),addPersonTest(expected_person.get(7)));
		
		System.out.println("28-[[Filter by any Field]] field=\"title\" ,value=\"Player\"");
		assertTrue(checkEquality(Arrays.asList(new Person[] {expected_person.get(1),expected_person.get(7)}),filterByAnyFieldTest("title","Player")));
		
		System.out.println("29-[[Filter by any Field]] field=\"title\", value=\"Officer\"");
		assertNull(filterByAnyFieldTest("title","Officer"));
	}
}
