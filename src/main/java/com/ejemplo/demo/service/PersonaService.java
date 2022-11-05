package com.ejemplo.demo.service;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.exception.PersonaNoEncontradaException;
import com.ejemplo.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class PersonaService {
    @Autowired
    private PersonaRepository repositorio;

    public Persona buscarPersona(final long personaId) throws PersonaNoEncontradaException {
        /*
        Optional<Persona> personaOpcional = repositorio.findById(personaId);

        if (personaOpcional.isPresent()) {
            return personaOpcional.get();
        } else {
            throw new PersonaNoEncontradaException();
        }
        */

        Optional<Persona> personaOpcional = repositorio.findById(personaId);
        personaOpcional.orElseThrow(PersonaNoEncontradaException::new);
        return personaOpcional.get();
    }

    public List<Persona> listarPersonas() {
        return repositorio.findAll();
    }

    public Persona agregarPersona(final Persona personaNueva) {
        return repositorio.save(personaNueva);
    }

    public void borrarPersona(final long personaId) throws PersonaNoEncontradaException {
        /*
        final Optional<Persona> personaOpcional = repositorio.findById(personaId);

        if (personaOpcional.isPresent()) {
            repositorio.deleteById(personaId);
        } else {
            throw new PersonaNoEncontradaException();
        }
        */

        repositorio.findById(personaId).orElseThrow(PersonaNoEncontradaException::new);
        repositorio.deleteById(personaId);
    }

    public Persona actualizarPersona(final long personaId, final Persona personaModificada)
            throws PersonaNoEncontradaException {
        /*
        Optional<Persona> personaOpcional = repositorio.findById(personaId);

        if (personaOpcional.isPresent()) {
            return repositorio.save(personaModificada);
        } else {
            throw new PersonaNoEncontradaException();
        }
        */

        final Persona persona =
                repositorio.findById(personaId).orElseThrow(PersonaNoEncontradaException::new);
        return repositorio.save(personaModificada);
    }
}
