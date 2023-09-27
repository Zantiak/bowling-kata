package main.domain;

import java.util.Objects;

/**
 * Representation class of a frame or turn.
 * TODO this class is candidate to use the record feature of Java 17
 */
public class Frame {

  private String firstThrow;
  private String secondThrow;
  private Integer sparePoints;
  private Integer strikePoints;

  public Frame(String firstThrow, String secondThrow) {
    this.firstThrow = firstThrow;
    this.secondThrow = secondThrow;
  }

  public Frame(String firstThrow, String secondThrow, Integer sparePoints, Integer strikePoints) {
    this.firstThrow = firstThrow;
    this.secondThrow = secondThrow;
    this.sparePoints = sparePoints;
    this.strikePoints = strikePoints;
  }

  public String getFirstThrow() {
    return firstThrow;
  }

  public void setFirstThrow(String firstThrow) {
    this.firstThrow = firstThrow;
  }

  public String getSecondThrow() {
    return secondThrow;
  }

  public void setSecondThrow(String secondThrow) {
    this.secondThrow = secondThrow;
  }

  public Integer getSparePoints() {
    return sparePoints;
  }

  public void setSparePoints(Integer sparePoints) {
    this.sparePoints = sparePoints;
  }

  public Integer getStrikePoints() {
    return strikePoints;
  }

  public void setStrikePoints(Integer strikePoints) {
    this.strikePoints = strikePoints;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Frame frame = (Frame) o;
    return Objects.equals(firstThrow, frame.firstThrow) &&
        Objects.equals(secondThrow, frame.secondThrow) &&
        Objects.equals(sparePoints, frame.sparePoints) &&
        Objects.equals(strikePoints, frame.strikePoints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstThrow, secondThrow, sparePoints, strikePoints);
  }
}
