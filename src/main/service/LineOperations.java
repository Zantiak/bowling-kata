package main.service;

import java.util.ArrayList;
import main.domain.Frame;

public interface LineOperations {

  /*
   Method to get the total bowling score
  */
  Integer sumFrames(ArrayList<Frame> frames);

  /*
   Method to parse the scoreCard String into an ArrayList<Frame>
  */
  ArrayList<Frame> parseScoreCard(String scoreCard);

}
