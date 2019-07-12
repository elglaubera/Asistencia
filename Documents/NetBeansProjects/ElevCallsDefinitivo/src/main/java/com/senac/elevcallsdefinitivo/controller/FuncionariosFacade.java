/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.elevcallsdefinitivo.controller;

import com.senac.elevcallsdefinitivo.entity.Funcionarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Glauber
 */
@Stateless
public class FuncionariosFacade extends AbstractFacade<Funcionarios> {

    @PersistenceContext(unitName = "com.senac_ElevCallsDefinitivo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncionariosFacade() {
        super(Funcionarios.class);
    }
    
}
