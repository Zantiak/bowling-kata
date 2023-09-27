package tests;

import static main.constants.AppConstants.PERFECT_SCORE;
import static main.constants.AppConstants.STRIKE;

import java.util.ArrayList;
import java.util.Arrays;
import main.domain.Frame;
import main.domain.Line;

public class TestBowlingKataApplication {

  //Test scenarios
  String perfectScore = "X X X X X X X X X X X X";              // Total score is: 300
  String ninetyScore = "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-";         // Total score is: 90
  String oneHundFiftyScore = "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5";  // Total score is: 150
  String someStrikes = "X -5 X X X 36 X -- 5/ XXX";             // Total score is: 161
  String randomScore = "5/ X 18 5/ 5/ -/ X 5/ 5/ 5/5";          // Total score is: 158
  String randomScore2 = "8- 5/ 35 81 71 X 9/ X X 8/6";          // Total score is: 150

  final Line line = new Line();
  final Frame frameStrike = new Frame(STRIKE, null);

  public static void main(String[] args) {

    TestBowlingKataApplication tbkApp = new TestBowlingKataApplication();

    // Execute test cases.
    tbkApp.testPerfectScore();
    tbkApp.testNinetyScore();
    tbkApp.testOneHundFiftyScore();
    tbkApp.testSomeStrikes();
    tbkApp.testRandomScore();
  }


  /*
   * This scenario covers the case when all the frames had a strike.
   * Scorecard entry: X X X X X X X X X X X X
   */
  public void testPerfectScore() {

    assert line.getLineTotalScore(perfectScore) == PERFECT_SCORE : "TestBowlingKataApplication.testPerfectScore FAILED";
    System.out.println("TestBowlingKataApplication.testPerfectScore PASSED");

  }

  /*
   * This scenario covers the case when all the frames had 9 points on first shot and zero on second shot.
   * Scorecard entry: 9- 9- 9- 9- 9- 9- 9- 9- 9- 9-
   */
  public void testNinetyScore() {

    assert line.getLineTotalScore(ninetyScore) == 90 : "TestBowlingKataApplication.testNinetyScore FAILED";
    System.out.println("TestBowlingKataApplication.testNinetyScore PASSED");

  }

  /*
   * This scenario covers the case when all the frames had 5 points on the first shot and a spare on the second shot,
   * as a result, there is an extra shot in the last frame.
   * Scorecard entry: 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5
   */
  public void testOneHundFiftyScore() {

    assert line.getLineTotalScore(oneHundFiftyScore) == 150 :
        "TestBowlingKataApplication.oneHundFiftyScore FAILED";
    System.out.println("TestBowlingKataApplication.testOneHundFiftyScore PASSED");

  }


  /*
   * This scenario covers the case when there are strikes present through the line, including three strikes on
   * the last shots.
   * Scorecard entry: X -5 X X X 36 X -- 5/ XXX
   */
  public void testSomeStrikes() {

    assert line.getLineTotalScore(someStrikes) == 161 :
        "TestBowlingKataApplication.testSomeStrikes FAILED";
    System.out.println("TestBowlingKataApplication.testSomeStrikes PASSED");

  }

  /*
   * This scenario covers random scoreCards.
   * Scorecard entry 1: 5/ X 18 5/ 5/ -/ X 5/ 5/ 5/5
   * Scorecard entry 2: 8- 5/ 35 81 71 X 9/ X X 8/6
   */
  public void testRandomScore() {

    assert line.getLineTotalScore(randomScore) == 158 :
        "TestBowlingKataApplication.testRandomScore FAILED";
    assert line.getLineTotalScore(randomScore2) == 150 :
        "TestBowlingKataApplication.testRandomScore FAILED";
    System.out.println("TestBowlingKataApplication.testRandomScore PASSED");

  }



  public void testParseScoreCardPerfectScore() {
    Frame frame = new Frame("X", null);
    ArrayList<Frame> frameCollection = new ArrayList<>();

    // Create a frame collection of a perfect score
    for(int i = 0; i < 12; i++) {
      frameCollection.add(frame);
    }

    // Call the method to parse the String with the score
    ArrayList<Frame> parsedFrame = line.parseScoreCard(perfectScore);

    // Assertion, check that both Lists contains the same elements
    assert frameCollection.equals(parsedFrame) == true;
    System.out.println("TestBowlingKataApplication.testParseScoreCardPerfectScore PASSED");
  }


  public void testParseScoreCardPerfectScoreFail() {
    Frame frame = new Frame("X", null);
    ArrayList<Frame> frameCollection = new ArrayList<>();

    // Create a frame collection of a perfect score
    for(int i = 0; i < 12; i++) {
      frameCollection.add(frame);
    }

    // Call the method to parse the String with the score
    ArrayList<Frame> parsedFrame = line.parseScoreCard(perfectScore.concat("X"));

    // Assertion, check that both Lists contains the same elements
    assert frameCollection.equals(parsedFrame) == false;
    System.out.println("TestBowlingKataApplication.testParseScoreCardPerfectScoreFail PASSED");
  }

  // case not completed
  public void testParseScoreCardNinetyScore(){
    Frame frame = new Frame("9", "-");
    ArrayList<Frame> frameCollection = new ArrayList<>();

    // Create a frame collection of a perfect score
    for(int i = 0; i < 12; i++) {
      frameCollection.add(frame);
    }

    // Call the method to parse the String with the score
    ArrayList<Frame> parsedFrame = line.parseScoreCard(ninetyScore);

    // Assertion, check that both Lists contains the same elements
    assert frameCollection.equals(parsedFrame) == true;
    System.out.println("TestBowlingKataApplication.testParseScoreCardNinetyScore PASSED");
  }


  /*
    This scenario covers the case when all the frames had a strike.
  */
  public void testPerfectScoreB() {
    ArrayList<Frame> perfectScore = new ArrayList<>(Arrays.asList(frameStrike,frameStrike,frameStrike,frameStrike,frameStrike,
        frameStrike,frameStrike,frameStrike,frameStrike,frameStrike));

    assert line.sumFrames(perfectScore) == PERFECT_SCORE : "TestBowlingKataApplication.testPerfectScore FAILED";
    System.out.println("TestBowlingKataApplication.testPerfectScore PASSED");
  }

  /*
  This scenario covers the case when all the frames had a strike except the last.
  */
  public void testPerfectScoreFailB() {
    ArrayList<Frame> perfectScore = new ArrayList<>(Arrays.asList(frameStrike,frameStrike,frameStrike,frameStrike,frameStrike,
        frameStrike,frameStrike,frameStrike,frameStrike,new Frame("10","-")));

    assert line.sumFrames(perfectScore) != PERFECT_SCORE;
    System.out.println("TestBowlingKataApplication.testPerfectScoreFail PASSED");
  }



}
