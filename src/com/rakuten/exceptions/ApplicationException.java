package com.rakuten.exceptions;

public class ApplicationException extends RuntimeException{
public ApplicationException(){
	
}
public ApplicationException(String msg){
	super(msg);
}
}
