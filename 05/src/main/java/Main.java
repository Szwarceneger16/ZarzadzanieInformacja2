import javax.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean dodawaj = true;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nowa_pusta");
        EntityManager em = emf.createEntityManager();

        if (dodawaj) {
            ClassRoom class1 = new ClassRoom();
            class1.setName("3C");
            class1.setLevel(1999);
            ClassRoom class2 = new ClassRoom();
            class2.setName("3D");
            class2.setLevel(1999);
            ClassRoom class3 = new ClassRoom();
            class3.setName("3A");
            class3.setLevel(1999);

            ArrayList<Subject> subjects1 = new ArrayList<>();
            ArrayList<Subject> subjects2 = new ArrayList<>();
            ArrayList<Subject> subjects3 = new ArrayList<>();
            subjects1.add(new Subject("Matematyka"));
            subjects1.add(new Subject("J.Polski"));
            subjects1.add(new Subject("WF"));
            subjects1.add(new Subject("Historia"));
            subjects1.forEach( arg -> {
                arg.setClasses(class1);
            });
            class1.setSubs(subjects1);
            subjects2.add(new Subject("Matematyka"));
            subjects2.add(new Subject("J.Polski"));
            subjects2.add(new Subject("Religia"));
            subjects2.add(new Subject("Historia"));
            subjects2.forEach( arg -> {
                arg.setClasses(class2);
            });
            class2.setSubs(subjects2);
            subjects3.add(new Subject("Matematyka"));
            subjects3.add(new Subject("J.Polski"));
            subjects3.add(new Subject("Etyka"));
            subjects3.add(new Subject("Historia"));
            subjects3.forEach( arg -> {
                arg.setClasses(class3);
            });
            class3.setSubs(subjects3);

            Teacher tech_mat;
            tech_mat = new Teacher("Joanna","Matematowska",1989);
            tech_mat.setEducator(class1);
            class1.setEducator(tech_mat);
            tech_mat.setSubjects(new ArrayList<>(List.of(subjects1.get(0), subjects2.get(0), subjects3.get(0))));
            tech_mat.getSubjects().forEach( arg -> {
                System.out.println(arg);
            });
            System.out.println();
            subjects1.get(0).setLecturer(tech_mat);
            subjects2.get(0).setLecturer(tech_mat);
            subjects3.get(0).setLecturer(tech_mat);

            Teacher tech_pol;
            tech_pol = new Teacher("Bogumila","Poliszewska",1975);
            tech_pol.setEducator(class2);
            class2.setEducator(tech_pol);
            tech_pol.setSubjects(new ArrayList<>(List.of(subjects1.get(1), subjects2.get(1), subjects3.get(1))));
            subjects1.get(1).setLecturer(tech_pol);
            subjects2.get(1).setLecturer(tech_pol);
            subjects3.get(1).setLecturer(tech_pol);

            Teacher tech_hist;
            tech_hist = new Teacher("Anna","Historioszewska",1981);
            tech_hist.setEducator(class3);
            class3.setEducator(tech_hist);
            tech_hist.setSubjects(new ArrayList<>(List.of(subjects1.get(3), subjects2.get(3), subjects3.get(3))));
            subjects1.get(3).setLecturer(tech_hist);
            subjects2.get(3).setLecturer(tech_hist);
            subjects3.get(3).setLecturer(tech_hist);

            Teacher tech_wf;
            tech_wf = new Teacher("Robert","Wuefista",1981);
            tech_wf.setEducator(null);
            tech_wf.setSubjects(new ArrayList<>(List.of(subjects1.get(2))));
            subjects1.get(2).setLecturer(tech_wf);

            Teacher tech_rel;
            tech_rel = new Teacher("Jan","Ksiazency",1965);
            tech_rel.setEducator(null);
            tech_rel.setSubjects(new ArrayList<>(List.of(subjects2.get(2))));
            subjects2.get(2).setLecturer(tech_rel);

            Teacher tech_etyk;
            tech_etyk = new Teacher("Natalia","Grzeczna",1979);
            tech_etyk.setEducator(null);
            tech_etyk.setSubjects(new ArrayList<>(List.of(subjects3.get(2))));
            subjects3.get(2).setLecturer(tech_etyk);


            ArrayList<Student> students1 = new ArrayList<>();
            students1.add(new Student("jan","kowalski",45645));
            students1.add(new Student("grzegorz","nowolski",44453));
            students1.add(new Student("piotr","osma",76565));
            students1.add(new Student("jakub","ulsza",44457));
            students1.add(new Student("robert","plas",99874));
            students1.forEach( arg -> {
                arg.setRoom(class1);
            });
            class1.setStudents(students1);

            ArrayList<Student> students2 = new ArrayList<>();
            students2.add(new Student("antoni","polek",78912));
            students2.add(new Student("milosz","jacobs",45623));
            students2.add(new Student("nataniel","opatowski",85263));
            students2.add(new Student("adrian","kosc",96854));
            students2.add(new Student("natalia","kulesza",23784));
            students2.forEach( arg -> {
                arg.setRoom(class2);
            });
            class2.setStudents(students2);

            ArrayList<Student> students3 = new ArrayList<>();
            students3.add(new Student("izabela","trojanowska",96853));
            students3.add(new Student("maria","tzrebierska",12968));
            students3.add(new Student("magdalena","piatnica",23784));
            students3.add(new Student("natalia","jantar",56781));
            students3.add(new Student("oliwia","piatek",65823));
            students3.forEach( arg -> {
                arg.setRoom(class3);
            });
            class3.setStudents(students3);

            em.getTransaction().begin();
            em.persist(class1);
            em.persist(class2);
            em.persist(class3);
            em.getTransaction().commit();
        }


//        List<ClassRoom> everyClass = em.createQuery("select p from ClassRoom p",ClassRoom.class).getResultList();
//        for (ClassRoom res:
//                everyClass) {
//            System.out.println(res);
//        }

        ClassRoom resultQuery = em.createQuery("select p from ClassRoom p",ClassRoom.class).getResultList().get(0);
        resultQuery.getSubs().forEach( arg -> {
            System.out.println(arg);
        });

        // dla wybranego ucznia wyswietlic jego przemdioty
        int album = 56781;
        Student resultQuery2 = em.createQuery("select p from Student p where p.album="+ album +"",Student.class).getResultList().get(0);
        System.out.println("SUbejcts for "+album+": ");
        resultQuery2.getRoom().getSubs().forEach( arg -> {
            System.out.println(arg);
        });
        System.out.println();

        String secName = "Matematowska";
        Teacher resultQuery3 = em.createQuery("select p from Teacher p where p.secondName='"+secName +"'",Teacher.class).getResultList().get(0);
        System.out.println("Students for "+secName+": ");
        resultQuery3.getSubjects().forEach( arg -> {
            //System.out.println(arg);
            arg.getClasses().getStudents().forEach( argg -> {
                System.out.println(argg);
            });
        });
        System.out.println();

    }

}
