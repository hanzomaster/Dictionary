public class Word {
  private String wordTarget = "";
  private String wordExplain = "";

  public Word(String wordTarget, String wordExplain) {
    this.wordTarget = wordTarget;
    this.wordExplain = wordExplain;
  }

  public void setWordTarget(final String wordTarget) {
    this.wordTarget = wordTarget;
  }

  public String getWordTarget() {
    return this.wordTarget;
  }

  public void setWordExplain(final String wordExplain) {
    this.wordExplain = wordExplain;
  }

  public String getWordExplain() {
    return this.wordExplain;
  }

  @Override
  public String toString() {
    return wordTarget + '\n' + wordExplain;
  }

}
