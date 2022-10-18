package com.rakuten.exceptions;

public class DuplicateRecordException extends RuntimeException{
public DuplicateRecordException(){
	
}
public DuplicateRecordException(String msg){
	super(msg);
}
}
