package com.speakout.speakoutapi.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No post with this id")
public class PostNotFoundException extends RuntimeException {
}
