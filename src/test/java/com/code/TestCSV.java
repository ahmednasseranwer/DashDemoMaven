package com.code;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Persons.Person;
import com.PersonsOpertions.PersonCSVFile;
import com.PersonsOpertions.PersonJsonFile;
import com.PersonsOpertions.PersonsOpertions;
import com.SeedWork.Enums;
import java.util.List;

public class TestCSV 
{
	
	protected PersonsOpertions persons_opertions = new PersonCSVFile();
	
	public Person[] expected_person ;
 	
	@Before
	public void setup() 
	{		
		expected_person = new Person[8];	
		expected_person[0] = new Person("Ahmed","Nasser","Developer","01141612615","24","ahmednasser@gmail");
		expected_person[1] = new Person("Hossam","Hassan","Player","012","41","mossamhassan@gmail");
		expected_person[2] = new Person("Khaled","Hassan","Banker","012","41","mossamhassan@gmail");		
		expected_person[3] = new Person("Khaled","Osama","Doctor","011232434453","24","khaledOsama@gmail.com");
		expected_person[4] = new Person("Khaled","Ahmed","Doctor","011111111111","24","KhaledAhmed@gmail.com");
		expected_person[5] = new Person("Samy","Ali","engineer","01234567","24","samyali.com");		
		expected_person[6] = new Person("Khaled","Said","Doctor","010","21","khaledsaid@gmail");		
	    expected_person[7] = new Person("Mohamed","Salah","Player","013","21","mohamedsalah@gmail");
	}

	public Person[] ToArray(List<Person> list) 
	{		
		return (Person[]) list.toArray(new Person[list.size()]);
	}
	
	public boolean CheckEquality(Person P1[],Person P2[])
	{
		for(int i=0;i<P1.length;i++)
		{
			if(	!P1[i].getFirstName().equals(P2[i].getFirstName()) ||
				!P1[i].getLastName().equals(P2[i].getLastName()) ||
				!P1[i].getTitle().equals(P2[i].getTitle()) ||
				!P1[i].getPhone().equals(P2[i].getPhone()) ||
				!P1[i].getAge().equals(P2[i].getAge()) ||
				!P1[i].getMail().equals(P2[i].getMail()) )
				{
					return false;
				}
		}
		return true;
	}
	
	// Notes
	//---------------------------
	//"CSVFile.csv" should be empty before run
	
