package com.ejemplo.demo.service;

import com.ejemplo.demo.entity.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class PersonaServiceTest {

    @Autowired
    PersonaService servicio;

    @Test
    void testCreacionPersona() {
        // GIVEN
        Persona personaNueva = new Persona();
        personaNueva.setCurp("ROVI490617HSPDSS05");
        personaNueva.setNombres("Ismael");
        personaNueva.setApellidoPaterno("Rodriguez");
        personaNueva.setApellidoMaterno("Vasquez");
        personaNueva.setFechaNacimiento(Calendar.getInstance());

        // WHEN
        Persona personaGuardada = servicio.agregarPersona(personaNueva);

        // THEN
        assertEquals(personaNueva, personaGuardada);
    }
}
