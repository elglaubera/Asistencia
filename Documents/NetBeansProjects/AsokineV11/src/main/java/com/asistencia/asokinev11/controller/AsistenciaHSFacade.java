/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.controller;

import com.asistencia.asokinev11.entity.AsistenciaHS;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Glauber
 */
@Stateless
public class AsistenciaHSFacade extends AbstractFacade<AsistenciaHS> {

    @PersistenceContext(unitName = "com.asistencia_AsokineV11_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaHSFacade() {
        super(AsistenciaHS.class);
    }
    
}
