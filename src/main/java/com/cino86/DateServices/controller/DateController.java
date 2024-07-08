package com.cino86.DateServices.controller;

import com.cino86.DateServices.exception.CustomException;
import com.cino86.DateServices.model.DataInput;
import com.cino86.DateServices.model.DataResponse;
import com.cino86.DateServices.service.DateService;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<ResponseEntity<DataResponse>> calcolaData(@RequestBody DataInput dataInput) {
        return dateService.calcolaData(dataInput)
            .thenApply(dataCalcolata -> ResponseEntity.ok(new DataResponse(dataCalcolata)))
            .exceptionally(ex -> ResponseEntity.badRequest().body(new DataResponse(null)));
    }
}
