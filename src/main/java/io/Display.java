package io;

public class Display implements UserDisplay {
  /**  
  * Public constructor.
  */
  public Display() {

  }
  /** Method to show a message.
  * @param message message to display.
  */

  public void showMessage(final String message) {
    System.out.println(message);
  }
}
