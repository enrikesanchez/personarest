package com.ejemplo.demo.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.exception.PersonaNoEncontradaException;
import com.ejemplo.demo.service.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Test
    void testEndPointBuscarPersona() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(df.parse("08/11/2000"));

        Persona personaGuardada = new Persona(1, "ROVI490617HSPDSS05", "Ismael", "Rodriguez", "Vasquez", fechaNacimiento);

        when(servicio.buscarPersona(1)).thenReturn(personaGuardada);

        mvc.perform(get("/personas/1").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 1,\"curp\": \"ROVI490617HSPDSS05\",\"nombres\": \"Ismael\",\"apellidoPaterno\": \"Rodriguez\",\"apellidoMaterno\": \"Vasquez\",\"fechaNacimiento\": \"08/11/2000\"}"));
    }

    @Test
    void testEndPointBuscarPersonaInexistente() throws Exception {
        when(servicio.buscarPersona(9999)).thenThrow(PersonaNoEncontradaException.class);

        mvc.perform(get("/personas/9999").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    @Test
    void testEndPointObtenerPersonas() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(df.parse("08/11/2000"));

        Persona personaGuardada1 = new Persona(1, "ROVI490617HSPDSS05", "Ismael", "Rodriguez", "Vasquez", fechaNacimiento);
        Persona personaGuardada2 = new Persona(2, "MAHJ280603MSPRRV09", "Maria Jimena", "Martinez", "Hernandez", fechaNacimiento);

        List<Persona> listaPersonas = new ArrayList<>();
        listaPersonas.add(personaGuardada1);
        listaPersonas.add(personaGuardada2);

        when(servicio.listarPersonas()).thenReturn(listaPersonas);

        mvc.perform(get("/personas").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json("["
                        + "{\"id\": 1,\"curp\": \"ROVI490617HSPDSS05\",\"nombres\": \"Ismael\",\"apellidoPaterno\": \"Rodriguez\",\"apellidoMaterno\": \"Vasquez\",\"fechaNacimiento\": \"08/11/2000\"},"
                        + "{\"id\": 2,\"curp\": \"MAHJ280603MSPRRV09\",\"nombres\": \"Maria Jimena\",\"apellidoPaterno\": \"Martinez\",\"apellidoMaterno\": \"Hernandez\",\"fechaNacimiento\": \"08/11/2000\"}"
                        + "]"));
    }

    @Test
    void testEndPointBorrarPersona() throws Exception {
        mvc.perform(delete("/personas/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testEndPointBorrarPersonaInexistente() throws Exception {
        doThrow(new PersonaNoEncontradaException()).when(servicio).borrarPersona(9999);

        mvc.perform(delete("/personas/9999").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    @Test
    void testEndPointModificarPersona() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(df.parse("08/11/2000"));

        Persona personaParametro = new Persona(1, "ROVI490617HSPDSS05", "Ismael", "Rodriguez", "Vazquez", fechaNacimiento);

        when(servicio.actualizarPersona(1, personaParametro)).thenReturn(personaParametro);

        mvc.perform(put("/personas/1").accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(personaParametro).getBytes(StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 1,\"curp\": \"ROVI490617HSPDSS05\",\"nombres\": \"Ismael\",\"apellidoPaterno\": \"Rodriguez\",\"apellidoMaterno\": \"Vazquez\",\"fechaNacimiento\": \"08/11/2000\"}"));
    }

    @Test
    void testEndPointModificarPersonaInexistente() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(df.parse("08/11/2000"));

        Persona personaParametro = new Persona(9999, "ROVI490617HSPDSS05", "Ismael", "Rodriguez", "Vazquez", fechaNacimiento);

        when(servicio.actualizarPersona(9999, personaParametro)).thenThrow(PersonaNoEncontradaException.class);

        mvc.perform(put("/personas/9999").accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(personaParametro).getBytes(StandardCharsets.UTF_8))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }
}
