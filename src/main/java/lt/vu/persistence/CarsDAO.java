package lt.vu.persistence;

import lombok.Getter;
import lt.vu.entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CarsDAO {

    @Inject
    @Getter
    private EntityManager em;

    public List<Car> loadAll() {
        return em.createNamedQuery("Car.findAll", Car.class).getResultList();
    }

    public void persist(Car car){
        this.em.persist(car);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void addCar(Car car){
        this.em.persist(car);
    }

    public Car findOne(Integer id) {
        return em.find(Car.class, id);
    }
}
