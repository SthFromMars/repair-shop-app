package lt.vu.usecases;

import lombok.Getter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.JobMapper;
import lt.vu.mybatis.dao.PartMapper;
import lt.vu.mybatis.model.Job;
import lt.vu.mybatis.model.Part;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class PartsForJobMyBatis {
    @Inject
    private JobMapper jobMapper;

    @Inject
    private PartMapper partMapper;

    @Getter
    private Job job;

    @Getter
    private List<Part> unassignedParts = new ArrayList<>();

    @Getter
    private List<Part> assignedParts;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int jobId = Integer.parseInt(requestParameters.get("jobId"));
        this.job = jobMapper.selectByPrimaryKey(jobId);
        this.assignedParts = partMapper.selectByJobId(jobId);
        List<Part> allParts = partMapper.selectAll();
        for (Part part: allParts) {
            if(!assignedParts.contains(part)){
                this.unassignedParts.add(part);
            }
        }
    }

    @Transactional
    @LoggedInvocation
    public String assignPartToJob(int partId) {
        System.out.println("assignPartToJob");
        partMapper.insertPartToJob(partId, this.job.getId());
        return "job?faces-redirect=true&jobId=" + this.job.getId();
    }
}
