package shape;

/**
 * Class Cercle
 */
public class Cercle extends Shape {

  //
  // Fields
  //

  private shape.Point center;
  private int radus;
  
  //
  // Constructors
  //
  public Cercle () { };
  
  //
  // Accessor methods
  //

  /**
   * Set the value of center
   * @param newVar the new value of center
   */
  public void setCenter (shape.Point newVar) {
    center = newVar;
  }

  /**
   * Get the value of center
   * @return the value of center
   */
  public shape.Point getCenter () {
    return center;
  }

  /**
   * Set the value of radus
   * @param newVar the new value of radus
   */
  public void setRadus (int newVar) {
    radus = newVar;
  }

  /**
   * Get the value of radus
   * @return the value of radus
   */
  public int getRadus () {
    return radus;
  }

  //
  // Other methods
  //

  /**
   * @return       String
   */
  public String toString()
  {
    return "Cercle[center=" + center + ", radus=" + radus + "]";
  }
}
