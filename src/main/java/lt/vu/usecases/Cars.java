package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.interceptors.MeasureInvocation;
import lt.vu.persistence.CarsDAO;
import lt.vu.utils.NumberplateProcessing;
import lt.vu.utils.TimeUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Cars {
    @EJB
    private TimeUtils timeUtils;

    @Inject
    private CarsDAO carsDAO;

    @Inject
    private NumberplateProcessing numberplateProcessing;

    @Getter
    @Setter
    private Car carToCreate = new Car();

    @Getter
    @Setter
    private String time = "loading";

    @Getter
    private List<Car> allCars;

    @PostConstruct
    public void init(){
        loadAllCars();
    }

    @Transactional
    public String createCar(){
        carToCreate.setNumberplate(numberplateProcessing.process(carToCreate.getNumberplate()));
        this.carsDAO.persist(carToCreate);
        return "index?faces-redirect=true";
    }

    public String refresh(){
        return null;
    }

    public void printTime(){
        timeUtils.longTime();
    }

    private void loadAllCars(){
        this.allCars = carsDAO.loadAll();
    }
}
