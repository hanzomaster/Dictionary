package backend.dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
  /**
   * Convert input {@code text} to voice and play it with freetts library. Reference link:
   * https://youtu.be/_8XstaraP9E
   * 
   * @param text The text to be converted to voice
   */
  public void playSound(String text) {
    System.setProperty("freetts.voices",
        "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

    Voice voice = VoiceManager.getInstance().getVoice("kevin16");

    if (voice != null) {
      voice.allocate();
      voice.speak(text);
      voice.deallocate();
    } else {
      System.err.println("Error in getting voices");
    }
  }
}
