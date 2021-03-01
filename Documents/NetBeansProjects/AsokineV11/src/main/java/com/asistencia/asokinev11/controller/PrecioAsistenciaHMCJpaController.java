/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.controller;

import com.asistencia.asokinev11.controller.exceptions.NonexistentEntityException;
import com.asistencia.asokinev11.controller.exceptions.RollbackFailureException;
import com.asistencia.asokinev11.entity.PrecioAsistenciaHMC;
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
public class PrecioAsistenciaHMCJpaController implements Serializable {

    public PrecioAsistenciaHMCJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrecioAsistenciaHMC precioAsistenciaHMC) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(precioAsistenciaHMC);
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

    public void edit(PrecioAsistenciaHMC precioAsistenciaHMC) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            precioAsistenciaHMC = em.merge(precioAsistenciaHMC);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = precioAsistenciaHMC.getId();
                if (findPrecioAsistenciaHMC(id) == null) {
                    throw new NonexistentEntityException("The precioAsistenciaHMC with id " + id + " no longer exists.");
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
            PrecioAsistenciaHMC precioAsistenciaHMC;
            try {
                precioAsistenciaHMC = em.getReference(PrecioAsistenciaHMC.class, id);
                precioAsistenciaHMC.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The precioAsistenciaHMC with id " + id + " no longer exists.", enfe);
            }
            em.remove(precioAsistenciaHMC);
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

    public List<PrecioAsistenciaHMC> findPrecioAsistenciaHMCEntities() {
        return findPrecioAsistenciaHMCEntities(true, -1, -1);
    }

    public List<PrecioAsistenciaHMC> findPrecioAsistenciaHMCEntities(int maxResults, int firstResult) {
        return findPrecioAsistenciaHMCEntities(false, maxResults, firstResult);
    }

    private List<PrecioAsistenciaHMC> findPrecioAsistenciaHMCEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrecioAsistenciaHMC.class));
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

    public PrecioAsistenciaHMC findPrecioAsistenciaHMC(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrecioAsistenciaHMC.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrecioAsistenciaHMCCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrecioAsistenciaHMC> rt = cq.from(PrecioAsistenciaHMC.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
