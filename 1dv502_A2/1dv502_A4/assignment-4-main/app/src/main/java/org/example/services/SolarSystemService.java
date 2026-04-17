package org.example.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.example.Moon;
import org.example.Planet;
import org.example.SolarSystem;
import org.example.Star;

/**
 * Handles operations related to solar systems such as adding planets and moons.
 */
public class SolarSystemService {

  private final List<SolarSystem> solarSystems;
  private final ValidationService validator;

  public SolarSystemService(List<SolarSystem> solarSystems) {
    this.solarSystems = solarSystems;
    this.validator = new ValidationService();
  }

  /**
 * Creates a new solar system with a star.
 *
 * @param name the name of the solar system
 * @param starName the name of the star
 * @param radius the radius of the star
 */
  public void createSolarSystem(String name, String starName, double radius) {
    if (!validator.isValidStarRadius(radius)) {
      System.out.println("Invalid star radius.");
      return;
    }

    Star star = new Star(starName, radius);
    SolarSystem system = new SolarSystem(name, star);
    solarSystems.add(system);
    System.out.println("Solar system '" + name + "' created successfully.");
  }


  /**
 * Adds a new planet to an existing solar system.
 *
 * @param systemName the name of the solar system
 * @param planetName the name of the planet
 * @param radius the radius of the planet
 * @param orbitRadius the orbit distance of the planet
 */
  public void addPlanetToSolarSystem(String systemName, 
      String planetName, double radius, double orbitRadius) {
    SolarSystem system = findSolarSystem(systemName);
    if (system == null) {
      System.out.println("Solar system not found.");
      return;
    }

    if (!validator.isValidPlanetRadius(radius)) {
      System.out.println("Invalid planet radius.");
      return;
    }

    if (!validator.isValidOrbitRadius(orbitRadius)) {
      System.out.println("Invalid orbit radius.");
      return;
    }

    Planet newPlanet = new Planet(planetName, radius, orbitRadius);
    system.getPlanets().add(newPlanet);
  }


  /**
 * Adds a moon to a planet in a solar system.
 *
 * @param systemName the name of the solar system
 * @param planetName the name of the planet
 * @param moonName the name of the moon
 * @param radius the radius of the moon
 * @param orbitRadius the orbit distance of the moon
 */
  public void addMoonToPlanet(String systemName, String planetName, 
      String moonName, double radius, double orbitRadius) {
    SolarSystem system = findSolarSystem(systemName);
    if (system == null) {
      System.out.println("Solar system not found.");
      return;
    }

    Planet targetPlanet = null;
    for (Planet planet : system.getPlanets()) {
      if (planet.getName().equalsIgnoreCase(planetName)) {
        targetPlanet = planet;
        break;
      }
    }

    if (targetPlanet == null) {
      System.out.println("Planet not found.");
      return;
    }

    if (!validator.isValidMoonRadius(radius)) {
      System.out.println("Invalid moon radius.");
      return;
    }

    if (!validator.isValidOrbitRadius(orbitRadius)) {
      System.out.println("Invalid moon orbit radius.");
      return;
    }

    Moon newMoon = new Moon(moonName, radius);
    newMoon.setOrbitRadius(orbitRadius);

    if (!validator.isValidMoonOrbit(targetPlanet, newMoon)) {
      System.out.println("Moon orbit is too close to the planet.");
      return;
    }

    targetPlanet.addMoon(newMoon);
  }

  /**
 * Prints a list of all available solar systems.
 */
  public void listSolarSystems() {
    if (solarSystems.isEmpty()) {
      System.out.println("No solar systems found.");
      return;
    }

    System.out.println("Available Solar Systems:");
    for (SolarSystem system : solarSystems) {
      System.out.println("- " + system.getName());
    }
  }

  public List<SolarSystem> getSolarSystems() {
    return new ArrayList<>(solarSystems);
  }

  public void updateSolarSystems(List<SolarSystem> updatedSystems) {
    solarSystems.clear();
    solarSystems.addAll(updatedSystems);
  }

