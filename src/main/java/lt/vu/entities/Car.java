package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select t from Car as t")
})
@Getter @Setter
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String numberplate;

    @Column
    String numberplate2;

    @Column
    String name;

    @JsonbTransient
    @OneToMany(mappedBy = "car")
    Set<Job> jobs;
}
