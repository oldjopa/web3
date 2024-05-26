package utils;

public class GraphHitChecker {
    public static boolean checkHit(float x, float y, float r){
        return (x >= 0 && y < 0 && x *x + y *y <= r *r) ||
                (x >= 0 && y >= 0 && x < r && y <= r / 2) ||
                (x < 0 && y < 0 && x + y + r/2 >= 0);
    }
}
