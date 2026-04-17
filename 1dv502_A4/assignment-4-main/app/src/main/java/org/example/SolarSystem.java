package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a solar system containing a star and a list of planets.
 */
public class SolarSystem {

  /** The name of the solar system. */
  private String name;

  /** The central star of the solar system. */
  private Star star;

  /** The list of planets orbiting the star. */
  private final List<Planet> planets;

  /**
   * Constructs a new solar system with a name and central star.
   *
   * @param name the name of the solar system
   * @param star the central star
   */
  public SolarSystem(String name, Star star) {
    setName(name);
    setStar(star);
    this.planets = new ArrayList<>();
  }

  /**
   * Gets the name of the solar system.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the solar system.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Solar system name cannot be empty.");
    }
    this.name = name.trim();
  }

  /**
   * Gets the central star of the solar system.
   *
   * @return the star
   */
  public Star getStar() {
    return star;
  }

  /**
   * Sets the central star.
   *
   * @param star the star to set
   */
  public void setStar(Star star) {
    if (star == null) {
      throw new IllegalArgumentException("Star cannot be null.");
    }
    this.star = star;
  }

  /**
   * Returns the list of planets in the solar system.
   * (Writable list – do not wrap in unmodifiable)
   *
   * @return list of planets
   */
  public List<Planet> getPlanets() {
    return planets;
  }

  /**
   * Adds a planet to the solar system.
   *
   * @param planet the planet to add
   */
  public void addPlanet(Planet planet) {
    if (planet == null) {
      throw new IllegalArgumentException("Planet cannot be null.");
    }
    planets.add(planet);
  }
}
