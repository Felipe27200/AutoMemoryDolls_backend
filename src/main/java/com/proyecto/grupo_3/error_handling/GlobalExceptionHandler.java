package com.proyecto.grupo_3.error_handling;

import com.proyecto.grupo_3.error_handling.exception.NotFoundException;
import com.proyecto.grupo_3.error_handling.response.CustomErrorResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(TypeMismatchException ex)
    {
        String errorMessage = "";

        Class<?> requiredType = ex.getRequiredType();
        Object incorrectValue = ex.getValue();

        if (requiredType == null)
            errorMessage = String.format("El valor '$%s' no tiene el tipo de dato correcto", incorrectValue);
        else
            errorMessage = String.format("The value '%s' no es del tipo '%s'.", incorrectValue, requiredType);

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundErrors(Exception ex)
    {
        CustomErrorResponse errorResponse = new CustomErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Controlador de excepciones para la validacion de inputs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        List<String> errors = ex
            .getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());

        return new ResponseEntity<>(this.getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMismatchRequest(HttpMessageNotReadableException exc)
    {
        String errorMessage = exc.getMessage();

        CustomErrorResponse errorsResponse = new CustomErrorResponse();

        errorsResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorsResponse.setMessage(errorMessage);

        // Return an error response.
        return new ResponseEntity<>(errorsResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception ex)
    {
        CustomErrorResponse errorResponse = new CustomErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();

        // Se define la llave y el valor
        errorResponse.put("errors", errors);

        return errorResponse;
    }
}
