
package com.recipe.sharing.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler({UserException.class, RecipeException.class})
	    public ResponseEntity<Object> handleUserAndRecipeExceptions(Exception ex, WebRequest request) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("status", HttpStatus.CONFLICT.value());
	        body.put("error", "Conflict");
	        body.put("message", ex.getMessage());
	        body.put("path", request.getDescription(false).replace("uri=", ""));
	        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	    }

	    @ExceptionHandler(NullPointerException.class)
	    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("status", HttpStatus.BAD_REQUEST.value());
	        body.put("error", "Bad request");
	        body.put("message", "Null pointer exception occurred");
	        body.put("path", request.getDescription(false).replace("uri=", ""));
	        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("status", HttpStatus.BAD_REQUEST.value());
	        body.put("error", "Bad Request");
	        body.put("message", ex.getMessage());
	        body.put("path", request.getDescription(false).replace("uri=", ""));
	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handleAllOtherExceptions(Exception ex, WebRequest request) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        body.put("error", "Internal Server Error");
	        body.put("message", "An unexpected error occurred");
	        body.put("path", request.getDescription(false).replace("uri=", ""));
	        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}