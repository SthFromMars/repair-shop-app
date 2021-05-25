package lt.vu.utils;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NumberplateProcessing {
    public String process(String numberplate){
        return numberplate.toUpperCase();
    }
}
