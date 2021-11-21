package com.ejemplo.demo.repository;

import com.ejemplo.demo.entity.Persona;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PersonaRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaRepositoryTest.class);

    private static final String FORMATO_FECHA = "yyyy-MM-dd";




    public Calendar parseDate(final String fecha)  {
        final DateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
        Calendar c = Calendar.getInstance();

        try {
            c.setTime(dateFormat.parse(fecha));
        } catch (ParseException e) {
            LOGGER.error("Falla al generar la fecha {} debe cumplir con el formato." +
                    " Causa: {}", fecha, FORMATO_FECHA, e);
        }
        return c;
    }

    public String formatDate(final Calendar fecha)  {
        final DateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA);
        return dateFormat.format(new Date(fecha.getTimeInMillis()));
    }

}