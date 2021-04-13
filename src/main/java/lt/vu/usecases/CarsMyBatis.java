package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.model.Car;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CarsMyBatis {
    @Inject
    private CarMapper carMapper;

    @Getter
    private List<Car> allCars;

    @Getter @Setter
    private Car carToCreate = new Car();

    @PostConstruct
    public void init() {
        this.loadAllCars();
    }

    private void loadAllCars() {
        this.allCars = carMapper.selectAll();
    }

    @Transactional
    public String createCar() {
        carMapper.insert(carToCreate);
        return "/myBatis/index?faces-redirect=true";
    }
}
