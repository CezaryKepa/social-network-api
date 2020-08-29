package com.speakout.speakoutapi.comment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No comment with this id")
public class CommentNotFoundException extends RuntimeException{
}
