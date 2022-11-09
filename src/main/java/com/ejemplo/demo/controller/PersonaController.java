package com.ejemplo.demo.controller;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.exception.PersonaNoEncontradaException;
import com.ejemplo.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    private PersonaService servicio;

    @PostMapping("/personas")
    public ResponseEntity<Persona> agregarPersona(@RequestBody final Persona personaNueva) {
        var personaGuardada = servicio.agregarPersona(personaNueva);
        return new ResponseEntity<>(personaGuardada, HttpStatus.CREATED);
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Persona> obtenerPersona(@PathVariable(value = "id") final long id)
            throws PersonaNoEncontradaException {
        var personaEncontrada = servicio.buscarPersona(id);
        return new ResponseEntity<>(personaEncontrada, HttpStatus.OK);
    }

    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> obtenerPersonas() {
        List<Persona> personaEncontradas = servicio.listarPersonas();
        return new ResponseEntity<>(personaEncontradas, HttpStatus.OK);
    }

    @DeleteMapping("/personas/{id}")
    public ResponseEntity<Persona> borrarPersona(@PathVariable(value = "id") final long id)
            throws PersonaNoEncontradaException {
        servicio.borrarPersona(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/personas/{id}")
    public ResponseEntity<Persona> modificarPersona(@PathVariable(value = "id") final long id,
            @RequestBody final Persona personaModificada) throws PersonaNoEncontradaException {
        var personaGuardada = servicio.actualizarPersona(id, personaModificada);
        return new ResponseEntity<>(personaGuardada, HttpStatus.OK);
    }

    @ExceptionHandler(PersonaNoEncontradaException.class)
    public ResponseEntity<String> siPersonaNoEncontradaException(
            final PersonaNoEncontradaException pnee) {
        return new ResponseEntity<>(pnee.getMessage(), HttpStatus.NO_CONTENT);
    }
}
