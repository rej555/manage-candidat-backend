package procheacksa.students.entites;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Candidateur implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
     private Long id;
     private String name;
     private String prenom;
     private String email;
     private String phone;
     private String nivetude;
     private int nmbrexp;
     private String derempleure;

    public Candidateur() {
    }

    public Candidateur(String name, String prenom, String email, String phone, String nivetude, int nmbrexp, String derempleure) {
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.nivetude = nivetude;
        this.nmbrexp = nmbrexp;
        this.derempleure = derempleure;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNivetude() {
        return nivetude;
    }

    public void setNivetude(String nivetude) {
        this.nivetude = nivetude;
    }

    public int getNmbrexp() {
        return nmbrexp;
    }

    public void setNmbrexp(int nmbrexp) {
        this.nmbrexp = nmbrexp;
    }

    public String getDerempleure() {
        return derempleure;
    }

    public void setDerempleure(String derempleure) {
        this.derempleure = derempleure;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nivetude='" + nivetude + '\'' +
                ", nmbrexp=" + nmbrexp +
                ", derempleure='" + derempleure + '\'' +
                '}';
    }
}
