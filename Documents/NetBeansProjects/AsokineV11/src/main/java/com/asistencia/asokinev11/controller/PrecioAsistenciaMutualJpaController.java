/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.controller;

import com.asistencia.asokinev11.controller.exceptions.NonexistentEntityException;
import com.asistencia.asokinev11.controller.exceptions.RollbackFailureException;
import com.asistencia.asokinev11.entity.PrecioAsistenciaMutual;
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
public class PrecioAsistenciaMutualJpaController implements Serializable {

    public PrecioAsistenciaMutualJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrecioAsistenciaMutual precioAsistenciaMutual) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(precioAsistenciaMutual);
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

    public void edit(PrecioAsistenciaMutual precioAsistenciaMutual) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            precioAsistenciaMutual = em.merge(precioAsistenciaMutual);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = precioAsistenciaMutual.getId();
                if (findPrecioAsistenciaMutual(id) == null) {
                    throw new NonexistentEntityException("The precioAsistenciaMutual with id " + id + " no longer exists.");
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
            PrecioAsistenciaMutual precioAsistenciaMutual;
            try {
                precioAsistenciaMutual = em.getReference(PrecioAsistenciaMutual.class, id);
                precioAsistenciaMutual.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The precioAsistenciaMutual with id " + id + " no longer exists.", enfe);
            }
            em.remove(precioAsistenciaMutual);
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

    public List<PrecioAsistenciaMutual> findPrecioAsistenciaMutualEntities() {
        return findPrecioAsistenciaMutualEntities(true, -1, -1);
    }

    public List<PrecioAsistenciaMutual> findPrecioAsistenciaMutualEntities(int maxResults, int firstResult) {
        return findPrecioAsistenciaMutualEntities(false, maxResults, firstResult);
    }

    private List<PrecioAsistenciaMutual> findPrecioAsistenciaMutualEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrecioAsistenciaMutual.class));
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

    public PrecioAsistenciaMutual findPrecioAsistenciaMutual(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrecioAsistenciaMutual.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrecioAsistenciaMutualCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrecioAsistenciaMutual> rt = cq.from(PrecioAsistenciaMutual.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
