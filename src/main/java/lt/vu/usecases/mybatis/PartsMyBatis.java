package lt.vu.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.PartMapper;
import lt.vu.mybatis.model.Part;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PartsMyBatis {
    @Inject
    private PartMapper partMapper;

    @Getter
    private List<Part> allParts;

    @Getter @Setter
    private Part partToCreate = new Part();

    @PostConstruct
    public void init() {
        this.loadAllParts();
    }

    private void loadAllParts() {
        this.allParts = partMapper.selectAll();
    }

    @Transactional
    public String createPart() {
        partMapper.insert(partToCreate);
        return "/myBatis/index?faces-redirect=true";
    }
}
