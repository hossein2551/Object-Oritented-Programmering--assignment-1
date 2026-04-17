package org.example.solitares;

public abstract class HeavenlyBody {
  private String name;
  private int avgRadiusInKm;

  protected HeavenlyBody(String name, int avgRadiusInKm) {
    setName(name);
    setAvgRadiusInKm(avgRadiusInKm);
  }

  public String getName() {
    return name;
  }

  private void setName(String newName) {
    if (newName == null || newName.trim().isEmpty()) {
      throw new IllegalArgumentException("Name must not be null or empty");
    }
    this.name = newName;
  }

  public int getAvgRadiusInKm() {
    return avgRadiusInKm;
  }

  private void setAvgRadiusInKm(int radius) {
    validateRadius(radius);
    this.avgRadiusInKm = radius;
  }

  protected abstract void validateRadius(int radius);

  @Override
    public String toString() {
    return getClass().getSimpleName() + ": " + name + ", average radius " + avgRadiusInKm + "km";
  }
}