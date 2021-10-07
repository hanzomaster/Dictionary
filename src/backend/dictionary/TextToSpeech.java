package backend.dictionary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;

/**
 * https://youtu.be/OLKxBorVwk8
 */
public class TextToSpeech {
  private AudioPlayer tts;
  private MaryInterface marytts;

  /**
   * Constructor.
   */
  public TextToSpeech() {
    try {
      marytts = new LocalMaryInterface();
    } catch (MaryConfigurationException ex) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Transform text to speech.
   * 
   * @param text The text that will be transformed to speech
   * @param daemon <b>True</b> The thread that will start the text to speech Player will be a daemon
   *          <br>
   *          <b>False</b> The thread that will start the text to speech Player will be a normal non
   *          daemon Thread
   * @param join <b>True</b> The current Thread calling this method will wait(blocked) until the
   *          Thread which is playing the Speech finish <br>
   *          <b>False</b> The current Thread calling this method will continue freely after calling
   *          this method
   */
  public void speak(String text, boolean daemon, boolean join) {

    // Stop the previous player
    stopSpeaking();

    try (AudioInputStream audio = marytts.generateAudio(text)) {

      // Player is a thread(threads can only run one time) so it can be
      // used has to be initiated every time
      tts = new AudioPlayer();
      tts.setAudio(audio);
      tts.setDaemon(daemon);
      tts.start();
      if (join) {
        tts.join();
      }
    } catch (SynthesisException ex) {
      Logger.getLogger(getClass().getName()).log(Level.WARNING, "Error saying phrase.", ex);
    } catch (IOException ex) {
      Logger.getLogger(getClass().getName()).log(Level.WARNING, "IO Exception", ex);
    } catch (InterruptedException ex) {
      Logger.getLogger(getClass().getName()).log(Level.WARNING, "Interrupted ", ex);
      tts.interrupt();
    }
  }

  /**
   * Stop the MaryTTS from Speaking.
   */
  public void stopSpeaking() {
    // Stop the previous player
    if (tts != null) {
      tts.cancel();
    }
  }
}
