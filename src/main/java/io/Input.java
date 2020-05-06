package io;

import java.util.Scanner;

public class Input implements UserInput {
  /** Reads input from user.
  *@return user input.
  */
  public String readValue() {
    Scanner sc = new Scanner(System.in, "UTF-8");
    String s = sc.nextLine();
    return s.replace("\n", "").replace("\r", "");
  }

}
