package com.ejemplo.demo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
@ActiveProfiles("test")
class PersonaRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(PersonaRepositoryTest.class);

    private static final String FORMATO_FECHA = "yyyy-MM-dd";

    /**
     * Parsea una cadena para obtener un objeto Calendar
     * @param fecha Cadena a ser parseada debe seguir el formato: yyyy-MM-dd
     * @return Objeto Calendar con la fecha especificada. Si el formato es inválido se
 *             regresará un calendario con la fecha actual.
     */
    public Calendar parseDate(final String fecha)  {
        final DateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(dateFormat.parse(fecha));
        } catch (ParseException e) {
            log.error("Falla al generar la fecha {} debe cumplir con el formato."
                    + " Causa: {}", fecha, FORMATO_FECHA, e);
        }
        return c;
    }

    /**
     * Realiza el formato de la fecha utilizando el patrón yyyy-MM-dd
     * @param fecha Calendar a ser formateado como cadena
     * @return La fecha formateada desde el calendario
     */
    public String formatDate(final Calendar fecha)  {
        final DateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
        return dateFormat.format(new Date(fecha.getTimeInMillis()));
    }

}
