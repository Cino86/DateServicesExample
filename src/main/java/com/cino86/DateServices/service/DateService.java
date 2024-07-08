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
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DateService {

    private static final Logger logger = LoggerFactory.getLogger(DateService.class);

    public CompletableFuture<LocalDate> calcolaData(DataInput dataInput) {
        logger.info("Calcolo della data iniziato");

        return calcolaGiorniFestivi(dataInput.getCalendariFestivi())
            .thenApply(giorniFestivi -> {
                LocalDate dataCorrente = dataInput.getDataPartenza();
                int giorniAggiunti = 0;
                Predicate<LocalDate> isGiornoValido = getGiornoValidoPredicate(dataInput, giorniFestivi);

                while (giorniAggiunti < Math.abs(dataInput.getDeltaGiorni())) {
                    dataCorrente = dataCorrente.plusDays(dataInput.getDeltaGiorni() > 0 ? 1 : -1);
                    if (isGiornoValido.test(dataCorrente)) {
                        giorniAggiunti++;
                    }
                }

                logger.info("Calcolo della data completato");
                return dataCorrente;
            });
    }

    private Predicate<LocalDate> getGiornoValidoPredicate(DataInput dataInput, Set<LocalDate> giorniFestivi) {
        if (!"lavorativi".equals(dataInput.getTipoDelta()) && !"calendario".equals(dataInput.getTipoDelta())) {
            String errorMessage = "tipoDelta deve essere \"lavorativi\" o \"calendario\". Ricevuto: " + dataInput.getTipoDelta();
            logger.error(errorMessage);
            throw new CustomException(errorMessage);
        }

        return dataCorrente -> {
            boolean isCalendario = "calendario".equals(dataInput.getTipoDelta());
            boolean isLavorativi = "lavorativi".equals(dataInput.getTipoDelta());
            boolean isWeekend = dataCorrente.getDayOfWeek() == DayOfWeek.SATURDAY || dataCorrente.getDayOfWeek() == DayOfWeek.SUNDAY;
            boolean isFestivo = giorniFestivi.contains(dataCorrente);
        
            return (isCalendario && !isFestivo) || (isLavorativi && !isWeekend && !isFestivo);
        };
    }

    private CompletableFuture<Set<LocalDate>> calcolaGiorniFestivi(List<String> calendariFestivi) {
        // Simulazione di un'operazione asincrona per recuperare i giorni festivi
        return CompletableFuture.completedFuture(Set.of());
    }
}
