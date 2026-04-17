package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.services.FileHandler;
import org.example.services.SolarSystemService;

/**
 * Handles loading, saving, and displaying solar systems.
 */
public class SolarRegistrySystem {

  /** Service for managing solar system data. */
  private final SolarSystemService solarSystemService;

  /** File handler for data persistence. */
  private final FileHandler fileHandler;

  /**
   * Constructs a new registry system with injected service and file handler.
   *
   * @param solarSystemService the solar system management service
   * @param fileHandler        the file handler to use for saving/loading
   */
  public SolarRegistrySystem(SolarSystemService solarSystemService, FileHandler fileHandler) {
    this.solarSystemService = solarSystemService;
    this.fileHandler = fileHandler;
  }

  /**
   * Convenience constructor for creating a system with default configuration.
   */
  public SolarRegistrySystem() {
    this(new SolarSystemService(new ArrayList<>()), new FileHandler("solarsystems.data"));
  }



  /**
   * Saves current solar systems to a file using the file handler.
   */
  public void saveSolarSystems() {
    if (solarSystemService.getSolarSystems().isEmpty()) {
      System.out.println(" No solar systems to save. Skipping write to file.");
      return;
    }

    fileHandler.saveData(solarSystemService.getSolarSystems());
  }


  /**
   * Prints all loaded solar systems with their stars, planets, and moons.
   */
  public void printSolarSystems() {
    List<SolarSystem> systems = solarSystemService.getSolarSystems();

    if (systems.isEmpty()) {
      System.out.println("No solar systems loaded.");
      return;
    }

    for (SolarSystem system : systems) {
      System.out.println("Solar System: " + system.getName());
      System.out.println("Star: " + system.getStar().getName());

      for (Planet planet : system.getPlanets()) {
        System.out.println("  Planet: " + planet.getName());
        for (Moon moon : planet.getMoons()) {
          System.out.println("    Moon: " + moon.getName());
        }
      }
      System.out.println();
    }
  }

  /**
   * Returns a list of all solar systems in memory.
   *
   * @return list of solar systems
   */
  public List<SolarSystem> getSolarSystems() {
    return solarSystemService.getSolarSystems();
  }

  /**
   * Finds a solar system by name, ignoring case.
   *
   * @param name the name to search for
   * @return the matching solar system, or null if not found
   */
  public SolarSystem getSolarSystemByNameIgnoreCase(String name) {
    for (SolarSystem system : solarSystemService.getSolarSystems()) {
      if (system.getName().equalsIgnoreCase(name)) {
        return system;
      }
    }
    return null;
  }

  public SolarSystemService getSolarSystemsService() {
    return this.solarSystemService;
  }

  public void loadSolarSystems() {
    List<SolarSystem> systems = fileHandler.loadData();
    solarSystemService.updateSolarSystems(systems);
  }


}
