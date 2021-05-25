package lt.vu.utils;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisplayCalculatorAdminCosts implements DisplayCalculator{
    public int calculate(int price){
        return price+30;
    }
}
