package main.domain;

import static main.constants.AppConstants.PERFECT_SCORE;
import static main.constants.AppConstants.STRIKE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.service.LineOperations;

public class Line implements LineOperations {

  private Integer lineScore;

  /*
    Method to get the total bowling score
  */
  @Override
  public Integer sumFrames(ArrayList<Frame> frames){

    if(frames.stream().allMatch(frame -> frame.getFirstThrow().equalsIgnoreCase(STRIKE)))
      return PERFECT_SCORE;

    return 0;
  }

  @Override
  public ArrayList<Frame> parseScoreCard(String scoreCard) {

//    StringTokenizer stringTokenizer = new StringTokenizer(scoreCard, " ");
//    String[] frames = scoreCard.split(" ");
    ArrayList<Frame> frameCollection = new ArrayList<>();
    List<String> frameList = Arrays.asList(scoreCard.trim().split(" "));

    int score = 0;

    for (String frameS:frameList) {
      if(frameS.equalsIgnoreCase(STRIKE)){
        frameCollection.add(new Frame(STRIKE,null));
      }
    }

    return frameCollection;
  }

  /**
   * Return the total score of a Line.
   *
   * @param scoreCard receives a String representation of the 10 frames of a line.
   * @return the sum of the total points parsed in the incoming String.
   */
  public int getLineTotalScore(String scoreCard) {
    int totalPoints = 0;
    String noSpacesSC = scoreCard.trim().replaceAll("-", "0")
        .replaceAll("\\s+","");

    char[] charArraySC = noSpacesSC.toCharArray();
    int fScore = 0;

    int j = 0;
    for(int i = 0; i < charArraySC.length; i++) {
      j++;
      char rollPoints = charArraySC[i];

      //if next value is numeric, add the numeric value to the sum of the frame score
      if(Character.isDigit(rollPoints)){
        fScore += Character.getNumericValue(rollPoints);
        //else it only can be a STRIKE or a SPARE, call the function to resolve appropriately
      } else {
        if(rollPoints == 'X') {
          //Resolve the total points after a STRIKE
          fScore += 10;
          //Verify the case when we have a STRIKE before the last shot
          if(i <= charArraySC.length - 3){
            fScore += Character.isDigit(charArraySC[i+1]) ? Character.getNumericValue(charArraySC[i+1]) : 10;
            if(charArraySC[i+2] == '/')
              fScore += 10 - Character.getNumericValue(charArraySC[i+1]);
            else
              fScore += Character.isDigit(charArraySC[i+2]) ? Character.getNumericValue(charArraySC[i+2]) : 10;
            //Verify if it is the case where we have one extra shot at the end of the game
          } else if(i + 2 == charArraySC.length) {
            fScore += Character.isDigit(charArraySC[i+1]) ? Character.getNumericValue(charArraySC[i+1]) : 10;
            break;
            //Verify the case where there are two shots after the last frame
          }
        } else {
          //Resolve the total points after a SPARE
          fScore += 10 - fScore;
          fScore += Character.isDigit(charArraySC[i+1]) ? Character.getNumericValue(charArraySC[i+1]) : 10;
        }
      }
      //sum the total points
      if (j == 2 || rollPoints == 'X'){
        totalPoints += fScore;
        j = 0;
        fScore = 0;
      }
    }
    return totalPoints;
  }


}
