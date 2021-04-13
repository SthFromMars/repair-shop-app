package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.entities.Job;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarsDAO;
import lt.vu.persistence.JobsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class JobsForCar implements Serializable {

    @Inject
    private CarsDAO carsDAO;

    @Inject
    private JobsDAO jobsDAO;

    @Getter
    @Setter
    private Car car;

    @Getter @Setter
    private Job jobToCreate = new Job();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int carId = Integer.parseInt(requestParameters.get("carId"));
        this.car = carsDAO.findOne(carId);
    }

    @Transactional
    @LoggedInvocation
    public String createJob() {
        jobToCreate.setCar(this.car);
        jobsDAO.persist(jobToCreate);
        return "car?faces-redirect=true&carId=" + this.car.getId();
    }

}
