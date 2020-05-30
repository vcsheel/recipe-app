package com.learn.spring.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException (Exception exception) {
        log.error("........Handle bad request exception.....");
        log.error(exception.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", HttpStatus.BAD_REQUEST.value());
        mav.addObject("reason", HttpStatus.BAD_REQUEST.getReasonPhrase());
        mav.addObject("exception", exception);
        mav.setViewName("errorpage");
        return mav;
    }
}
