/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.elevcallsdefinitivo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Glauber
 */
@Entity
public class Condominios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nomeCondominio;
    private String endereco;
    private String contrato;
    private int qtdeElevadores;
    private String marcaElevadores;
    private int qtdeAndares;
    private int qtdeBlocos;
    private String nomeSindico;
    private String telSindico;
    private String emailSindico;
    private String nomeSubSindico;
    private String telSubSindico;
    private String emailSubSindico;
    private String imobiliaria;
    private String telImob;
    private String emailImob;

    public String getNomeCondominio() {
        return nomeCondominio;
    }

    public void setNomeCondominio(String nomeCondominio) {
        this.nomeCondominio = nomeCondominio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public int getQtdeElevadores() {
        return qtdeElevadores;
    }

    public void setQtdeElevadores(int qtdeElevadores) {
        this.qtdeElevadores = qtdeElevadores;
    }

    public String getMarcaElevadores() {
        return marcaElevadores;
    }

    public void setMarcaElevadores(String marcaElevadores) {
        this.marcaElevadores = marcaElevadores;
    }

    public int getQtdeAndares() {
        return qtdeAndares;
    }

    public void setQtdeAndares(int qtdeAndares) {
        this.qtdeAndares = qtdeAndares;
    }

    public int getQtdeBlocos() {
        return qtdeBlocos;
    }

    public void setQtdeBlocos(int qtdeBlocos) {
        this.qtdeBlocos = qtdeBlocos;
    }

    public String getNomeSindico() {
        return nomeSindico;
    }

    public void setNomeSindico(String nomeSindico) {
        this.nomeSindico = nomeSindico;
    }

    public String getTelSindico() {
        return telSindico;
    }

    public void setTelSindico(String telSindico) {
        this.telSindico = telSindico;
    }

    public String getEmailSindico() {
        return emailSindico;
    }

    public void setEmailSindico(String emailSindico) {
        this.emailSindico = emailSindico;
    }

    public String getNomeSubSindico() {
        return nomeSubSindico;
    }

    public void setNomeSubSindico(String nomeSubSindico) {
        this.nomeSubSindico = nomeSubSindico;
    }

    public String getTelSubSindico() {
        return telSubSindico;
    }

    public void setTelSubSindico(String telSubSindico) {
        this.telSubSindico = telSubSindico;
    }

    public String getEmailSubSindico() {
        return emailSubSindico;
    }

    public void setEmailSubSindico(String emailSubSindico) {
        this.emailSubSindico = emailSubSindico;
    }

    public String getImobiliaria() {
        return imobiliaria;
    }

    public void setImobiliaria(String imobiliaria) {
        this.imobiliaria = imobiliaria;
    }

    public String getTelImob() {
        return telImob;
    }

    public void setTelImob(String telImob) {
        this.telImob = telImob;
    }

    public String getEmailImob() {
        return emailImob;
    }

    public void setEmailImob(String emailImob) {
        this.emailImob = emailImob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Condominios)) {
            return false;
        }
        Condominios other = (Condominios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + nomeCondominio +" | " + endereco +"";
    }
    
}
