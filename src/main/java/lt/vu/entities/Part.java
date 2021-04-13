package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@Getter @Setter
public class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    int price;

    @Column
    String name;

    @ManyToMany(mappedBy = "parts")
    Set<Job> jobs;
}
