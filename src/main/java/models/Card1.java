package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Card1 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_card",
            joinColumns = @JoinColumn(name = "card1_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;

    public Card1(String number) {
        this.number = number;
    }
}
