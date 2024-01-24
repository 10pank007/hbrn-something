package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sungluses implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_sg",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns  = @JoinColumn(name = "sg_id")
    )
    private List<User> users;

    public Sungluses(String brand) {
        this.brand = brand;
    }

    public Sungluses(String brand, List<User> users) {
        this.brand = brand;
        this.users = users;
    }
}
