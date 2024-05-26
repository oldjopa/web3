package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import utils.CheckResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBHandler {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CheckResult");
    static {

    }

    static public void addResult(CheckResult result){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(result);
        transaction.commit();
        em.close();
    }

    static public ArrayList<CheckResult> getResults(){
        EntityManager em = emf.createEntityManager();
        List<CheckResult> resultList = em.createQuery("SELECT e FROM CheckResult e", CheckResult.class).getResultList();
        em.close();
        return new ArrayList<>(resultList);
    }

    public static void clearResults() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.createQuery("DELETE FROM CheckResult e").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}