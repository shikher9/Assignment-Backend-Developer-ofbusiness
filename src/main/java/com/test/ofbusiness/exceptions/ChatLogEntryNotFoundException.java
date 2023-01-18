package com.test.ofbusiness.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Chatlog not found")
public class ChatLogEntryNotFoundException extends RuntimeException {

}
