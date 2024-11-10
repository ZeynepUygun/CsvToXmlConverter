package com.magarsus.fileconverter.controller.advice;

import com.magarsus.fileconverter.constants.Messages;
import com.magarsus.fileconverter.exception.CsvToXmlGeneralException;
import com.magarsus.fileconverter.exception.ExceptionResponse;
import com.magarsus.fileconverter.exception.ReadProcessesException;
import com.magarsus.fileconverter.exception.WriteProcessesException;
import freemarker.template.TemplateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(
            {
                    IOException.class,
                    RuntimeException.class,
                    TemplateException.class,
                    Exception.class
            })
    public ResponseEntity<ExceptionResponse> handleIOException() {
        return ResponseEntity.ok(new ExceptionResponse(Messages.UNEXPECTED_ERROR, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ReadProcessesException.class)
    public ResponseEntity<ExceptionResponse> handleCsvException(ReadProcessesException exception) {
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(WriteProcessesException.class)
    public ResponseEntity<ExceptionResponse> handleCsvException(WriteProcessesException exception) {
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(CsvToXmlGeneralException.class)
    public ResponseEntity<ExceptionResponse> handleCsvException(CsvToXmlGeneralException exception) {
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }


}