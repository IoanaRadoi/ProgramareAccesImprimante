/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.UserDB;
import beans.UserprinterDB;
import controllers.exceptions.RollbackFailureException;
import drivenbeans.User;
import drivenbeans.UserPrinter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Oana
 */
public class MainController {

    private static MainController singleton;

    private EntityManagerFactory emf;
    private UserTransaction utx;
    private Context ctx;
    private EntityManager em;

    private UserDBJpaController userContoller;
    private UserprinterDBJpaController userprinterController;

    public MainController() {
        try {
            ctx = new InitialContext();
            utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");

            userContoller = new UserDBJpaController(utx, em.getEntityManagerFactory());
            userprinterController = new UserprinterDBJpaController(utx, em.getEntityManagerFactory());

        } catch (NamingException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static MainController getInstance() {
        if (singleton == null) {
            singleton = new MainController();
        }
        return singleton;
    }

    public boolean inregistrare(String user, String parola) {
        UserDB u = userContoller.getUserByUser(user);
        if (u == null) {
            try {
                userContoller.create(new UserDB(0, user, parola, 1));
            } catch (Exception ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }

    public User login(String user, String parola) {

        UserDB u = userContoller.getUserByUser(user);
        if (u != null) {
            if (u.getParola().equals(parola)) {
                return new User(u.getId(), u.getUser(), u.getParola());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void createUserPrinter(int user, int imp, String time1, String time2) {

        try {
            userprinterController.create(new UserprinterDB(0, time1, time2, user, imp));
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //return new UserPrinter(imp, user, imp, time1, time2, "doc");
    }

    public List<UserPrinter> getPrinters() {
        List<UserprinterDB> printers = userprinterController.findUserprinterDBEntities();
        List<UserPrinter> list = new ArrayList<UserPrinter>();
        for (UserprinterDB p : printers) {
            list.add(new UserPrinter(p.getId(), p.getUser(), p.getPrinter(), p.getStart(), p.getEnd(), "doc"));
        }
        return list;
    }

    public List<UserPrinter> getUserPrinters(int user, int printer) {
        List<UserprinterDB> printers = userprinterController.getByUserAndPrinter(user, printer);
        List<UserPrinter> list = new ArrayList<UserPrinter>();
        for (UserprinterDB p : printers) {
            list.add(new UserPrinter(p.getId(), p.getUser(), p.getPrinter(), p.getStart(), p.getEnd(), "doc"));
        }
        return list;
    }

    public List<UserPrinter> getUsersByPrinter(int printer) {
        List<UserprinterDB> printers = userprinterController.getUsersByPrinter(printer);
        List<UserPrinter> list = new ArrayList<UserPrinter>();
        for (UserprinterDB p : printers) {
            list.add(new UserPrinter(p.getId(), p.getUser(), p.getPrinter(), p.getStart(), p.getEnd(), "doc"));
        }
        return list;
    }

    public List<UserPrinter> getPrintersByUser(int user) {
        List<UserprinterDB> printers = userprinterController.getPrintersByUser(user);
        List<UserPrinter> list = new ArrayList<UserPrinter>();
        for (UserprinterDB p : printers) {
            list.add(new UserPrinter(p.getId(), p.getUser(), p.getPrinter(), p.getStart(), p.getEnd(), "doc"));
        }
        return list;
    }

    public void stergeUserPrinter(int id) {
        try {
            userprinterController.destroy(id);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserPrinter getUserPrinter(int id) {

        UserprinterDB p = userprinterController.findUserprinterDB(id);
        if (p != null) {
            return new UserPrinter(p.getId(), p.getUser(), p.getPrinter(), p.getStart(), p.getEnd(), "doc");
        } else {
            return null;
        }

    }

    public void updateUserPrinter(int id, String start, String end) {
        UserprinterDB p = userprinterController.findUserprinterDB(id);
        p.setStart(start);
        p.setEnd(end);
        try {
            userprinterController.edit(p);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
