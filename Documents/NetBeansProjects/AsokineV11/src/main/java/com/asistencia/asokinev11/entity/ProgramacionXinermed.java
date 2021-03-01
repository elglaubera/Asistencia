/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Glauber
 */
@Entity
public class ProgramacionXinermed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = false)
    private Empleados empleados;

    @OneToOne(optional = false)
    private Xinermed xinermed;

    @OneToOne(optional = false)
    private PrecioAsistenciaXinermed asistencia;

    private String data;
    private String hora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Xinermed getXinermed() {
        return xinermed;
    }

    public void setXinermed(Xinermed xinermed) {
        this.xinermed = xinermed;
    }

    public PrecioAsistenciaXinermed getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(PrecioAsistenciaXinermed asistencia) {
        this.asistencia = asistencia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramacionXinermed)) {
            return false;
        }
        ProgramacionXinermed other = (ProgramacionXinermed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return data + " | " + hora + " | " + empleados + " | " + xinermed + " | " + asistencia;
    }

}
