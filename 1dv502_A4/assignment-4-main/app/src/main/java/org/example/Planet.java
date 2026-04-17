package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.services.ValidationService;


/**
 * Represents a planet in a solar system.
 * A planet has a name, radius, orbit radius, and can have moons.
 */
public class Planet extends HeavenlyBody {

  private double orbitRadius;
  private final List<Moon> moons;
  private final ValidationService validationService;
  /**
   * Creates a planet with a name, radius, and orbit distance.
   * It also checks if the values are valid.
   *
   * @param name         the name of the planet
   * @param radius       the size of the planet
   * @param orbitRadius  how far it orbits from the star
   * @throws IllegalArgumentException if the radius or orbit is invalid
   */
  
  public Planet(String name, double radius, double orbitRadius) {
    super(name, radius);
    this.moons = new ArrayList<>();
    this.validationService = new ValidationService();

    if (!validationService.isValidPlanetRadius(radius)) {
      throw new IllegalArgumentException("Planet radius is out of valid range.");
    }

    setOrbitRadius(orbitRadius);
  }

  /**
   * Sets the orbit distance of the planet from the star.
   *
   * @param orbitRadius the orbit distance
   * @throws IllegalArgumentException if the orbit is invalid
   */
  public void setOrbitRadius(double orbitRadius) {
    if (!validationService.isValidOrbitRadius(orbitRadius)) {
      throw new IllegalArgumentException("Orbit radius is out of valid range.");
    }
    this.orbitRadius = orbitRadius;
  }

  public double getOrbitRadius() {
    return orbitRadius;
  }

  /**
   * Adds a moon to this planet.
   * The moon's orbit is checked to make sure it's not too close.
   *
   * @param moon the moon to add
   * @throws IllegalArgumentException if the moon orbit is too close
   */
  public void addMoon(Moon moon) {
    if (!validationService.isValidMoonOrbit(this, moon)) {
      throw new IllegalArgumentException("Moon orbit radius is too close to the planet.");
    }
    moons.add(moon);
  }

  public void removeMoon(Moon moon) {
    moons.remove(moon);
  }

  public List<Moon> getMoons() {
    return moons;
  }
}
