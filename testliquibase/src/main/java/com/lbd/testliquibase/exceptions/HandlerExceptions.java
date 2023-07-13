package com.lbd.testliquibase.exceptions;

import com.lbd.testliquibase.domain.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(value= MyException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorDTO handleMyException(MyException exception) {

        return ErrorDTO.builder().code(exception.getStatusCode()).description(exception.getMessage()).build();
    }

    @ExceptionHandler(value= MyException2.class)
    @ResponseStatus(code = HttpStatus.FAILED_DEPENDENCY)
    public @ResponseBody ErrorDTO handleMyException2(MyException2 exception) {

        return ErrorDTO.builder().code(exception.getStatusCode()).description(exception.getMessage()).build();
    }
}
