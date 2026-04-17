package org.example;

import org.example.services.ValidationService;

/**
 * Represents a star in a solar system.
 * A star has a name and radius.
 */
public class Star extends HeavenlyBody {

  private final ValidationService validator;
  
  /**
   * Creates a new star with the given name and radius.
   *
   * @param name   the name of the star
   * @param radius the size (radius) of the star
   * @throws IllegalArgumentException if the radius is not valid
   */

  public Star(String name, double radius) {
    super(name, radius);
    this.validator = new ValidationService();

    if (!validator.isValidStarRadius(radius)) {
      throw new IllegalArgumentException("Invalid star radius: " + radius);
    }
  }
}
