package com.speakout.speakoutapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No user with this username")
public class UserNotFoundException extends RuntimeException{
}