	@Test
	public void testProposedFile() throws JsonParseException, JsonMappingException, IOException, ParseException 
	{
		System.out.println("-------------------------CSVFILE--------------------------");

		System.out.println("1-[[List All Persons]]");		
		assertNull(persons_opertions.ListPerson());

		System.out.println("2-[[Delete Specific Person]]  selectedMail = \"ahmedali@gmailcom\" ");		
		assertNull(persons_opertions.DeletePerson("ahmedali@gmailcom"));
		
		System.out.println("3-[[Filter by any Field]] field= \"mail\" value= \"Player\" ");
		assertNull(persons_opertions.FilterByAnyField("mail", "Player"));
		
		System.out.println("4-[[Sort on any Field]] selectedField= \"age");
		assertNull(persons_opertions.SortOnAnyField("age"));

		System.out.println("5-[[Update Person's information]] selectedMail=\"ahmednasser@gmail , updatedPerson= \"Ahmed\",\"Nasser\",\"Developer\",\"01141612615\",\"24\",\"ahmednasser@gmail\"");
		assertNull(persons_opertions.UpdatePersonInformation("ahmednasser@gmail",expected_person[0]));
		
		System.out.println("6-[[Add Person]] newPerson=\"Ahmed\",\"Nasser\",\"Developer\",\"01141612615\",\"24\",\"ahmednasser@gmail\" ");
		assertEquals(expected_person[0],persons_opertions.AddPerson(expected_person[0]));
		
		System.out.println("7-[[Add Person]] \"Hossam\",\"Hassan\",\"Player\",\"012\",\"41\",\"mossamhassan@gmail\"");
		assertEquals(expected_person[1],persons_opertions.AddPerson(expected_person[1]));
		
		System.out.println("8-[[Add Person]] \"Khaled\",\"Hassan\",\"Banker\",\"012\",\"41\",\"mossamhassan@gmail\"");
		assertNull(persons_opertions.AddPerson(expected_person[2]));	
			
		System.out.println("9-[[List All Persons]]");	
		assertTrue(CheckEquality(new Person[]{expected_person[0],expected_person[1]},ToArray(persons_opertions.ListPerson())));
		
		System.out.println("10-[[Delete Specific Person]] selectedMail= \"khaledsalah@gmail\" ");
		assertEquals(null,persons_opertions.DeletePerson("khaledsalah@gmail"));
		
		System.out.println("11-[[Delete Specific Person]] selectedMail= \"mossamhassan@gmail\"");
		assertEquals("mossamhassan@gmail",persons_opertions.DeletePerson("mossamhassan@gmail"));	
				
		System.out.println("12-[[List All Persons]]"); 
		assertTrue(CheckEquality(new Person[]{expected_person[0]},ToArray(persons_opertions.ListPerson())));

		System.out.println("13-[[Delete Specific Person]] selectedMail= \"ahmednasser@gmail\"");
		assertEquals("ahmednasser@gmail",persons_opertions.DeletePerson("ahmednasser@gmail"));	
		
		System.out.println("14-[[List All Persons]]");
		assertNull(persons_opertions.ListPerson());
		
		System.out.println("15-[[Add Person]] newPerson= \"Khaled\",\"Osama\",\"Doctor\",\"011232434453\",\"24\",\"khaledOsama@gmail.com\""); 
		assertEquals(expected_person[3],persons_opertions.AddPerson(expected_person[3]));
		
		System.out.println("16-[[Update Person's information]] selectedMail=\"khaledOsama@gmail.com\" updatedPerson= \"Khaled\",\"Ahmed\",\"Doctor\",\"011111111111\",\"24\",\"KhaledAhmed@gmail.com\""); 
		assertEquals(expected_person[4],persons_opertions.UpdatePersonInformation("khaledOsama@gmail.com",expected_person[4]));	

		System.out.println("17-[[Update Person's information]] selectedMail= \"SamyAli@gmail.com\" , updatedPerson= \"Samy\",\"Ali\",\"engineer\",\"01234567\",\"24\",\"samyali.com\"");
		assertNull(persons_opertions.UpdatePersonInformation("SamyAli@gmail.com",expected_person[5]));

		System.out.println("18-[[Delete Specific Person]] selectedMail= \"KhaledAhmed@gmail.com\" ");
		assertEquals("KhaledAhmed@gmail.com",persons_opertions.DeletePerson("KhaledAhmed@gmail.com"));	
		
		System.out.println("19-[[Update Person's information]] selectedMail= \"AhmedNasser@gmail.com\" ,upadtedPerson =\"Ahmed\",\"Nasser\",\"Developer\",\"01141612615\",\"24\",\"ahmednasser@gmail\"");
		assertNull(persons_opertions.UpdatePersonInformation("AhmedNasser@gmail.com",expected_person[0]));
		
		System.out.println("20-[[Sort on any Field]] selectedField= \"firstName\" ");
		assertNull(persons_opertions.SortOnAnyField("firstName"));

		System.out.println("21-[[Filter by any Field]] field= \"firstName\" , value=\"ali\" "); 
		assertNull(persons_opertions.FilterByAnyField("firstName", "ali"));
		
		System.out.println("22-[[Add Person]] newPerson=\"Ahmed\",\"Nasser\",\"Developer\",\"01141612615\",\"24\",\"ahmednasser@gmail\"");
		assertEquals(expected_person[0],persons_opertions.AddPerson(expected_person[0]));
		
		System.out.println("23-[[Add Person]] newPerson= \"Hossam\",\"Hassan\",\"Player\",\"012\",\"41\",\"mossamhassan@gmail\"");
		assertEquals(expected_person[1],persons_opertions.AddPerson(expected_person[1]));		
		
		System.out.println("24-[[Add Person]] newPerson= \"Khaled\",\"Said\",\"Doctor\",\"010\",\"21\",\"khaledsaid@gmail\"");
		assertEquals(expected_person[6],persons_opertions.AddPerson(expected_person[6]));		
		
		System.out.println("25-[[Sort on any Field]] selectedField= \"location\"");
		assertNull(persons_opertions.SortOnAnyField("location"));
		
		System.out.println("26-[[Sort on any Field]] selectedField= \"age\"");
		assertTrue(CheckEquality(new Person[] {expected_person[6],expected_person[0],expected_person[1]},
				   ToArray(persons_opertions.SortOnAnyField("age"))));

		System.out.println("27-[[Add Person]] newPerson= \"Mohamed\",\"Salah\",\"Player\",\"013\",\"21\",\"mohamedsalah@gmail\""); 
		assertEquals(expected_person[7],persons_opertions.AddPerson(expected_person[7]));
		
		System.out.println("28-[[Filter by any Field]] field=\"title\" ,value=\"Player\"");
		assertTrue(CheckEquality(new Person[] {expected_person[1],expected_person[7]},
				   ToArray(persons_opertions.FilterByAnyField("title","Player"))));
		
		System.out.println("29-[[Filter by any Field]] field=\"title\", value=\"Officer\"");
		assertNull(persons_opertions.FilterByAnyField("title","Officer"));
		
	}

}
