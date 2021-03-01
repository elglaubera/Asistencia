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
public class ProgramacionAsistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = false)
    private Xinermed xinermed;

    @OneToOne(optional = false)
    private HMC hmc;

    @OneToOne(optional = false)
    private HS hs;

    @OneToOne(optional = false)
    private Mutual mutual;

    @OneToOne(optional = false)
    private Particular particular;

    @OneToOne(optional = false)
    private Empleados empleados;

    private String data;
    private String hora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Xinermed getXinermed() {
        return xinermed;
    }

    public void setXinermed(Xinermed xinermed) {
        this.xinermed = xinermed;
    }

    public HMC getHmc() {
        return hmc;
    }

    public void setHmc(HMC hmc) {
        this.hmc = hmc;
    }

    public HS getHs() {
        return hs;
    }

    public void setHs(HS hs) {
        this.hs = hs;
    }

    public Mutual getMutual() {
        return mutual;
    }

    public void setMutual(Mutual mutual) {
        this.mutual = mutual;
    }

    public Particular getParticular() {
        return particular;
    }

    public void setParticular(Particular particular) {
        this.particular = particular;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
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
        if (!(object instanceof ProgramacionAsistencia)) {
            return false;
        }
        ProgramacionAsistencia other = (ProgramacionAsistencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asistencia.asokinev11.ProgramacionAsistencia[ id=" + id + " ]";
    }
    
}
