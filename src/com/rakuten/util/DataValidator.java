package com.rakuten.util;

import java.util.Date;

public class DataValidator  {

	public static boolean isNull(String val){
		if(val==null||val.trim().length()==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	
public static boolean isNotNull(String val){
	return! isNull(val);
	
}

public static boolean isInteger(String val){
	if(isNotNull(val)){
		
		try{
			
			int i = Integer.parseInt(val);
			  return true;
		}
		
		catch(NumberFormatException e){
			return false;
		}
	}
	else{
		return false;
	}
	
}

public static boolean isLong(String val){
	if(isNotNull(val)){
		
		try{
			
			Long i = Long.parseLong(val);
			return true;
		}
		
		catch(NumberFormatException e){
			return false;
		}
		
	}
	else{
		return false;
	}
}

public static boolean isEmail(String val){

	String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	if(isNotNull(val)){
		
		try{
			return val.matches(emailreg);
		}
		
		catch(NumberFormatException e){
			return false;
		}
	}
	
	else{
		
		return false;
	}
}

//public static boolean isDate(String val){
//	
//	Date d = null;
//	if(isNotNull(val)){
//		
//		d = DataUtility.getDate(val); 
//	}
//	
//	return d!= null;
//}

public static boolean isFloat(String val){
	if(isNotNull(val)){
		
		try{
			
			float i = Float.parseFloat(val);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
		
	}
		else{
			return false;
		}
	}
  public static boolean isDouble(String val){
	  if(isNotNull(val)){
		  
		  try{
			  
			  double i = Double.parseDouble(val);
			  return true;
		  }
		  
		  catch(NumberFormatException e){
			  return false;
		  }
	  }
	  else{
		  return false;
	  }
	  
  }
  
  public static boolean isBoolean(String val){
	  if(isNotNull(val)){
		  
		  try{
			  
			  boolean i = Boolean.parseBoolean(val);
			  return true;
		  }
		  
		  catch(StringIndexOutOfBoundsException se){
			  return false;
		  }
	  }
	  
	  else{
		  
		  return false;
	  }
  }

public static void main(String [] args){
	System.out.println("abc String is null =" + isNull("abc"));
	System.out.println("Null String is null =" + isNull(null));
	System.out.println("Blank String is null =" + isNull(""));
	System.out.println("Space String is null =" + isNull(" "));
	
	System.out.println("---------------------------------------------------");
	System.out.println("abc String is not null =" + isNotNull("abc"));
	System.out.println("Null String is not null =" + isNotNull(null));
	System.out.println("Blank String is not null =" + isNotNull(""));
	System.out.println("Space String is not null =" + isNotNull(" "));
	
	System.out.println("-----------------------------------------------------");
	System.out.println("abc String is integer =" + isInteger("abc"));
	System.out.println("Null String is integer =" + isInteger(null));
	System.out.println("Blank String is integer =" + isInteger(""));
	System.out.println("Space String is integer =" + isInteger(" "));
	System.out.println("123 String is integer =" + isInteger("123"));
	System.out.println("6.8 String is integer =" + isInteger("6.8"));
	
	
	System.out.println("------------------------------------------------------");
	System.out.println("abc String is long =" + isLong("abc"));
	System.out.println("Null String is long =" + isLong(null));
	System.out.println("Blank String is long =" + isLong(""));
	System.out.println("Space String is long =" + isLong(" "));
	System.out.println("123 String is long =" + isLong("123"));
	System.out.println("5.6 String is long =" + isLong("3.89"));
	
	System.out.println("--------------------------------------------------------");
	System.out.println("abc String is Float =" + isFloat("abc"));
	System.out.println("Null String is Float =" + isFloat(null));
	System.out.println("Blank String is Float =" + isFloat(""));
	System.out.println("Space String is Float =" + isFloat(" "));
	System.out.println("123 String is Float =" + isFloat("123"));
	System.out.println("5.3 String is Float =" + isFloat("5.2"));
	
    System.out.println("----------------------------------------------------------");
    System.out.println("abc String is Double =" + isDouble("abc"));
    System.out.println("Null String is Double =" + isDouble(null));
    System.out.println("Blank String is Double  =" + isDouble(""));
    System.out.println("Space String is Double =" + isDouble(" "));
    System.out.println("123 String is Double =" + isDouble("123"));
    System.out.println("3.2 String is Double =" + isDouble("5.3"));
    
    System.out.println("-----------------------------------------------------------");
    System.out.println("abc String is Boolean =" + isBoolean("abc"));
    System.out.println("Null String is Boolean =" + isBoolean(null));
    System.out.println("Blank String is Boolean =" + isBoolean(""));
    System.out.println("Space String is Boolean =" + isBoolean(" "));
    System.out.println("123 String is Boolean =" + isBoolean("123"));
    System.out.println("3.2 String is Boolean =" + isBoolean("5.324"));
    System.out.println(" true String is Boolean =" + isBoolean("true"));
    System.out.println(" false String is Boolean =" + isBoolean("false"));
}
	
	
}
