/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.controller;

import com.asistencia.asokinev11.controller.exceptions.NonexistentEntityException;
import com.asistencia.asokinev11.controller.exceptions.RollbackFailureException;
import com.asistencia.asokinev11.entity.PrecioAsistenciaHS;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Glauber
 */
public class PrecioAsistenciaHSJpaController implements Serializable {

    public PrecioAsistenciaHSJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrecioAsistenciaHS precioAsistenciaHS) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(precioAsistenciaHS);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrecioAsistenciaHS precioAsistenciaHS) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            precioAsistenciaHS = em.merge(precioAsistenciaHS);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = precioAsistenciaHS.getId();
                if (findPrecioAsistenciaHS(id) == null) {
                    throw new NonexistentEntityException("The precioAsistenciaHS with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PrecioAsistenciaHS precioAsistenciaHS;
            try {
                precioAsistenciaHS = em.getReference(PrecioAsistenciaHS.class, id);
                precioAsistenciaHS.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The precioAsistenciaHS with id " + id + " no longer exists.", enfe);
            }
            em.remove(precioAsistenciaHS);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrecioAsistenciaHS> findPrecioAsistenciaHSEntities() {
        return findPrecioAsistenciaHSEntities(true, -1, -1);
    }

    public List<PrecioAsistenciaHS> findPrecioAsistenciaHSEntities(int maxResults, int firstResult) {
        return findPrecioAsistenciaHSEntities(false, maxResults, firstResult);
    }

    private List<PrecioAsistenciaHS> findPrecioAsistenciaHSEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrecioAsistenciaHS.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PrecioAsistenciaHS findPrecioAsistenciaHS(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrecioAsistenciaHS.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrecioAsistenciaHSCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrecioAsistenciaHS> rt = cq.from(PrecioAsistenciaHS.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
