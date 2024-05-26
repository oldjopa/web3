package org.oldjopa.web3.beans;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.concurrent.ManagedExecutorDefinition;
import org.oldjopa.web3.db.DBHandler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.oldjopa.web3.jmx.MeanIntervalStatistics;
import org.oldjopa.web3.jmx.MeanIntervalStatisticsMXBean;
import org.oldjopa.web3.jmx.PointStatisticsMXBean;
import org.oldjopa.web3.utils.CheckResult;
import org.oldjopa.web3.utils.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

@Named("results")
@ManagedBean
@ApplicationScoped
public class CheckResults implements Serializable {
    private ArrayList<CheckResult> results = DBHandler.getResults();
    private Point point = new Point();

    public ArrayList<CheckResult> getResults(){
        ArrayList<CheckResult> reversed = new ArrayList<>(results);
        Collections.reverse(reversed);
        return reversed;
    }

    public void addPoint(){
//        System.out.println(point);
        CheckResult check = point.check();
        results.add(check);
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

//    @Override
    public long getHitsCounter() {
        return results.stream().filter(CheckResult::getHit).count();
    }

//    @Override
    public long getPointCounter() {
        return results.size();
    }
}
