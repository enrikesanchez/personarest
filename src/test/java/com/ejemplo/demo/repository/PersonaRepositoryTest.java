package com.ejemplo.demo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PersonaRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaRepositoryTest.class);

}
