/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.controller;

import com.asistencia.asokinev11.controller.exceptions.NonexistentEntityException;
import com.asistencia.asokinev11.controller.exceptions.RollbackFailureException;
import com.asistencia.asokinev11.entity.PrecioAsistenciaXinermed;
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
public class PrecioAsistenciaXinermedJpaController implements Serializable {

    public PrecioAsistenciaXinermedJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrecioAsistenciaXinermed precioAsistenciaXinermed) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(precioAsistenciaXinermed);
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

    public void edit(PrecioAsistenciaXinermed precioAsistenciaXinermed) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            precioAsistenciaXinermed = em.merge(precioAsistenciaXinermed);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = precioAsistenciaXinermed.getId();
                if (findPrecioAsistenciaXinermed(id) == null) {
                    throw new NonexistentEntityException("The precioAsistenciaXinermed with id " + id + " no longer exists.");
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
            PrecioAsistenciaXinermed precioAsistenciaXinermed;
            try {
                precioAsistenciaXinermed = em.getReference(PrecioAsistenciaXinermed.class, id);
                precioAsistenciaXinermed.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The precioAsistenciaXinermed with id " + id + " no longer exists.", enfe);
            }
            em.remove(precioAsistenciaXinermed);
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

    public List<PrecioAsistenciaXinermed> findPrecioAsistenciaXinermedEntities() {
        return findPrecioAsistenciaXinermedEntities(true, -1, -1);
    }

    public List<PrecioAsistenciaXinermed> findPrecioAsistenciaXinermedEntities(int maxResults, int firstResult) {
        return findPrecioAsistenciaXinermedEntities(false, maxResults, firstResult);
    }

    private List<PrecioAsistenciaXinermed> findPrecioAsistenciaXinermedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrecioAsistenciaXinermed.class));
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

    public PrecioAsistenciaXinermed findPrecioAsistenciaXinermed(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrecioAsistenciaXinermed.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrecioAsistenciaXinermedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrecioAsistenciaXinermed> rt = cq.from(PrecioAsistenciaXinermed.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
