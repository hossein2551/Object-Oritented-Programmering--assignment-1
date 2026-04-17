package org.example;

import org.example.services.ValidationService;

/**
 * Represents a moon orbiting a planet.
 */
public class Moon extends HeavenlyBody {

  private double orbitRadius;
  private final ValidationService validator;

  /**
   * Creates a moon with a name and a radius.
   * Also checks if the radius is within allowed range.
   *
   * @param name   the name of the moon
   * @param radius the radius of the moon
   * @throws IllegalArgumentException if radius is invalid
   */
  
  public Moon(String name, double radius) {
    super(name, radius);
    this.validator = new ValidationService();

    if (!validator.isValidMoonRadius(radius)) {
      throw new IllegalArgumentException("Invalid moon radius: " + radius);
    }
  }

  /**
   * Creates a moon with a name, radius, and orbit radius.
   * Both radius and orbit radius are validated.
   *
   * @param name         the name of the moon
   * @param radius       the radius of the moon
   * @param orbitRadius  how far the moon orbits from the planet
   * @throws IllegalArgumentException if radius or orbit radius is invalid
   */
  public Moon(String name, double radius, double orbitRadius) {
    this(name, radius);
    setOrbitRadius(orbitRadius);
  }

  /**
   * Sets the orbit radius for the moon.
   *
   * @param orbitRadius the distance from the planet
   * @throws IllegalArgumentException if orbit radius is invalid
   */
  public void setOrbitRadius(double orbitRadius) {
    if (!validator.isValidOrbitRadius(orbitRadius)) {
      throw new IllegalArgumentException("Invalid orbit radius for moon: " + orbitRadius);
    }
    this.orbitRadius = orbitRadius;
  }

  public double getOrbitRadius() {
    return orbitRadius;
  }
}
