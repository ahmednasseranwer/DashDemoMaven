package com.SeedWork;

public class Enums {
	
	// Map index into type name used in filter and sort by any Field
	public static enum Type  { 
		firstName(0),lastName(1),title(2),phone(3),age(4),mail(5);
		public int value;  
		Type(int value)
		{  
			this.value=value;  
		} 
		public static String getValue(int index) 
		{
			return Type.values()[index-1].toString();
		}
		
		public static boolean checkType(String type)
		{
			if(type.equals("firstName")|| type.equals("lastName")
					||type.equals("title") || type.equals("phone") 
					|| type.equals("age") || type.equals("mail") )
			{
				return true;
			}
			return false;
		}
	}
	
}
