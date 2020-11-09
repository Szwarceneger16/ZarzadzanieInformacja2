import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class ClassRoom implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer level;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    List<Student> students;

    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classes",orphanRemoval = true)
    List<Subject> subs;

    @OneToOne(cascade = CascadeType.ALL)
    Teacher educator;

    public List<Subject> getSubs() {
        return subs;
    }

    public void setSubs(List<Subject> subs) {
        this.subs = subs;
    }

    public Teacher getEducator() {
        return educator;
    }

    public void setEducator(Teacher educator) {
        this.educator = educator;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
