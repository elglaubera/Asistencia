/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.controller;

import com.asistencia.asokinev11.entity.HS;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Glauber
 */
@Stateless
public class HSFacade extends AbstractFacade<HS> {

    @PersistenceContext(unitName = "com.asistencia_AsokineV11_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HSFacade() {
        super(HS.class);
    }
    
}
