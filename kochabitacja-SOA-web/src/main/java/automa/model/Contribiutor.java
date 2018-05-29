//package automa.model;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//
//import javax.ejb.Stateless;
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.ArrayList;
//import java.util.List;
//
//@Stateless
//@Entity
//@Data
////@RequiredArgsConstructor
////@NoArgsConstructor
//@NamedQueries({@NamedQuery(name = Contribiutor.Contribiutor_BY_ID, query = "SELECT a from Contribiutor a where a.id = :id")})
//public class Contribiutor {
//
//    public static final String Contribiutor_BY_ID = "contribiutor_by_id";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @NotNull
//    private String name;
//
//    @JsonIgnore
//    @ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//            name = "Automa_contributors",
//            joinColumns = { @JoinColumn(name = "contribiutor_id") },
//            inverseJoinColumns = { @JoinColumn(name = "automa_id") }
//    )
//    private List<Automa> automas;
//
//    public Contribiutor() {
//        this.automas = new ArrayList<>();
//    }
//
////    public Contribiutor(String name) {
////        this.name = name;
////    }
////
////    public Contribiutor(String name, List<Automa> automas) {
////        this.name = name;
////        this.automas = automas;
////    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Automa> getAutomas() {
//        return automas;
//    }
//
//    public void setAutomas(List<Automa> automas) {
//        this.automas = automas;
//    }
//
//    @Override
//    public String toString() {
//        return "Contribiutor{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", automas=" + automas +
//                '}';
//    }
//}
