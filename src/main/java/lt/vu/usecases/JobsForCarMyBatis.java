package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.dao.JobMapper;
import lt.vu.mybatis.model.Car;
import lt.vu.mybatis.model.Job;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class JobsForCarMyBatis {
    @Inject
    private CarMapper carMapper;

    @Inject
    private JobMapper jobMapper;

    @Getter
    private Car car;

    @Getter
    private List<Job> jobs;

    @Getter @Setter
    private Job jobToCreate = new Job();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int carId = Integer.parseInt(requestParameters.get("carId"));
        this.car = carMapper.selectByPrimaryKey(carId);
        this.jobs = jobMapper.selectByCarId(carId);
    }

    @Transactional
    @LoggedInvocation
    public String createJob() {
        jobToCreate.setCarId(this.car.getId());
        jobMapper.insert(jobToCreate);
        return "car?faces-redirect=true&carId=" + this.car.getId();
    }
}
