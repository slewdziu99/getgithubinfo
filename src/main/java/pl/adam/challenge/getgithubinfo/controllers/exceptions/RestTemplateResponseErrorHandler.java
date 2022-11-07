package pl.adam.challenge.getgithubinfo.controllers.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestTemplateResponseErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorJsonObject jsonObject = new ErrorJsonObject(HttpStatus.NOT_ACCEPTABLE.toString(),ex.getMessage());
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(mapper.writeValueAsString(jsonObject),HttpStatus.NOT_ACCEPTABLE );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return responseEntity;
    }
}
