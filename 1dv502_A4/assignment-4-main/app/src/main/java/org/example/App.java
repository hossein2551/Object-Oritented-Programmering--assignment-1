package org.example;

import java.util.Scanner;

/**
 * Entry point for the application.
 * Responsible only for starting the program using proper OOP structure.
 */
public class App {

  /**
     * Main method to launch the application.
     */
  public static void main(String[] args) {
    App app = new App();
    app.startApplication();
  }

  /**
     * Creates required services and starts the console menu.
     * Follows strict OOP without modifying logic or requirements.
     */
  public void startApplication() {
    Scanner scanner = new Scanner(System.in);
  
    SolarRegistrySystem registry = new SolarRegistrySystem();
    registry.loadSolarSystems(); 
  
    ConsoleMenu menu = new ConsoleMenu(registry, scanner);
    menu.start();
  
    scanner.close();
  }
  
}
