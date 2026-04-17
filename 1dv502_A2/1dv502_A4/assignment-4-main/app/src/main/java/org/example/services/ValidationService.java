package org.example.services;

import org.example.Moon;
import org.example.Planet;

/**
 * Service class for validating celestial body properties.
 * Fully object-oriented design with no static methods.
 */
public class ValidationService {

  private final double minStarRadius = 100000;
  private final double maxStarRadius = 2000000;

  private final double minPlanetRadius = 500;
  private final double maxPlanetRadius = 70000;

  private final double minMoonRadius = 100;
  private final double maxMoonRadius = 3000;

  private final double minOrbitRadius = 1000;
  private final double maxOrbitRadius = 1_000_000_000;

  /**
     * Validates if the star radius is within allowed range.
     */
  public boolean isValidStarRadius(double radius) {
    return radius >= minStarRadius && radius <= maxStarRadius;
  }

  /**
     * Validates if the planet radius is within allowed range.
     */
  public boolean isValidPlanetRadius(double radius) {
    return radius >= minPlanetRadius && radius <= maxPlanetRadius;
  }

  /**
     * Validates if the moon radius is within allowed range.
     */
  public boolean isValidMoonRadius(double radius) {
    return radius >= minMoonRadius && radius <= maxMoonRadius;
  }

  /**
     * Validates if the orbit radius is within allowed range.
     */
  public boolean isValidOrbitRadius(double orbitRadius) {
    return orbitRadius >= minOrbitRadius && orbitRadius <= maxOrbitRadius;
  }

  /**
     * Validates that the moon's orbit is sufficiently far from the planet.
     * Requires at least 1000 km beyond the planet's surface.
     */
  public boolean isValidMoonOrbit(Planet planet, Moon moon) {
    return moon.getOrbitRadius() > (planet.getRadius() + 1000);
  }

  public double getMinStarRadius() {
    return minStarRadius; 
    
  }

  public double getMaxStarRadius() {
    return maxStarRadius; 
  }

  public boolean isValidName(String name) {
    return name != null && !name.trim().isEmpty();
  }

  public double getMinPlanetRadius() { 
    return minPlanetRadius;
  }

  public double getMaxPlanetRadius() {
    return maxPlanetRadius; 
  }

  public double getMinMoonRadius() {
    return minMoonRadius; 
  }

  public double getMaxMoonRadius() { 
    return maxMoonRadius; 
  }

  public double getMinOrbitRadius() { 
    return minOrbitRadius; 
  }

  public double getMaxOrbitRadius() { 
    return maxOrbitRadius; 
  }
  

  public boolean isValidRadius(double radius, double min, double max) {
    return radius >= min && radius <= max;
  }

  
}
