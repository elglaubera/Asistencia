/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.controller;

import com.asistencia.asokinev11.controller.exceptions.NonexistentEntityException;
import com.asistencia.asokinev11.controller.exceptions.RollbackFailureException;
import com.asistencia.asokinev11.entity.ProgramacionXinermed;
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
public class ProgramacionXinermedJpaController implements Serializable {

    public ProgramacionXinermedJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramacionXinermed programacionXinermed) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(programacionXinermed);
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

    public void edit(ProgramacionXinermed programacionXinermed) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            programacionXinermed = em.merge(programacionXinermed);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = programacionXinermed.getId();
                if (findProgramacionXinermed(id) == null) {
                    throw new NonexistentEntityException("The programacionXinermed with id " + id + " no longer exists.");
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
            ProgramacionXinermed programacionXinermed;
            try {
                programacionXinermed = em.getReference(ProgramacionXinermed.class, id);
                programacionXinermed.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionXinermed with id " + id + " no longer exists.", enfe);
            }
            em.remove(programacionXinermed);
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

    public List<ProgramacionXinermed> findProgramacionXinermedEntities() {
        return findProgramacionXinermedEntities(true, -1, -1);
    }

    public List<ProgramacionXinermed> findProgramacionXinermedEntities(int maxResults, int firstResult) {
        return findProgramacionXinermedEntities(false, maxResults, firstResult);
    }

    private List<ProgramacionXinermed> findProgramacionXinermedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProgramacionXinermed.class));
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

    public ProgramacionXinermed findProgramacionXinermed(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramacionXinermed.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionXinermedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProgramacionXinermed> rt = cq.from(ProgramacionXinermed.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
