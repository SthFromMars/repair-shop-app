package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.persistence.CarsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Cars {

    @Inject
    private CarsDAO carsDAO;

    @Getter
    @Setter
    private Car carToCreate = new Car();

    @Getter
    private List<Car> allCars;

    @PostConstruct
    public void init(){
        loadAllCars();
    }

    @Transactional
    public String createCar(){
        this.carsDAO.persist(carToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllCars(){
        this.allCars = carsDAO.loadAll();
    }
}
