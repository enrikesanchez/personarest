package com.ejemplo.demo.service;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.exception.PersonaNoEncontradaException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("test")
class PersonaServiceTest {

    @Autowired
    private PersonaService servicio;

    @Test
    void testCreacionPersona() {
        // GIVEN
        Persona personaNueva = new Persona();
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
        Persona personaNueva = new Persona();
        personaNueva.setCurp("MAHJ280603MSPRRV09");
        personaNueva.setNombres("Maria Jimena");
        personaNueva.setApellidoPaterno("Martinez");
        personaNueva.setApellidoMaterno("Hernandez");
        personaNueva.setFechaNacimiento(LocalDate.of(2019,1,10));

        Persona personaGuardada = servicio.agregarPersona(personaNueva);

        // WHEN
        Persona personaEncontrada = servicio.buscarPersona(personaGuardada.getId());

        // THEN
        assertEquals(personaGuardada, personaEncontrada);
    }

    @Test
    void testBuscarPersonaNoExistente() {
        // GIVEN
        long notExistingId = Long.MAX_VALUE;

        // WHEN
        try {
            Persona personaEncontrada = servicio.buscarPersona(notExistingId);
            fail();
        } catch (PersonaNoEncontradaException pnee) {
            // THEN
            assertTrue(true);
        }
    }

    @Test
    void testBorrarPersonaExistente() throws PersonaNoEncontradaException {
        // GIVEN
        Persona personaNueva = new Persona();
        personaNueva.setCurp("PERC561125MSPRMT03");
        personaNueva.setNombres("Catalina");
        personaNueva.setApellidoPaterno("Peralta");
        personaNueva.setApellidoMaterno("Ramirez");
        personaNueva.setFechaNacimiento(LocalDate.of(2019,1,10));

        Persona personaGuardada = servicio.agregarPersona(personaNueva);

        // WHEN
        servicio.borrarPersona(personaGuardada.getId());

        // THEN
        try {
            Persona personaEncontrada = servicio.buscarPersona(personaGuardada.getId());
            fail();
        } catch (PersonaNoEncontradaException pnee) {
            // THEN
            assertTrue(true);
        }
    }

    @Test
    void testBorrarPersonaNoExistente() {
        // GIVEN
        long notExistingId = Long.MAX_VALUE;

        // WHEN
        try {
            servicio.borrarPersona(notExistingId);
            fail();
        } catch (PersonaNoEncontradaException pnee) {
            // THEN
            assertTrue(true);
        }
    }

    @Test
    void testActualizarPersonaExistente() throws PersonaNoEncontradaException {
        // GIVEN
        Persona personaNueva = new Persona();
        personaNueva.setCurp("TOHA530902HSPRRN07");
        personaNueva.setNombres("Antonio");
        personaNueva.setApellidoPaterno("Torres");
        personaNueva.setApellidoMaterno("Hernandez");
        personaNueva.setFechaNacimiento(LocalDate.of(2019,1,10));

        Persona personaGuardada = servicio.agregarPersona(personaNueva);
        personaGuardada.setApellidoMaterno("Holguin");

        // WHEN
        Persona personaActualizada = servicio.actualizarPersona(
                personaGuardada.getId(), personaGuardada);

        // THEN
        assertEquals(personaGuardada, personaActualizada);
    }

    @Test
    void testActualizarPersonaNoExistente() {
        // GIVEN
        Persona personaNoExistente = new Persona();
        personaNoExistente.setId(Long.MAX_VALUE);
        personaNoExistente.setCurp("VEGA780615HDFLML08");
        personaNoExistente.setNombres("Alvaro");
        personaNoExistente.setApellidoPaterno("Velazquez");
        personaNoExistente.setApellidoMaterno("Gomez");
        personaNoExistente.setFechaNacimiento(LocalDate.now());

        // WHEN
        try {
            Persona personaActualizada = servicio.actualizarPersona(
                    personaNoExistente.getId(), personaNoExistente);
            fail();
        } catch (PersonaNoEncontradaException pnee) {
            // THEN
            assertTrue(true);
        }
    }
}
