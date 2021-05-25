package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Job;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.JobsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Model
public class JobInfo implements Serializable {

    @Inject
    private JobsDAO jobsDAO;

    @Getter
    @Setter
    private Job job;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int jobId = Integer.parseInt(requestParameters.get("jobId"));
        this.job = jobsDAO.findOne(jobId);
    }

    @LoggedInvocation
    @Transactional
    public String updateJob() {
            jobsDAO.persist(job);
            return "job?faces-redirect=true&jobId=" + this.job.getId();
    }

    @LoggedInvocation
    @Transactional
    public String updateJob2() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jobsDAO.persist(job);
            return "job?faces-redirect=true&jobId=" + this.job.getId();
    }
}
