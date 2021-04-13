package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@Getter @Setter
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String description;

    @Column
    int estimate;

    @ManyToOne
    @JoinColumn(name="car_id")
    Car car;

    @ManyToMany
    @JoinTable(
            name = "job_part",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    Set<Part> parts;
}
