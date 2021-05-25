package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Interceptor
@MeasureInvocation
public class MethodMeasurer implements Serializable{
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        LocalTime start = LocalTime.now();
        Object returnObject = context.proceed();
        System.out.println(start.until(LocalTime.now(), ChronoUnit.MICROS));
        return returnObject;
    }
}
