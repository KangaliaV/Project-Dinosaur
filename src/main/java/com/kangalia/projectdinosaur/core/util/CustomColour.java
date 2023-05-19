package com.kangalia.projectdinosaur.core.util;

import java.awt.*;

public class CustomColour {

    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(1f, 1f, 1f);

    public float a;
    public float r;
    public float g;
    public float b;

    public CustomColour() {
        this(1f, 1f, 1f);
    }

    public CustomColour(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1f;
    }

    public CustomColour(int red, int green, int blue) {
        this(red / 255f, green / 255f, blue / 255f);
    }

    public CustomColour(CustomColour copy) {
        this(copy.r, copy.g, copy.b);
    }

    // Average this color with another, weighted
    public void average(CustomColour c, float weight) {
        r = r * (1 - weight) + c.r * weight;
        g = g * (1 - weight) + c.g * weight;
        b = b * (1 - weight) + c.b * weight;
        a = a * (1 - weight) + c.a * weight;
        clamp();
    }

    // Clamp values to be between 0 and 1
    private void clamp() {
        r = Math.max(0f, Math.min(r, 1f));
        g = Math.max(0f, Math.min(g, 1f));
        b = Math.max(0f, Math.min(b, 1f));
        a = Math.max(0f, Math.min(a, 1f));
    }
}
