package utils;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
//@Table(name = "CheckResults")
public class CheckResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String successText;
    private Boolean hit;
    private String timeAccess;
    private long timeExecution;

    private Float x;
    private Float y;
    private Float r;

    public CheckResult() {
    }

    public CheckResult(Point point) {
        long timeStart =System.nanoTime();
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeAccess = currentDateTime.format(formatter);
        this.x = point.getX();
        this.y = point.getY();
        this.r = point.getR();
        hit = checkHit();
        timeExecution = (System.nanoTime() - timeStart) / 1000;
    }

    private boolean checkHit(){
        return (x >= 0 && y < 0 && x *x + y *y < r *r) ||
                (x >= 0 && y >= 0 && x < r && y < r / 2) ||
                (x < 0 && y < 0 && x + y + r/2 > 0);
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        System.out.println(x);
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getR() {
        return r;
    }

    public void setR(Float r) {
        this.r = r;
    }

    public String getSuccessText() {
        return successText;
    }

    public void setSuccessText(String successText) {
        this.successText = successText;
    }

    public Boolean getHit() {
        return hit;
    }

    public void setHit(Boolean hit) {
        this.hit = hit;
    }

    public String getTimeAccess() {
        return timeAccess;
    }

    public void setTimeAccess(String timeAccess) {
        this.timeAccess = timeAccess;
    }

    public long getTimeExecution() {
        return timeExecution;
    }

    public void setTimeExecution(long timeExecution) {
        this.timeExecution = timeExecution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
