package beans;

import db.DBHandler;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import utils.CheckResult;
import utils.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Named("results")
@SessionScoped
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
}
