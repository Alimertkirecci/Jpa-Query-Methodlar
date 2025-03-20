package com.example.config;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

//Hata Çeşitleri Console Den görülür buna göre global hata yakalayıcı yazılır.
@RestControllerAdvice
public class GlobalException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)//hatayı yakalayan method
    //MethodArgumentNotValidException bu hata doğduğunda geriye dönecek yapıdır.
    public Object methodArgumentNotValid(MethodArgumentNotValidException ex) {
        return parseError(ex.getFieldErrors());
    }
    private Set parseError(List<FieldError> fieldErrors) {
        Set<Map<String, Object>> ls = new LinkedHashSet();
        for (FieldError fieldError : fieldErrors) {
            Map<String, Object> map = new HashMap();
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            Object rejectedValue = fieldError.getRejectedValue();
            map.put("field", field);
            map.put("message", message);
            map.put("rejectedValue", rejectedValue);
            ls.add(map);
        }
        return ls;

    }
    //Save all deki dizilerin boş gelmesi ve benzeri durumları yakalatacağız.
    //Genel geçer hata ve mistik durumları.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map map =new LinkedHashMap();
        map.put("status",false);
        map.put("message",ex.getMessage());
        return map;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)//dinler hatayı yapau zeka söyler
    public Map MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map map =new LinkedHashMap();
        map.put("status",false);
        map.put("message",ex.getMessage());
        return map;

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)//dinler hatayı yapau zeka söyler
    public Map MethodArgumentTypeMismatchException(ConstraintViolationException ex) {
        Map map =new LinkedHashMap();
        map.put("status",false);
        map.put("message",ex.getMessage());
        return map;

    }

}