import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public ClassRoom getClasses() {
        return classes;
    }

    public void setClasses(ClassRoom classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }

    @ManyToOne(cascade = CascadeType.ALL)
    Teacher lecturer;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    ClassRoom classes;

    public Subject(String name) {
        this.name = name;
    }

    public Subject() {

    }

    public Teacher getLecturer() {
        return lecturer;
    }

    public void setLecturer(Teacher lecturer) {
        this.lecturer = lecturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
