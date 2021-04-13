package lt.vu.persistence;

import lt.vu.entities.Job;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class JobsDAO {

    @Inject
    private EntityManager em;

    public void persist(Job job){
        this.em.persist(job);
    }

    public Job findOne(Integer id){
        return em.find(Job.class, id);
    }

    public Job update(Job job){
        return em.merge(job);
    }

}
