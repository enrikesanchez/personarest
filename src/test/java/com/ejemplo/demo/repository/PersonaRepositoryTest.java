package com.ejemplo.demo.repository;

import com.ejemplo.demo.entity.Persona;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class PersonaRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaRepositoryTest.class);

    private static final String FORMATO_FECHA = "yyyy-MM-dd";

    @Autowired
    PersonaRepository repository;

    @Test
    void testBuscarPersona() {
        Optional<Persona> result = repository.findById(1L);
        assertTrue(result.isPresent(), "La persona debe existir en la base de datos");
        Persona persona = result.get();
        assertEquals("MAAE16200912HLDCL", persona.getCurp());
        assertEquals("Eleonor Artemisa", persona.getNombres());
        assertEquals("Manriquez", persona.getApellidoPaterno());
        assertEquals("Arizona", persona.getApellidoMaterno());
        assertEquals("Arizona", persona.getApellidoMaterno());
        assertEquals(LocalDate.of(2016, 6, 1),  persona.getFechaNacimiento() );
    }

    @Test
    void testAgregarPersonaNueva() {
        Persona persona = crearPersona();
        Persona resultado = repository.save(persona);
        LOGGER.info("Id generado: {}", persona.getId());
        assertTrue(persona.getId() > 2,
                "El id de la persona debe ser mayor a los registrados en la base de datos");
        validarPersona(persona, resultado);
    }

    @Test
    void testActualizar() {
        final long idModificar = 2;
        Persona persona = crearPersona();
        persona.setId(idModificar);
        Persona resultado = repository.save(persona);
        assertEquals(idModificar, persona.getId(),
                "El id de la persona debe ser 2");
        validarPersona(persona, resultado);
    }

    @Test
    void testAgregarConError() {
        Persona persona = crearPersona();
        persona.setCurp("ROVI000617HSPDSS05.12");
        DataIntegrityViolationException ex = assertThrows(DataIntegrityViolationException.class,
                () -> repository.save(persona));
        assertNotNull(ex);
    }

    @Test
    void testObtenerPersonas() {
        List<Persona> lista = repository.findAll();
        assertFalse(lista.isEmpty(), "La lista debe tener registros");
        for (Persona persona: lista) {
            assertFalse(persona.getCurp().isBlank());
            assertFalse(persona.getNombres().isBlank());
            LOGGER.info("{}", persona);
        }
    }


    private void validarPersona(final Persona esperado, final Persona resultado) {
        assertEquals(esperado.getCurp(), resultado.getCurp());
        assertEquals(esperado.getNombres(), resultado.getNombres());
        assertEquals(esperado.getApellidoPaterno(), resultado.getApellidoPaterno());
        assertEquals(esperado.getApellidoMaterno(), resultado.getApellidoMaterno());
        assertEquals(esperado.getFechaNacimiento(),  resultado.getFechaNacimiento());
    }

    private Persona crearPersona() {
        Persona persona = new Persona();
        persona.setCurp("ROVI000617HSPDSS05");
        persona.setApellidoPaterno("Romero");
        persona.setApellidoMaterno("Vega");
        persona.setNombres("Israel");
        persona.setFechaNacimiento(LocalDate.of(2010, 4,15));
        return persona;
    }
}
