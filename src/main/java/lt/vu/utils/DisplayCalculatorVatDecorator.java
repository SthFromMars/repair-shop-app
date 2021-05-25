package lt.vu.utils;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
@Dependent
public class DisplayCalculatorVatDecorator implements DisplayCalculator{
    @Inject
    @Delegate
    @Any
    DisplayCalculator displayCalculator;

    public int calculate(int price){
        return (int) (displayCalculator.calculate(price)*1.21);
    };
}
