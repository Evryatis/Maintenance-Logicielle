package layer;
import java.util.ArrayList;

/**
 * Class Areas
 */
public class Areas {

  //
  // Fields
  //

  private ArrayList<Layer> layers;
  private Integer id;
  private String name;
  private Integer width;
  private Integer height;
  private char emptyChar;
  private char fullChar;
  
  //
  // Constructors
  //
  public Areas () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of layers
   * @param newVar the new value of layers
   */
  public void setLayers (ArrayList<Layer> newVar) {
    layers = newVar;
  }

  /**
   * Get the value of layers
   * @return the value of layers
   */
  public ArrayList<Layer> getLayers () {
    return layers;
  }

  /**
   * Set the value of id
   * @param newVar the new value of id
   */
  public void setId (Integer newVar) {
    id = newVar;
  }

  /**
   * Get the value of id
   * @return the value of id
   */
  public Integer getId () {
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
   * Set the value of width
   * @param newVar the new value of width
   */
  public void setWidth (Integer newVar) {
    width = newVar;
  }

  /**
   * Get the value of width
   * @return the value of width
   */
  public Integer getWidth () {
    return width;
  }

  /**
   * Set the value of height
   * @param newVar the new value of height
   */
  public void setHeight (Integer newVar) {
    height = newVar;
  }

  /**
   * Get the value of height
   * @return the value of height
   */
  public Integer getHeight () {
    return height;
  }

  /**
   * Set the value of emptyChar
   * @param newVar the new value of emptyChar
   */
  public void setEmptyChar (char newVar) {
    emptyChar = newVar;
  }

  /**
   * Get the value of emptyChar
   * @return the value of emptyChar
   */
  public char getEmptyChar () {
    return emptyChar;
  }

  /**
   * Set the value of fullChar
   * @param newVar the new value of fullChar
   */
  public void setFullChar (char newVar) {
    fullChar = newVar;
  }

  /**
   * Get the value of fullChar
   * @return the value of fullChar
   */
  public char getFullChar () {
    return fullChar;
  }

  //
  // Other methods
  //

}
