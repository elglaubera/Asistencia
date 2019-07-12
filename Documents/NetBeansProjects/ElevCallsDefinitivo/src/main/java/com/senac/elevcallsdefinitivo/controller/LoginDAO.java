package com.senac.elevcallsdefinitivo.controller;

import com.senac.elevcallsdefinitivo.entity.Login;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author Glauber
 */
public class LoginDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("login");
    private EntityManager em = factory.createEntityManager();

    public Login getLogin(String usuario, String senha) {

        try {
            Login login = (Login) em.createQuery("SELECT u FROM login u where u.usuario = :usuario and u.senha = :senha")
                    .setParameter("usuario", usuario).setParameter("senha", senha).getSingleResult();

            return login;

        } catch (NoResultException e) {
            return null;
        }
    }
    
    public boolean inserirLogin(Login login) {
          try {
                em.persist(login);
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }
}
