package com.ejemplo.demo.repository;

import com.ejemplo.demo.entity.Persona;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class PersonaRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaRepositoryTest.class);

    private static final String FORMATO_FECHA = "yyyy-MM-dd";

    @Autowired
    PersonaRepository repository;

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
