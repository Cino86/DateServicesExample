package com.cino86.DateServices.model;

import java.time.LocalDate;
import java.util.List;

public class DataInput {
    private LocalDate dataPartenza;
    private int deltaGiorni;
    private String tipoDelta; // "lavorativi" o "calendario"
    private List<String> calendariFestivi;

    public DataInput(LocalDate dataPartenza, int deltaGiorni, String tipoDelta, List<String> calendariFestivi) {
        this.dataPartenza = dataPartenza;
        this.deltaGiorni = deltaGiorni;
        this.tipoDelta = tipoDelta;
        this.calendariFestivi = calendariFestivi;
    }
    public DataInput() {
    }
    public LocalDate getDataPartenza() {
        return dataPartenza;
    }
    public void setDataPartenza(LocalDate dataPartenza) {
        this.dataPartenza = dataPartenza;
    }
    public int getDeltaGiorni() {
        return deltaGiorni;
    }
    public void setDeltaGiorni(int deltaGiorni) {
        this.deltaGiorni = deltaGiorni;
    }
    public String getTipoDelta() {
        return tipoDelta;
    }
    public void setTipoDelta(String tipoDelta) {
        this.tipoDelta = tipoDelta;
    }
    public List<String> getCalendariFestivi() {
        return calendariFestivi;
    }
    public void setCalendariFestivi(List<String> calendariFestivi) {
        this.calendariFestivi = calendariFestivi;
    }
}
