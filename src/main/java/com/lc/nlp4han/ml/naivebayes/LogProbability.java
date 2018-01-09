package com.lc.nlp4han.ml.naivebayes;

/**
 * Class implementing the probability for a label.
 *
 * @param <T> the label (category) class
 *
 */
public class LogProbability<T> extends Probability<T> {

  public LogProbability(T label) {
    super(label);
    set(1.0);
  }

  /**
   * Assigns a probability to a label, discarding any previously assigned probability.
   *
   * @param probability the probability to assign
   */
  public void set(double probability) {
    this.probability = Math.log(probability);
  }

  /**
   * Assigns a probability to a label, discarding any previously assigned probability.
   *
   * @param probability the probability to assign
   */
  public void set(Probability probability) {
    this.probability = probability.getLog();
  }

  /**
   * Assigns a probability to a label, discarding any previously assigned probability, if the new probability is greater than the old one.
   *
   * @param probability the probability to assign
   */
  public void setIfLarger(double probability) {
    double logP = Math.log(probability);
    if (this.probability < logP) {
      this.probability = logP;
    }
  }

  /**
   * Assigns a probability to a label, discarding any previously assigned probability, if the new probability is greater than the old one.
   *
   * @param probability the probability to assign
   */
  public void setIfLarger(Probability probability) {
    if (this.probability < probability.getLog()) {
      this.probability = probability.getLog();
    }
  }

  /**
   * Checks if a probability is greater than the old one.
   *
   * @param probability the probability to assign
   */
  public boolean isLarger(Probability probability) {
    return this.probability < probability.getLog();
  }

  /**
   * Assigns a log probability to a label, discarding any previously assigned probability.
   *
   * @param probability the log probability to assign
   */
  public void setLog(double probability) {
    this.probability = probability;
  }

  /**
   * Compounds the existing probability mass on the label with the new probability passed in to the method.
   *
   * @param probability the probability weight to add
   */
  public void addIn(double probability) {
    setLog(this.probability + Math.log(probability));
  }

  /**
   * Returns the probability associated with a label
   *
   * @return the probability associated with the label
   */
  public Double get() {
    return Math.exp(probability);
  }

  /**
   * Returns the log probability associated with a label
   *
   * @return the log probability associated with the label
   */
  public Double getLog() {
    return probability;
  }

  /**
   * Returns the probabilities associated with all labels
   *
   * @return the HashMap of labels and their probabilities
   */
  public T getLabel() {
    return label;
  }

  public String toString() {
    return label.toString() + ":" + probability;
  }
}