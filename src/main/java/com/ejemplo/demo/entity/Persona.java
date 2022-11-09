package com.ejemplo.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "PERSONAS")
public class Persona {

    @Id
    @GeneratedValue
    @Column(name = "persona_id")
    private long id;

    @Column(name = "curp")
    private String curp;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate fechaNacimiento;

    public Persona() {

    }

    public Persona(final long id, final String curp, final String nombres,
            final String apellidoPaterno, final String apellidoMaterno,
            final LocalDate fechaNacimiento) {
        this.id = id;
        this.curp = curp;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(final String curp) {
        this.curp = curp;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(final String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(final String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(final String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(final LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona [id=" + id +
                ", curp=" + curp +
                ", nombres=" + nombres +
                ", apellidoPaterno=" + apellidoPaterno +
                ", apellidoMaterno=" + apellidoMaterno +
                ", fechaNacimiento=" + fechaNacimiento + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((curp == null) ? 0 : curp.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Persona other = (Persona) obj;
        if (curp == null) {
            if (other.curp != null)
                return false;
        } else if (!curp.equals(other.curp))
            return false;
        return true;
    }
}
