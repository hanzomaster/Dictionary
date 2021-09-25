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
      // System.out.println("Voice rate: " + voice.getRate());
      // System.out.println("Voice pitch: " + voice.getPitch());
      // System.out.println("Voice volumn: " + voice.getVolume());
      // boolean status = voice.speak(text);
      voice.speak(text);
      // System.out.println("Status: " + status);
      voice.deallocate();
    } else {
      System.err.println("Error in getting Voices");
    }
  }
}
