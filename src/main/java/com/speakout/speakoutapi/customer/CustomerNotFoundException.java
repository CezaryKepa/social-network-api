package com.speakout.speakoutapi.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No customer with this username")
public class CustomerNotFoundException extends RuntimeException {
}
