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
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import java.io.Serializable;
import java.util.Map;

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
    public String updateJob() {
        try {
            jobsDAO.persist(job);
        } catch (RollbackException e){
            if (e.getCause() instanceof OptimisticLockException) {
                System.out.println("OptimisticLockException");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                try {
                    jobsDAO.persist2(job);
                } catch (RollbackException rollbackException) {
                    return null;
                }
            }
        }
        return "job?faces-redirect=true&jobId=" + this.job.getId();
    }

    @LoggedInvocation
    public String updateJob2() {
        try {
            jobsDAO.persist2(job);
        } catch (RollbackException e){
            if (e.getCause() instanceof OptimisticLockException) {
                System.out.println("OptimisticLockException");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                try {
                    jobsDAO.persist2(job);
                } catch (RollbackException rollbackException) {
                    return null;
                }
            }
        }
        return "job?faces-redirect=true&jobId=" + this.job.getId();
    }
}
