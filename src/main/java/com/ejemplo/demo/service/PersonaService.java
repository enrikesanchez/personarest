package com.ejemplo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.exception.PersonaNoEncontradaException;
import com.ejemplo.demo.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository repositorio;

    public Persona buscarPersona(final long personaId) throws PersonaNoEncontradaException {
        Optional<Persona> personaOpcional = repositorio.findById(personaId);

        if (personaOpcional.isPresent()) {
            return personaOpcional.get();
        } else {
            throw new PersonaNoEncontradaException();
        }
    }

    public List<Persona> listarPersonas() {
        return repositorio.findAll();
    }

    public Persona agregarPersona(final Persona personaNueva) {
        return repositorio.save(personaNueva);
    }

    public void borrarPersona(final long personaId) throws PersonaNoEncontradaException {
        Optional<Persona> personaOpcional = repositorio.findById(personaId);

        if (personaOpcional.isPresent()) {
            repositorio.deleteById(personaId);
        } else {
            throw new PersonaNoEncontradaException();
        }
    }

    public Persona actualizarPersona(final long personaId, final Persona personaModificada) throws PersonaNoEncontradaException {
        Optional<Persona> personaOpcional = repositorio.findById(personaId);

        if (personaOpcional.isPresent()) {
            return repositorio.save(personaModificada);
        } else {
            throw new PersonaNoEncontradaException();
        }
    }
}
