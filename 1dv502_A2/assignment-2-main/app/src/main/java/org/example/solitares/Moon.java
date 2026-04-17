package org.example.solitares;

public class Moon extends HeavenlyBody {
    public Moon(String name, int avgRadiusInKm) {
        super(name, avgRadiusInKm);
    }

    @Override
    protected void validateRadius(int radius) {
        if (radius < 6 || radius > 10000) {
            throw new IllegalArgumentException("Moon radius must be between 6km and 10000km");
        }
    }
}