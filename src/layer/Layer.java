/**
 * Class Layer
 */
package layer;

import java.util.ArrayList;

import shape.Shape;

public class Layer {

  //
  // Fields
  //

  private int id;
  private String name;
  private boolean visible;
  private ArrayList<Shape> listShape;
  
  //
  // Constructors
  //
  public Layer () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setId (int newVar) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public int getId () {
    return id;
  }

  /**
   * Set the value of name
   * @param newVar the new value of name
   */
  public void setName (String newVar) {
    name = newVar;
  }

  /**
   * Get the value of name
   * @return the value of name
   */
  public String getName () {
    return name;
  }

  /**
   * Set the value of visible
   * @param newVar the new value of visible
   */
  public void setVisible (boolean newVar) {
    visible = newVar;
  }

  /**
   * Get the value of visible
   * @return the value of visible
   */
  public boolean getVisible () {
    return visible;
  }

  /**
   * Set the value of listShape
   * @param newVar the new value of listShape
   */
  public void setListShape (ArrayList<Shape> newVar) {
    listShape = newVar;
  }

  /**
   * Get the value of listShape
   * @return the value of listShape
   */
  public ArrayList<Shape> getListShape () {
    return listShape;
  }

  /**
   * Add a shape to the layer
   * @param shape the shape to add
   */
  public void addShape(Shape shape) {
    if (listShape == null) {
      listShape = new ArrayList<>();
    }
    listShape.add(shape);
  }

  /**
   * Remove a shape from the layer
   * @param shape the shape to remove
   */
  public void removeShape(Shape shape) {
    if (listShape != null) {
      listShape.remove(shape);
    }
  }

  //
  // Other methods
  //

}
