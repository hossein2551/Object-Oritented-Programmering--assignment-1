package org.example;

/**
 * Represents a generic celestial body with a name and radius.
 * Used as a base class for more specific body types like stars, planets, and moons.
 */
public abstract class HeavenlyBody {

  private String name;
  private double radius;

  public HeavenlyBody(String name, double radius) {
    setName(name);
    setRadius(radius);
  }

  public String getName() {
    return name;
  }

  public double getRadius() {
    return radius;
  }

  /**
 * Sätter namnet på himlakroppen.
 * Kontrollerar att namnet inte är null eller tomt.
 *
 * @param name namnet som ska sättas
 * @throws IllegalArgumentException om namnet är null eller tomt
 */

  public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty.");
    }
    this.name = name.trim();
  }

  /**
 * Sätter radien för himlakroppen.
 * Radien måste vara ett positivt tal.
 *
 * @param radius radien som ska sättas (i kilometer)
 * @throws IllegalArgumentException om radien är mindre än eller lika med 0
 */
  public void setRadius(double radius) {
    if (radius <= 0) {
      throw new IllegalArgumentException("Radius must be positive.");
    }
    this.radius = radius;
  }
}
