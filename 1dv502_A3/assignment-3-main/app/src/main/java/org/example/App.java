package org.example;

import org.example.solitares.Moon;

public class App {

    public static void main(String[] args) {
        App theApp = new App();
        theApp.sunPlanetMoon();
    }

    private void sunPlanetMoon() {
        Moon[] moons = {
            new Moon("Moon", 1737),
            new Moon("Phobos", 12),
            new Moon("Io", 1821),
            new Moon("Europa", 1560),
            new Moon("Ganymede", 2634),
            new Moon("Callisto", 2410),
            new Moon("Mimas", 198),
            new Moon("Enceladus", 252),
            new Moon("Tethys", 533),
            new Moon("Dione", 561),
            new Moon("Rhea", 764),
            new Moon("Titan", 2575)
        };

        System.out.println("Solar System:\n");

        // Placeholder, Planet & Star kommer läggas till senare
        System.out.println("  Planets:");
        System.out.println("    (not implemented)");

        System.out.println("\n  Some moons:");
        for (Moon moon : moons) {
            System.out.println("    " + moon);
        }
    }
}
