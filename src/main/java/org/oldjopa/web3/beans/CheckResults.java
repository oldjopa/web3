package org.oldjopa.web3.beans;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import org.oldjopa.web3.db.DBHandler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.oldjopa.web3.jmx.PointStatisticsMXBean;
import org.oldjopa.web3.listners.MBeanRegistry;
import org.oldjopa.web3.utils.CheckResult;
import org.oldjopa.web3.utils.Point;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

@Named("results")
@ManagedBean
@ApplicationScoped
public class CheckResults extends NotificationBroadcasterSupport implements Serializable, PointStatisticsMXBean {
    private ArrayList<CheckResult> results = DBHandler.getResults();
    private Point point = new Point();
    private long msgCouner = 0;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object unused) {
        MBeanRegistry.registerBean(this, "checkResults");
    }

    public ArrayList<CheckResult> getResults(){
        ArrayList<CheckResult> reversed = new ArrayList<>(results);
        Collections.reverse(reversed);
        return reversed;
    }

    private boolean isInCanvasArea(CheckResult checkResult){
        return (checkResult.getX() >= -3)&(checkResult.getX() <= 3)&(checkResult.getY() >= -3)&(checkResult.getY() <= 3);
    }

    public void addPoint(){
//        System.out.println(point);
        CheckResult check = point.check();
        results.add(check);
        if(!isInCanvasArea(check)) {
            System.out.println("notification");
            Notification notification = new Notification(
                    "Outside of area",
                    getClass().getSimpleName(),
                    msgCouner++,
                    String.format("The point %f %f %f is outside of area.", check.getX(), check.getY(), check.getR())
            );
            sendNotification(notification);
        }
        DBHandler.addResult(check);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    public void clear(){
        results.clear();
        DBHandler.clearResults();
    }

    @Override
    public long getHitsCounter() {
        return results.stream().filter(CheckResult::getHit).count();
    }

    @Override
    public long getPointCounter() {
        return results.size();
    }
}
