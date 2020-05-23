package Project2;
public enum Major {
  UNDECLARED ("Undeclared"),
  CSC ("CompSci"),
  MAT ("Math"),
  CHE ("Chemistry"),
  BUS ("Business"),
  ENL ("English"),
  HIS ("History"),
  PSY ("Psychology"),
  MUS ("Music"),
  ART ("Art");

  private final String value;
  Major(String v) {
    this.value = v;
  }

  public String value() { return this.value; }

  public String toString() { return this.value; }
}
