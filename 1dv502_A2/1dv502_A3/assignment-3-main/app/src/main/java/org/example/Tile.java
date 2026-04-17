package org.example;

import java.util.ArrayList;

/**
 * Represents a double linked list of game tiles.
 */
public abstract class Tile {
  // These are the only fields you need.
  private Tile next;
  private Tile prev;

  // a tile knows what objects (players) are currently on the tile
  ArrayList<Object> playersOnTile;  

  protected Tile() {
    // Correctly set the next and prev references to point to this tile
    this.next = this;
    this.prev = this;

    playersOnTile = new ArrayList<>();
  }

   // Constructor that inserts a new tile after `prevTile`
   protected Tile(Tile prevTile) {
    //  Correctly insert `this` into the linked list after `prevTile`
    this.prev = prevTile; 
    this.next = prevTile.next; 
    
    // Updated surrounding tiles to maintain the doubly linked list
    prevTile.next.prev = this; 
    prevTile.next = this; 

    playersOnTile = new ArrayList<>();
  }

  public Tile getNext() {
    return next;
  }

  public Tile getPrev() {
    return prev;
  }

  /**
  * Checks if a player is on the tile.
  */
  public boolean isOnTile(Player player) {
    return playersOnTile.contains(player);
  }

  /**
  * Called when a player moves over a tile.
  */
  public abstract void visit(Player player);

  /**
  * Called when a player stops on a tile.
  * Subclasses need to call super if overridden.
  */
  public void stoppedOn(Player player) {
    playersOnTile.add(player);
  }

  /**
  * Called when a player moves away from the tile stopped on.
  * Subclasses need to call super if overridden.
  */
  public void startOn(Player player) {
    playersOnTile.remove(player);
  }

  /**
  * Called when a player wants to buy a tile.
  */
  public abstract boolean buy(Player player);
}
