package com.ejemplo.demo.controller;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {
    @Autowired
    PersonaService servicio;

    @PostMapping("/personas")
    public ResponseEntity<Persona> agregarPersona(@RequestBody final Persona personaNueva) {
        Persona personaGuardada = servicio.agregarPersona(personaNueva);
        return new ResponseEntity<>(personaGuardada, HttpStatus.CREATED);
    }
}
