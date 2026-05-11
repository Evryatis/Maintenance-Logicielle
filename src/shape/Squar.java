package shape;

/**
 * Class Squar
 */
public class Squar extends Shape {

  //
  // Fields
  //

  private shape.Point p1;
  private int length;
  
  //
  // Constructors
  //
  public Squar () { };
  
  //
  // Accessor methods
  //

  /**
   * Set the value of p1
   * @param newVar the new value of p1
   */
  public void setP1 (shape.Point newVar) {
    p1 = newVar;
  }

  /**
   * Get the value of p1
   * @return the value of p1
   */
  public shape.Point getP1 () {
    return p1;
  }

  /**
   * Set the value of length
   * @param newVar the new value of length
   */
  public void setLength (int newVar) {
    length = newVar;
  }

  /**
   * Get the value of length
   * @return the value of length
   */
  public int getLength () {
    return length;
  }

  //
  // Other methods
  //

  /**
   * @return       String
   */
  public String toString()
  {
    return "Squar[p1=" + p1 + ", length=" + length + "]";
  }
}
