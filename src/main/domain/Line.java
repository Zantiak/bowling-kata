package main.domain;

import static main.constants.AppConstants.MISS;
import static main.constants.AppConstants.SPARE;
import static main.constants.AppConstants.STRIKE;

public class Line {

  private Integer lineScore;

  /**
   * Return the total score of a Line.
   *
   * @param scoreCard receives a String representation of the 10 frames of a line.
   * @return the sum of the total points parsed in the incoming String.
   */
  public int getLineTotalScore(String scoreCard) {
    int totalPoints = 0;
    String noSpacesSC = scoreCard.trim().replaceAll(MISS, "0")
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
        if(rollPoints == STRIKE) {
          //Resolve the total points after a STRIKE
          fScore += 10;
          //Verify the case when we have a STRIKE before the last shot
          if(i <= charArraySC.length - 3){
            fScore += Character.isDigit(charArraySC[i+1]) ? Character.getNumericValue(charArraySC[i+1]) : 10;
            if(charArraySC[i+2] == SPARE)
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
      if (j == 2 || rollPoints == STRIKE){
        totalPoints += fScore;
        j = 0;
        fScore = 0;
      }
    }
    return totalPoints;
  }

}
