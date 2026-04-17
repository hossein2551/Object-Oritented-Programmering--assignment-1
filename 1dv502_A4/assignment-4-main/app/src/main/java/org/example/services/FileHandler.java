package org.example.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.Moon;
import org.example.Planet;
import org.example.SolarSystem;
import org.example.Star;

/**
 * Handles saving and loading solar system data from a file.
 */
public class FileHandler {
  private final String filename;
  private final ValidationService validator;

  public FileHandler(String filename) {
    this.filename = filename;
    this.validator = new ValidationService();
  }
  
  /**
 * Saves all solar system data to a file.
 * The method goes through each solar system and writes its star, planets,
 * and moons to the file in a structured format.
 * Each line in the file represents a star, planet, or moon.
 * If something goes wrong when writing, it prints an error message.
 *
 * @param solarSystems the list of solar systems to save to the file
 */

  public void saveData(List<SolarSystem> solarSystems) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      for (SolarSystem system : solarSystems) {
        Star star = system.getStar();
        writer.write(star.getName() + ":" + star.getRadius());
        writer.newLine();

        for (Planet planet : system.getPlanets()) {
          writer.write("-" + planet.getName() + ":" + planet.getRadius()
                + ":" + planet.getOrbitRadius());
          writer.newLine();

          for (Moon moon : planet.getMoons()) {
            writer.write("--" + moon.getName() + ":" + moon.getRadius() 
                + ":" + moon.getOrbitRadius());
            writer.newLine();
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Error saving data: " + e.getMessage());
    }
  }

  /**
 * Loads solar system data from a file.
 * This method reads each line of the file and builds solar systems,
 * planets, and moons based on the format in the file.
 * It checks that all values (like radius and orbit) are valid.
 * If the file or lines are incorrect, the method skips those lines.
 *
 * @return a list of solar systems created from the file data
 */
  public List<SolarSystem> loadData() {
    List<SolarSystem> systems = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      SolarSystem currentSystem = null;
      Planet currentPlanet = null;

      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) { 
          continue;
        }
        try {
          if (!line.startsWith("-")) {
            String[] parts = line.split(":");
            if (parts.length < 2) {
              continue;
            }
            String name = parts[0];
            double radius = Double.parseDouble(parts[1]);

            if (!validator.isValidStarRadius(radius)) {
              continue;
            }
            Star star = new Star(name, radius);
            currentSystem = new SolarSystem(name, star);
            systems.add(currentSystem);
            currentPlanet = null;

          } else if (line.startsWith("--")) {
            String[] parts = line.substring(2).split(":");
            if (parts.length < 3) {
              continue;
            }
            String name = parts[0];
            double radius = Double.parseDouble(parts[1]);
            double orbit = Double.parseDouble(parts[2]);

            if (!validator.isValidMoonRadius(radius)) {
              continue;
            }
            if (!validator.isValidOrbitRadius(orbit)) {
              continue;
            }
            Moon moon = new Moon(name, radius);
            moon.setOrbitRadius(orbit);

            if (currentPlanet != null) {
              currentPlanet.addMoon(moon);
            }

          } else {
            String[] parts = line.substring(1).split(":");
            if (parts.length < 3) { 
              continue;
            }
            String name = parts[0];
            double radius = Double.parseDouble(parts[1]);
            double orbit = Double.parseDouble(parts[2]);

            if (!validator.isValidPlanetRadius(radius)) {

              continue;
            }

            if (!validator.isValidOrbitRadius(orbit)) {
              continue; 
            }
            currentPlanet = new Planet(name, radius, orbit);

            if (currentSystem != null) {
              currentSystem.getPlanets().add(currentPlanet);
            }
          }

      } catch (Exception e) {
          System.out.println("ERROR: Exception while parsing line: " + line);
          System.out.println("       [DEBUG EXCEPTION] Class: " + e.getClass().getSimpleName());
          System.out.println("       [DEBUG EXCEPTION] Message: " + e.getMessage());
        }
      }

    } catch (IOException e) {
      System.out.println("ERROR: Failed to read file: " + e.getMessage());
    }

    return systems;
  }
}
