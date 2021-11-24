package com.ejemplo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.demo.entity.Persona;
import com.ejemplo.demo.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository repositorio;

    public Persona agregarPersona(final Persona personaNueva) {
        return repositorio.save(personaNueva);
    }
}
