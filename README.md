# Date Services Application

## Descrizione
Date Services è un'applicazione Spring Boot che fornisce API per calcolare date evitando giorni festivi. È progettata per essere semplice da usare e facilmente estendibile.

## Funzionalità
- Calcola una data aggiungendo o sottraendo un numero specificato di giorni lavorativi o di calendario.
- Evita automaticamente i giorni festivi basandosi su calendari configurabili.

## Tecnologie Utilizzate
- Spring Boot
- Java
- Maven


## Uso
Per utilizzare l'API, inviare una richiesta POST all'endpoint `/api/date/calcolaData` con un payload JSON che specifica la `dataPartenza`, il `deltaGiorni`, il `tipoDelta` e i `calendariFestivi`.

Esempio di payload JSON:
```json
{
 "dataPartenza": "2024-07-07",
 "deltaGiorni": 10,
 "tipoDelta": "lavorativi",
 "calendariFestivi": ["Milano"]
}
```

## Test
Per eseguire i test automatizzati:

mvn test