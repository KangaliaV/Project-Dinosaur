package com.kangalia.projectdinosaur.core.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomNumGen {

    private final Random rng;

    public RandomNumGen() {
        SecureRandom sr = new SecureRandom();
        Random r = new Random();
        r.setSeed(sr.nextInt());
        this.rng = r;
    }

    public int nextInt() {
        return rng.nextInt();
    }

    public double nextDouble() {
        return rng.nextDouble();
    }

    public int nextInt(int bound) {
        return rng.nextInt(bound);
    }
}
