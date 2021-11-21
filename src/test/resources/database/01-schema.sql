DROP SEQUENCE HIBERNATE_SEQUENCE IF EXISTS;

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 3 INCREMENT BY 1;


DROP TABLE personas IF EXISTS;

CREATE TABLE personas (
    persona_id integer not null,
    curp VARCHAR(18),
    nombres VARCHAR(30) NOT NULL,
    apellido_paterno VARCHAR(20) NOT NULL,
    apellido_materno VARCHAR(20) NOT NULL,
    fecha_nacimiento DATE NOT NULL
);

ALTER TABLE personas ADD CONSTRAINT  persona_pk PRIMARY KEY (persona_id);


