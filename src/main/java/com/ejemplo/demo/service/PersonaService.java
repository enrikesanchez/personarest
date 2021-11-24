package com.ejemplo.demo.service;

import com.ejemplo.demo.exception.PersonaNoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.repository.PersonaRepository;

import java.util.Optional;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository repositorio;

    public Persona agregarPersona(final Persona personaNueva) {
        return repositorio.save(personaNueva);
    }

    public Persona buscarPersona(final long personaId) throws PersonaNoEncontradaException {
        Optional<Persona> personaOpcional = repositorio.findById(personaId);

        if (personaOpcional.isPresent()) {
            return personaOpcional.get();
        } else {
            throw new PersonaNoEncontradaException();
        }
    }
}
