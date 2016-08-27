package com.projectvalis.altk.util;

import java.util.concurrent.ThreadLocalRandom;

import org.jbox2d.common.Vec2;

public class RandomVectorUtils {

    /**
     * returns getRandomFloat() * multiplier
     * 
     * @param multiplier
     * @return
     */
    public static Vec2 getRandomVector(int xMultiplier, int yMultiplier) {
        float xVal = getRandomFloat() * xMultiplier;
        float yVal = getRandomFloat() * yMultiplier;
        return new Vec2(xVal, yVal);
    }

    /**
     * returns (.nextFloat() * 10 ) * (1/2 chance of *= -1)
     * 
     * @return
     */
    public static float getRandomFloat() {
        float randomFloat = ThreadLocalRandom.current().nextFloat() * 10;
        float coinFlip = ThreadLocalRandom.current().nextInt(2);
        if (coinFlip > 0) { randomFloat *= -1; }
        return randomFloat;
    }

    /**
     * bound is exclusive
     * 
     * @param bound
     * @return
     */
    public static int getRandomBoundedInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
