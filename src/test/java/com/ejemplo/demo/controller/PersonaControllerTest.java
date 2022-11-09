package com.ejemplo.demo.controller;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.exception.PersonaNoEncontradaException;
import com.ejemplo.demo.service.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(PersonaController.class)
class PersonaControllerTest {

    private static final Logger log = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonaService servicio;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testEndPointCreacionPersona() throws Exception {
        var fechaNacimiento = LocalDate.of(2000,11,8);

        var personaParametro = new Persona(0,
                "ROVI490617HSPDSS05",
                "Ismael",
                "Rodriguez",
                "Vasquez", fechaNacimiento);
        var personaGuardada = new Persona(1,
                "ROVI490617HSPDSS05",
                "Ismael",
                "Rodriguez",
                "Vasquez", fechaNacimiento);

        when(servicio.agregarPersona(personaParametro)).thenReturn(personaGuardada);

        mvc.perform(post("/personas").accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(personaParametro).getBytes(StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "curp": "ROVI490617HSPDSS05",
                            "nombres": "Ismael",
                            "apellidoPaterno": "Rodriguez",
                            "apellidoMaterno": "Vasquez",
                            "fechaNacimiento": "08/11/2000"
                        }"""));
    }


}
