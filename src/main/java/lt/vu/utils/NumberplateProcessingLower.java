package lt.vu.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class NumberplateProcessingLower extends NumberplateProcessing{
    public String process(String numberplate){
        return numberplate.toLowerCase();
    }
}
