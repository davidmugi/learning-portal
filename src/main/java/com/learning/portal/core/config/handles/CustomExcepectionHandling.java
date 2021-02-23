package com.learning.portal.core.config.handles;

import com.learning.portal.api.data.AuthenticationResponse;
import com.learning.portal.api.data.ResponseModel;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomExcepectionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    protected ResponseEntity<Object> handle(BadCredentialsException badCredentialsException){
        AuthenticationResponse response = new AuthenticationResponse();
        response.setMessage(badCredentialsException.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<Object> handle(CustomException exception){
        var code = (exception.getCode() == 0) ? 400 : exception.getCode();

        ResponseModel responseModel =
                new ResponseModel("01", exception.getMessage());

        return ResponseEntity.status(code).body(responseModel);
    }

    @ExceptionHandler(value = IOException.class)
    protected ResponseEntity<Object> handleIoException(IOException ioException){
        var code = HttpStatus.BAD_REQUEST.value();

        ResponseModel responseModel =
                new ResponseModel("01",ioException.getMessage());
        return ResponseEntity.status(code).body(responseModel);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        ResponseModel response = new ResponseModel("01", builder.toString(), null);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(response);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handle(Exception exception) {
        exception.printStackTrace();
        var code = 400;
        ResponseModel response =
                new ResponseModel(
                        "01",
                        "An error occurred processing your request. Please try again later or contact the admin",
                        null);
        return ResponseEntity.status(code).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ResponseModel error = new ResponseModel("01","Validation Error", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ex.printStackTrace();
        ResponseModel error = new ResponseModel("01", "Required request body is missing!", null);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Overriden to customize the response sent back to the client for 'page not found' errors.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseModel error = new ResponseModel("01", "page not found", null);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }


}
