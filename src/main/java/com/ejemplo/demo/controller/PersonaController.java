package com.ejemplo.demo.controller;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.exception.PersonaNoEncontradaException;
import com.ejemplo.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonaController {
    @Autowired
    PersonaService servicio;

    @PostMapping("/personas")
    public ResponseEntity<Persona> agregarPersona(@RequestBody final Persona personaNueva) {
        Persona personaGuardada = servicio.agregarPersona(personaNueva);
        return new ResponseEntity<>(personaGuardada, HttpStatus.CREATED);
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Persona> obtenerPersona(@PathVariable(value = "id") final long id) throws PersonaNoEncontradaException {
        Persona personaEncontrada = servicio.buscarPersona(id);
        return new ResponseEntity<>(personaEncontrada, HttpStatus.OK);
    }

    @ExceptionHandler(PersonaNoEncontradaException.class)
    public ResponseEntity<String> siPersonaNoEncontradaException(final PersonaNoEncontradaException pnee) {
        return new ResponseEntity<>(pnee.getMessage(), HttpStatus.NO_CONTENT);
    }
}
