import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static utils.GraphHitChecker.checkHit;

public class GraphTest {
    @Test
    void checkIfOnSectorEdge() {
        // убейте меня, я не хочу так дальше жить
        assertTrue(checkHit((float)sqrt(2)/2,(float)-sqrt(2)/2,1));
    }

    @Test
    void checkIfOnSector() {
        assertTrue(checkHit(-0.5f,-0.5f,1));
    }

    @Test
    void checkIfNotOnSector() {
        assertFalse(checkHit(2,-2,1));
    }

    @Test
    void checkIfOnRectEdge() {
        assertTrue(checkHit(0.8f,0.5f,1));
    }

    @Test
    void checkIfOnRect() {
        assertTrue(checkHit(0.1f,0.1f,1));
    }

    @Test
    void checkIfNotOnRect() {
        assertFalse(checkHit(0.5f,1,1));
    }

    @Test
    void checkIfOnTriangleEdge() {
        assertTrue(checkHit(-0.25f,-0.25f,1));
    }

    @Test
    void checkIfOnTriangle() {
        assertTrue(checkHit(-0.1f,-0.1f,1));
    }

    @Test
    void checkIfNotOnTriangle() {
        assertFalse(checkHit(-1,1,1));
    }
}
