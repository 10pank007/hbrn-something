package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment id
    private int id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private GENDER gender;

    @ElementCollection
    private List<String> skills;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_card1",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "card1_id")
    )
    private List<Card1> card1s;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_sg",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sg_id")
    )
    private List<Sungluses> sungluses;


    public User(String name) {
    }

    public User(String name, GENDER gender) {
        this.name = name;
        this.gender = gender;
    }

    public User(String name, GENDER gender, List<String> skills) {
        this.name = name;
        this.gender = gender;
        this.skills = skills;
    }

    public User(String name, GENDER gender, List<String> skills, Passport passport) {
        this.name = name;
        this.gender = gender;
        this.skills = skills;
        this.passport = passport;
    }

    public User(String name, GENDER gender, List<String> skills, Passport passport, List<Card1> card1s) {
        this.name = name;
        this.gender = gender;
        this.skills = skills;
        this.passport = passport;
        this.card1s = card1s;
    }

    public User(String name, GENDER gender, List<String> skills, Passport passport, List<Card1> card1s, List<Sungluses> sungluses) {
        this.name = name;
        this.gender = gender;
        this.skills = skills;
        this.passport = passport;
        this.card1s = card1s;
        this.sungluses = sungluses;
    }
}
