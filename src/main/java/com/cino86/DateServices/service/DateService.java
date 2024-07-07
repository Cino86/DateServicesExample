package com.cino86.DateServices.service;

import com.cino86.DateServices.exception.CustomException;
import com.cino86.DateServices.model.DataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class DateService {

    private static final Logger logger = LoggerFactory.getLogger(DateService.class);

    public LocalDate calcolaData(DataInput dataInput) {
        logger.info("Calcolo della data iniziato");
        Set<LocalDate> giorniFestivi = calcolaGiorniFestivi(dataInput.getCalendariFestivi());

        LocalDate dataCorrente = dataInput.getDataPartenza();
        int giorniAggiunti = 0;

        while (giorniAggiunti < Math.abs(dataInput.getDeltaGiorni())) {
            dataCorrente = dataCorrente.plusDays(dataInput.getDeltaGiorni() > 0 ? 1 : -1);

            if ((dataInput.getTipoDelta().equals("lavorativi") && !isGiornoLavorativo(dataCorrente, giorniFestivi)) ||
                (dataInput.getTipoDelta().equals("calendario") && giorniFestivi.contains(dataCorrente))) {
                continue;
            }

            giorniAggiunti++;
        }

        
        logger.info("Calcolo della data completato");
        return dataCorrente;
    }


    private boolean isGiornoLavorativo(LocalDate data, Set<LocalDate> giorniFestivi) {
        return !data.getDayOfWeek().equals(DayOfWeek.SATURDAY) &&
               !data.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
               !giorniFestivi.contains(data);
    }

    private Set<LocalDate> calcolaGiorniFestivi(List<String> calendariFestivi) {
        // Recupero i giorni festivi in base ai calendari forniti
        return Set.of(); // Placeholder per l'implementazione
    }
}
