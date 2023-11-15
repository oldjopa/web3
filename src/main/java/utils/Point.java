package utils;

import java.io.Serializable;

public class Point implements Serializable {
    private Float x=0F;
    private Float y=0F;
    private Float r=0F;

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

    @Override
    public String toString() {
        return String.format("%f, %f, %f", x,y,r);
    }

    public CheckResult check() {
        CheckResult result = new CheckResult(this);
        return result;
    }
}
