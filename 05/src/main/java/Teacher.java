import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String secondName;
    private Integer birthYear;

    @OneToOne(cascade = CascadeType.ALL)
    ClassRoom educator;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecturer")
    List<Subject> subjects;

    public Teacher(String firstName, String secondName, Integer birthYear) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthYear = birthYear;
    }

    public Teacher() {

    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public ClassRoom getEducator() {
        return educator;
    }

    public void setEducator(ClassRoom educator) {
        this.educator = educator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer age) {
        this.birthYear = age;
    }

}
