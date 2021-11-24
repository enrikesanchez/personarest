package com.ejemplo.demo.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.service.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SpringJUnitConfig
@WebMvcTest(PersonaController.class)
class PersonaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonaService servicio;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testEndPointCreacionPersona() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(df.parse("08/11/2000"));

        Persona personaParametro = new Persona(0, "ROVI490617HSPDSS05", "Ismael", "Rodriguez", "Vasquez", fechaNacimiento);
        Persona personaGuardada = new Persona(1, "ROVI490617HSPDSS05", "Ismael", "Rodriguez", "Vasquez", fechaNacimiento);

        when(servicio.agregarPersona(personaParametro)).thenReturn(personaGuardada);

        mvc.perform(post("/personas").accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(personaParametro).getBytes(StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(content().json("{\"id\": 1,\"curp\": \"ROVI490617HSPDSS05\",\"nombres\": \"Ismael\",\"apellidoPaterno\": \"Rodriguez\",\"apellidoMaterno\": \"Vasquez\",\"fechaNacimiento\": \"08/11/2000\"}"));
    }
}
