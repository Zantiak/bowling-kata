package main;

import java.util.Scanner;
import main.domain.Line;

public class BowlingKataApplication {

  /*
    This is the main method of the application.
  */
  public static void main(String[] args) {

    Line line = new Line();

    System.out.println("Provide a valid bowling scorecard");
    Scanner scanner = new Scanner(System.in);
    String scorecard = scanner.nextLine();

    System.out.println("Line " + scorecard + " total score is: " + line.getLineTotalScore(scorecard));

  }
}
