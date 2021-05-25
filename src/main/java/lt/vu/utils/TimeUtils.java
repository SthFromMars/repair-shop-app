package lt.vu.utils;

import lt.vu.interceptors.MeasureInvocation;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Stateless
public class TimeUtils implements Serializable {

    @Asynchronous
    @MeasureInvocation
    public void longTime(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        setTime2((new Timestamp(System.currentTimeMillis())).toString());
        System.out.println((new Timestamp(System.currentTimeMillis())).toString());
    }
}
