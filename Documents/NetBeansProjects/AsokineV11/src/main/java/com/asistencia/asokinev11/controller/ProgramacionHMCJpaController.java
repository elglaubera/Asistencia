/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asistencia.asokinev11.controller;

import com.asistencia.asokinev11.controller.exceptions.NonexistentEntityException;
import com.asistencia.asokinev11.controller.exceptions.RollbackFailureException;
import com.asistencia.asokinev11.entity.ProgramacionHMC;
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
public class ProgramacionHMCJpaController implements Serializable {

    public ProgramacionHMCJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramacionHMC programacionHMC) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(programacionHMC);
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

    public void edit(ProgramacionHMC programacionHMC) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            programacionHMC = em.merge(programacionHMC);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = programacionHMC.getId();
                if (findProgramacionHMC(id) == null) {
                    throw new NonexistentEntityException("The programacionHMC with id " + id + " no longer exists.");
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
            ProgramacionHMC programacionHMC;
            try {
                programacionHMC = em.getReference(ProgramacionHMC.class, id);
                programacionHMC.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionHMC with id " + id + " no longer exists.", enfe);
            }
            em.remove(programacionHMC);
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

    public List<ProgramacionHMC> findProgramacionHMCEntities() {
        return findProgramacionHMCEntities(true, -1, -1);
    }

    public List<ProgramacionHMC> findProgramacionHMCEntities(int maxResults, int firstResult) {
        return findProgramacionHMCEntities(false, maxResults, firstResult);
    }

    private List<ProgramacionHMC> findProgramacionHMCEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProgramacionHMC.class));
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

    public ProgramacionHMC findProgramacionHMC(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramacionHMC.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionHMCCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProgramacionHMC> rt = cq.from(ProgramacionHMC.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
