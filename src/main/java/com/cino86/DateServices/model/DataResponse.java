package com.cino86.DateServices.model;

import java.time.LocalDate;

public class DataResponse {

    private LocalDate dataCalcolata;

    public DataResponse(LocalDate dataCalcolata) {
        this.dataCalcolata = dataCalcolata;
    }

    public LocalDate getDataCalcolata() {
        return dataCalcolata;
    }

    public void setDataCalcolata(LocalDate dataCalcolata) {
        this.dataCalcolata = dataCalcolata;
    }
}
