package com.rakuten.exceptions;

public class NoRecordFoundException extends RuntimeException{
public NoRecordFoundException(){
	
} 
public NoRecordFoundException(String msg){
	super(msg);
}
}
