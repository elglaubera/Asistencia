/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.controller;

import com.asistencia.asokinev11.controller.exceptions.NonexistentEntityException;
import com.asistencia.asokinev11.controller.exceptions.RollbackFailureException;
import com.asistencia.asokinev11.entity.TipoAsistenciaXinermed;
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
public class TipoAsistenciaXinermedJpaController implements Serializable {

    public TipoAsistenciaXinermedJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAsistenciaXinermed tipoAsistenciaXinermed) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tipoAsistenciaXinermed);
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

    public void edit(TipoAsistenciaXinermed tipoAsistenciaXinermed) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tipoAsistenciaXinermed = em.merge(tipoAsistenciaXinermed);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoAsistenciaXinermed.getId();
                if (findTipoAsistenciaXinermed(id) == null) {
                    throw new NonexistentEntityException("The tipoAsistenciaXinermed with id " + id + " no longer exists.");
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
            TipoAsistenciaXinermed tipoAsistenciaXinermed;
            try {
                tipoAsistenciaXinermed = em.getReference(TipoAsistenciaXinermed.class, id);
                tipoAsistenciaXinermed.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAsistenciaXinermed with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoAsistenciaXinermed);
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

    public List<TipoAsistenciaXinermed> findTipoAsistenciaXinermedEntities() {
        return findTipoAsistenciaXinermedEntities(true, -1, -1);
    }

    public List<TipoAsistenciaXinermed> findTipoAsistenciaXinermedEntities(int maxResults, int firstResult) {
        return findTipoAsistenciaXinermedEntities(false, maxResults, firstResult);
    }

    private List<TipoAsistenciaXinermed> findTipoAsistenciaXinermedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoAsistenciaXinermed.class));
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

    public TipoAsistenciaXinermed findTipoAsistenciaXinermed(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAsistenciaXinermed.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAsistenciaXinermedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoAsistenciaXinermed> rt = cq.from(TipoAsistenciaXinermed.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
