/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignment1_soap.entities;

import com.mycompany.assignment1_soap.entities.exceptions.NonexistentEntityException;
import com.mycompany.assignment1_soap.entities.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Harsh
 */
public class CinemasJpaController implements Serializable {

    public CinemasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cinemas cinemas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cinemas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCinemas(cinemas.getId()) != null) {
                throw new PreexistingEntityException("Cinemas " + cinemas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cinemas cinemas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cinemas = em.merge(cinemas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cinemas.getId();
                if (findCinemas(id) == null) {
                    throw new NonexistentEntityException("The cinemas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cinemas cinemas;
            try {
                cinemas = em.getReference(Cinemas.class, id);
                cinemas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cinemas with id " + id + " no longer exists.", enfe);
            }
            em.remove(cinemas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cinemas> findCinemasEntities() {
        return findCinemasEntities(true, -1, -1);
    }

    public List<Cinemas> findCinemasEntities(int maxResults, int firstResult) {
        return findCinemasEntities(false, maxResults, firstResult);
    }

    private List<Cinemas> findCinemasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cinemas.class));
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

    public Cinemas findCinemas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cinemas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCinemasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cinemas> rt = cq.from(Cinemas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
