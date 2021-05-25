package lt.vu.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class PriceCalculatorDiscount implements PriceCalculator{
    public int calculatePrice(int partsPrice){
        return (int) (partsPrice*0.9);
    }
}
