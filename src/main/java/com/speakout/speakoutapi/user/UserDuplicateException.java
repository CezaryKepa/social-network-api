package com.speakout.speakoutapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Użytkownik z takim mailem już istnieje")
class UserDuplicateException extends RuntimeException {

}