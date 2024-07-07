package com.cino86.DateServices.controller;

import com.cino86.DateServices.exception.CustomException;
import com.cino86.DateServices.model.DataInput;
import com.cino86.DateServices.model.DataResponse;
import com.cino86.DateServices.service.DateService;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/date")
public class DateController {

    private final DateService dateService;

    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @PostMapping("/calcolaData")
    public ResponseEntity<?> calcolaData(@RequestBody DataInput dataInput) {
         try {
            LocalDate dataCalcolata = dateService.calcolaData(dataInput);
            DataResponse response = new DataResponse(dataCalcolata);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(new DataResponse(null));
        }
    }
}
