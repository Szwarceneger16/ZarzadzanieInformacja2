import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nowa_pusta");
        EntityManager em = emf.createEntityManager();

        //zdanie 5 - troche wiecej dla proby
        ArrayList<Person> per = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Person p = new Person();
            p.setAge(i*i);
            p.setFamilyName("kowalski");
            if (i % 2 == 0) {
                p.setFirstName("adam");
            } else {
                p.setFirstName("jan");
            }
            per.add(p);
        }

        //zadanie 6
        em.getTransaction().begin();
        for (Person p:
             per) {
            em.persist(p);
        }
        em.getTransaction().commit();

        Query query = em.createQuery("SELECT p FROM Person p where p.age < :id");
        query.setParameter("id",18);
        List<Person> people = query.getResultList();
        for (Person p:
             people) {
            p.setAge(18);
        }

        em.getTransaction().begin();
        for (Person p:
                people) {
            em.persist(p);
        }
        em.getTransaction().commit();

        //zadanie 7
        Query firstQuery = em.createQuery("SELECT COUNT(p) FROM Person p");
        Object o = firstQuery.getSingleResult();

        System.out.println("Count: " + o);

        //zadanie 8
        Query query2 = em.createQuery("SELECT p FROM Person p where p.firstName LIKE :character ");
        query2.setParameter("character","j%");
        List<Person> people2 = query2.getResultList();
        for (Person p:
                people2) {
            System.out.println(p);
        }


    }
}
