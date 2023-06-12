//package org.mingchencodelab.blogbackendspringboot.exception;
//
//import org.mingchencodelab.blogbackendspringboot.model.payload.response.CustomErrorResponse;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.LocalDateTime;
//
//@ControllerAdvice
//public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<CustomErrorResponse> handleDataIntegrityViolationException(
//            DataIntegrityViolationException ex, WebRequest request) {
//        CustomErrorResponse errorResponse = new CustomErrorResponse();
//        errorResponse.setTimestamp(LocalDateTime.now().toString());
//        errorResponse.setStatus(HttpStatus.CONFLICT.value());
//        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
//        errorResponse.setMessage("Custom error message here");
//        errorResponse.setPath(request.getDescription(false));
//
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
//    }
//}
//
