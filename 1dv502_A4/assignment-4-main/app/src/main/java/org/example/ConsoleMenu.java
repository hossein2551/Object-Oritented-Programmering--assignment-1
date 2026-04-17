package org.example;

import java.util.List;
import java.util.Scanner;

import org.example.services.ValidationService;

/**
 * This class shows a menu to the user and handles input.
 * The user can manage solar systems through the menu.
 */
public class ConsoleMenu {

  private final SolarRegistrySystem registry;
  private final Scanner scanner;

  /**
   * Creates a new ConsoleMenu with the solar system registry and a scanner for user input.
   *
   * @param registry the system that manages solar systems
   * @param scanner the scanner to read user input
   */
  public ConsoleMenu(SolarRegistrySystem registry, Scanner scanner) {
    this.registry = registry;
    this.scanner = scanner;
  }

  /**
   * Starts the menu.
   * Loads data from file, shows menu, and saves data when the user exits.
   */
  public void start() {
    registry.loadSolarSystems();
    runMenuLoop();
    registry.saveSolarSystems();
  }

  private void runMenuLoop() {
    boolean running = true;
    while (running) {
      showMenu();
      int choice = getUserInput();
      running = handleChoice(choice);
    }
  }

  private void showMenu() {
    System.out.println("\n--- Solar System Menu ---");
    System.out.println("1. Create new solar system");
    System.out.println("2. List all solar systems");
    System.out.println("3. Show solar system details");
    System.out.println("4. Add planet to solar system");
    System.out.println("5. Add moon to planet");
    System.out.println("6. Move moon between planets");
    System.out.println("7. Delete planet or moon");
    System.out.println("8. Delete entire solar system");
    System.out.println("9. Exit");
    System.out.print("Choose option (1-9): ");
  }

  private int getUserInput() {
    try {
      return Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input! Please enter a number.");
      return -1;
    }
  }

  private boolean handleChoice(int choice) {
    switch (choice) {
      case 1 -> createSolarSystem();
      case 2 -> listSolarSystems();
      case 3 -> showSolarSystemDetails();
      case 4 -> addPlanetToSolarSystem();
      case 5 -> addMoonToPlanet();
      case 6 -> moveMoon();
      case 7 -> deleteCelestialBody();
      case 8 -> deleteSolarSystem();
      case 9 -> {
        System.out.println("Goodbye!");
        return false;
      }
      default -> System.out.println("Please choose a valid option.");
    }
    return true;
  }

  private void listSolarSystems() {
    List<SolarSystem> systems = registry.getSolarSystemsService().getSolarSystems();
    if (systems.isEmpty()) {
      System.out.println("No solar systems loaded.");
    } else {
      System.out.println("Available Solar Systems:");
      for (SolarSystem system : systems) {
        System.out.println("- " + system.getName());
      }
    }
  }

  private void createSolarSystem() {
    ValidationService validationService = new ValidationService();

    System.out.print("Enter solar system name: ");
    String systemName = scanner.nextLine().trim();

    if (!validationService.isValidName(systemName)) {
      System.out.println("Error: Solar system name cannot be empty.");
      return;
    }

    System.out.print("Enter star name: ");
    String starName = scanner.nextLine().trim();

    if (!validationService.isValidName(starName)) {
      System.out.println("Error: Star name cannot be empty.");
      return;
    }

    double starRadius;
    while (true) {
      System.out.print("Enter star radius: ");
      String radiusInput = scanner.nextLine().trim();
      try {
        starRadius = Double.parseDouble(radiusInput);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number.");
      }
    }

    registry.getSolarSystemsService().createSolarSystem(systemName, starName, starRadius);
  }



  private void addPlanetToSolarSystem() {
    try {
      System.out.print("Enter solar system name: ");
      String systemName = scanner.nextLine().trim();

      System.out.print("Enter planet name: ");
      String planetName = scanner.nextLine().trim();

      System.out.print("Enter planet radius: ");
      double radius = Double.parseDouble(scanner.nextLine().trim());

      System.out.print("Enter orbital radius: ");
      double orbit = Double.parseDouble(scanner.nextLine().trim());

      registry.getSolarSystemsService().addPlanetToSolarSystemIgnoreCase(
          systemName, planetName, radius, orbit);
    } catch (NumberFormatException e) {
      System.out.println("Radius and orbit must be numbers.");
    }
  }

  private void addMoonToPlanet() {
    try {
      System.out.print("Enter solar system name: ");
      String systemName = scanner.nextLine().trim();

      System.out.print("Enter planet name: ");
      String planetName = scanner.nextLine().trim();

      System.out.print("Enter moon name: ");
      String moonName = scanner.nextLine().trim();

      System.out.print("Enter moon radius: ");
      double radius = Double.parseDouble(scanner.nextLine().trim());

      System.out.print("Enter moon orbit radius: ");
      double orbit = Double.parseDouble(scanner.nextLine().trim());

      registry.getSolarSystemsService().addMoonToPlanetIgnoreCase(
          systemName, planetName, moonName, radius, orbit);
    } catch (NumberFormatException e) {
      System.out.println("Moon radius and orbit must be numbers.");
    }
  }

  private void showSolarSystemDetails() {
    System.out.print("Enter solar system name: ");
    String name = scanner.nextLine().trim();

    System.out.println("Sort by:");
    System.out.println("1. Size");
    System.out.println("2. Orbit radius");
    int sort = getUserInput();

    boolean sortBySize = sort == 1;
    registry.getSolarSystemsService().showSolarSystemDetailsSortedIgnoreCase(name, sortBySize);
  }

  private void moveMoon() {
    System.out.print("Enter solar system name: ");
    String system = scanner.nextLine().trim();

    System.out.print("Enter moon name: ");
    String moon = scanner.nextLine().trim();

    System.out.print("Enter source planet: ");
    String from = scanner.nextLine().trim();

    System.out.print("Enter target planet: ");
    String to = scanner.nextLine().trim();

    registry.getSolarSystemsService().moveMoonIgnoreCase(system, moon, from, to);
  }

  private void deleteCelestialBody() {
    System.out.print("Enter solar system name: ");
    String system = scanner.nextLine().trim();

    System.out.print("Enter name of planet or moon: ");
    String body = scanner.nextLine().trim();

    boolean deleted = registry.getSolarSystemsService().deleteCelestialBodyIgnoreCase(system, body);
    if (deleted) {
      System.out.println("Deleted: " + body);
    } else {
      System.out.println("Could not delete body.");
    }
  }

  private void deleteSolarSystem() {
    System.out.print("Enter the name of the solar system to delete: ");
    String name = scanner.nextLine().trim();

    boolean deleted = registry.getSolarSystemsService().deleteSolarSystemIgnoreCase(name);
    if (deleted) {
      System.out.println("Solar system '" + name + "' deleted.");
    } else {
      System.out.println("Solar system not found.");
    }
  }
}
