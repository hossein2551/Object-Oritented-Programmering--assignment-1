package org.example;

/**
 * Represents a property tile that can be bought by a player.
 */
public class Property extends Tile {
    private final String name;
    private  Player owner;
    private final int price = 200;
    private final int rent = 300;

    /**
     * Creates a new property object with a name.
     */
    public Property(Tile prevTile, String name) {
        super(prevTile);
        this.name = name;
    }

    @Override
    public String toString() {
        String ownerName = (owner == null) ? "none" : owner.getName();
        return name + " (" + ownerName + ")";
    }

    @Override
    public void visit(Player player) {
        // Nothing happens when just visiting
    }

    @Override
    public void stoppedOn(Player player) {
        super.stoppedOn(player);
        if (owner != null && owner != player) {
            owner.addFunds(player.payRent(getRent()));
        }
    }

    /**
     * Gets the owner of the property.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Call to buy a property.
     */
    @Override
    public boolean buy(Player player) {
        if (owner == null && player.getTile() == this && player.getFunds() >= price) {
            player.deduceFunds(price);
            setOwner(player);
            return true;
        }
        return false;
    }

    /**
     * Sets the owner of the property.
     */
    private void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Gets the cost of stopping on the property.
     */
    public int getRent() {
        return rent;
    }

    /**
     * Gets the name of the property.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if a particular player is the owner of the property.
     */
    public boolean isOwner(Player player) {
        return owner == player;
    }

    /**
     * Returns the price to buy the property.
     */
    public int getPrice() {
        return price;
    }
}
