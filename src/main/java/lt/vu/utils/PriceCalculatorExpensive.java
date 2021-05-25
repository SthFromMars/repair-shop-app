package lt.vu.utils;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PriceCalculatorExpensive implements PriceCalculator{
    public int calculatePrice(int partsPrice){
        return (int) (partsPrice*1.5);
    }
}
