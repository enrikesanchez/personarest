package com.ejemplo.demo.service;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.exception.PersonaNoEncontradaException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("test")
public class PersonaServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaServiceTest.class);

    @Autowired
    private PersonaService servicio;

    @Test
    void testCreacionPersona() {
        // GIVEN
        var personaNueva = new Persona();
        personaNueva.setCurp("ROVI490617HSPDSS05");
        personaNueva.setNombres("Ismael");
        personaNueva.setApellidoPaterno("Rodriguez");
        personaNueva.setApellidoMaterno("Vasquez");
        personaNueva.setFechaNacimiento(LocalDate.of(2019,1,10));

        // WHEN
        Persona personaGuardada = servicio.agregarPersona(personaNueva);

        // THEN
        assertEquals(personaNueva, personaGuardada);
    }

    @Test
    void testBuscarPersonaExistente() throws PersonaNoEncontradaException {
        // GIVEN
        var personaNueva = new Persona();
        personaNueva.setCurp("MAHJ280603MSPRRV09");
        personaNueva.setNombres("Maria Jimena");
        personaNueva.setApellidoPaterno("Martinez");
        personaNueva.setApellidoMaterno("Hernandez");
        personaNueva.setFechaNacimiento(LocalDate.of(2019,1,10));

        var personaGuardada = servicio.agregarPersona(personaNueva);

        // WHEN
        var personaEncontrada = servicio.buscarPersona(personaGuardada.getId());
        LOGGER.info("Persona encontrada {}", personaEncontrada);

        // THEN
        assertEquals(personaGuardada, personaEncontrada);
    }

}
