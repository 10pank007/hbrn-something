package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
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
}
