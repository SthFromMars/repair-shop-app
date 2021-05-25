package lt.vu.persistence;

import lt.vu.entities.Job;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.RollbackException;
import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class JobsDAO {

    @Inject
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRED)
    public void persist(Job job) throws RollbackException{
        this.em.persist(job);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void persist2(Job job) throws RollbackException {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {}
        this.em.persist(job);
    }

    public Job findOne(Integer id){
        return em.find(Job.class, id);
    }

    public Job update(Job job){
        return em.merge(job);
    }
}
