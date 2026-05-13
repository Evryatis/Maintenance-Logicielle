package shape;

/**
 * Class Curve
 */
public class Curve extends Shape {

  //
  // Fields
  //

  private shape.Point p1;
  private shape.Point p2;
  private shape.Point control1;
  private shape.Point control2;

  //
  // Constructors
  //
  public Curve() { };

  //
  // Accessor methods
  //

  /**
   * Set the value of p1
   * @param newVar the new value of p1
   */
  public void setP1(shape.Point newVar) {
    p1 = newVar;
  }

  /**
   * Get the value of p1
   * @return the value of p1
   */
  public shape.Point getP1() {
    return p1;
  }

  /**
   * Set the value of p2
   * @param newVar the new value of p2
   */
  public void setP2(shape.Point newVar) {
    p2 = newVar;
  }

  /**
   * Get the value of p2
   * @return the value of p2
   */
  public shape.Point getP2() {
    return p2;
  }

  /**
   * Set the value of control1
   * @param newVar the new value of control1
   */
  public void setControl1(shape.Point newVar) {
    control1 = newVar;
  }

  /**
   * Get the value of control1
   * @return the value of control1
   */
  public shape.Point getControl1() {
    return control1;
  }

  /**
   * Set the value of control2
   * @param newVar the new value of control2
   */
  public void setControl2(shape.Point newVar) {
    control2 = newVar;
  }

  /**
   * Get the value of control2
   * @return the value of control2
   */
  public shape.Point getControl2() {
    return control2;
  }

  //
  // Other methods
  //

  /**
   * @return       String
   */
  public String toString() {
    return "Curve[p1=" + p1 + ", p2=" + p2 + ", control1=" + control1 + ", control2=" + control2 + "]";
  }
}