  /**
 * Shows the planets and moons in a solar system.
 * You can choose to sort them by size or orbit distance.
 *
 * @param systemName the name of the solar system
 * @param sortBySize true to sort by size, false to sort by orbit
 */

  public void showSolarSystemDetailsSorted(String systemName, boolean sortBySize) {
    SolarSystem system = findSolarSystem(systemName);
    if (system == null) {
      System.out.println("System not found.");
      return;
    }

    List<Planet> planets = new ArrayList<>(system.getPlanets());
    planets.sort(sortBySize
                ? Comparator.comparingDouble(Planet::getRadius)
                : Comparator.comparing(Planet::getOrbitRadius));

    for (Planet planet : planets) {
      System.out.println(planet.getName() + ": " + planet.getRadius()
            + "km | Orbit: " + planet.getOrbitRadius());
      for (Moon moon : planet.getMoons()) {
        System.out.println("  - " + moon.getName()
              + ": " + moon.getRadius() + "km | Orbit: " + moon.getOrbitRadius());
      }
    }
  }


  /**
 * Deletes a planet or moon by name in a solar system.
 *
 * @param systemName the name of the solar system
 * @param bodyName the name of the planet or moon to delete
 * @return true if something was deleted, false otherwise
 */
  public boolean deleteCelestialBody(String systemName, String bodyName) {
    SolarSystem system = findSolarSystem(systemName);
    if (system == null) {
      return false;
    }

    return system.getPlanets().removeIf(p -> p.getName().equalsIgnoreCase(bodyName))
                || system.getPlanets().stream().anyMatch(
                        p -> p.getMoons().removeIf(m -> m.getName().equalsIgnoreCase(bodyName)));
  }

  /**
 * Moves a moon from one planet to another in the same solar system.
 *
 * @param systemName the name of the solar system
 * @param moonName the name of the moon to move
 * @param fromPlanet the name of the planet the moon is currently orbiting
 * @param toPlanet the name of the planet to move the moon to
 */
  public void moveMoon(String systemName, String moonName, String fromPlanet, String toPlanet) {
    SolarSystem system = findSolarSystem(systemName);
    if (system == null) {
      return;
    }

    Planet source = null;
    Moon moonToMove = null;

    for (Planet p : system.getPlanets()) {
      if (p.getName().equalsIgnoreCase(fromPlanet)) {
        for (Moon m : p.getMoons()) {
          if (m.getName().equalsIgnoreCase(moonName)) {
            moonToMove = m;
            source = p;
            break;
          }
        }
      }
    }

    if (moonToMove == null || source == null) {
      return;
    }

    for (Planet p : system.getPlanets()) {
      if (p.getName().equalsIgnoreCase(toPlanet)) {
        source.getMoons().remove(moonToMove);
        p.addMoon(moonToMove);
        return;
      }
    }
  }

  public boolean deleteSolarSystem(String name) {
    return solarSystems.removeIf(system -> system.getName().equalsIgnoreCase(name));
  }

  private SolarSystem findSolarSystem(String name) {
    for (SolarSystem system : solarSystems) {
      if (system.getName().equalsIgnoreCase(name)) {
        return system;
      }
    }
    return null;
  }

  public boolean deleteSolarSystemIgnoreCase(String name) {
    return deleteSolarSystem(name);
  }

  public void addPlanetToSolarSystemIgnoreCase(String system, 
      String planet, double radius, double orbit) {
    addPlanetToSolarSystem(system, planet, radius, orbit);
  }

  public void addMoonToPlanetIgnoreCase(String system,
      String planet, String moon, double radius, double orbit) {
    addMoonToPlanet(system, planet, moon, radius, orbit);
  }

  public void moveMoonIgnoreCase(String system, String moon, String from, String to) {
    moveMoon(system, moon, from, to);
  }

  public void showSolarSystemDetailsSortedIgnoreCase(String system, boolean bySize) {
    showSolarSystemDetailsSorted(system, bySize);
  }

  public boolean deleteCelestialBodyIgnoreCase(String system, String name) {
    return deleteCelestialBody(system, name);
  }
}
