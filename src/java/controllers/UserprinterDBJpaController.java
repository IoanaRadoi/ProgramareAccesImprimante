/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.UserprinterDB;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.RollbackFailureException;
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
 * @author Oana
 */
public class UserprinterDBJpaController implements Serializable {

    public UserprinterDBJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserprinterDB userprinterDB) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(userprinterDB);
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

    public void edit(UserprinterDB userprinterDB) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            userprinterDB = em.merge(userprinterDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userprinterDB.getId();
                if (findUserprinterDB(id) == null) {
                    throw new NonexistentEntityException("The userprinterDB with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserprinterDB userprinterDB;
            try {
                userprinterDB = em.getReference(UserprinterDB.class, id);
                userprinterDB.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userprinterDB with id " + id + " no longer exists.", enfe);
            }
            em.remove(userprinterDB);
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

    public List<UserprinterDB> findUserprinterDBEntities() {
        return findUserprinterDBEntities(true, -1, -1);
    }

    public List<UserprinterDB> findUserprinterDBEntities(int maxResults, int firstResult) {
        return findUserprinterDBEntities(false, maxResults, firstResult);
    }

    private List<UserprinterDB> findUserprinterDBEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserprinterDB.class));
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

    public UserprinterDB findUserprinterDB(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserprinterDB.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserprinterDBCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserprinterDB> rt = cq.from(UserprinterDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<UserprinterDB> getByUserAndPrinter(int user, int printer) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("UserprinterDB.findByUserAndPrinter");
        q.setParameter("user", user);
        q.setParameter("printer", printer);
        List<UserprinterDB> usersprinters = q.getResultList();

        return usersprinters;
    }

    public List<UserprinterDB> getPrintersByUser(int user) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("UserprinterDB.findByUser");
        q.setParameter("user", user);
        List<UserprinterDB> usersprinters = q.getResultList();

        return usersprinters;
    }

    public List<UserprinterDB> getUsersByPrinter(int printer) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("UserprinterDB.findByPrinter");
        q.setParameter("printer", printer);
        List<UserprinterDB> usersprinters = q.getResultList();

        return usersprinters;
    }

}
