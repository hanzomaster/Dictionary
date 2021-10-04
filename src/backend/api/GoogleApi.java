package backend.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleApi {

  /**
   * Translate text with Goolge API. Reference link:
   * https://stackoverflow.com/questions/8147284/how-to-use-google-translate-api-in-my-java-application
   * 
   * @param langFrom language of the {@code text} parameter
   * @param langTo language that text get translated to
   * @param text input string
   * @return Translated string
   * @throws IOException Can't connect to Google API
   */
  private static String getTranslateApi(String langFrom, String langTo, String text)
      throws IOException {
    final String urlStr =
        "https://script.google.com/macros/s/AKfycbxhKajFpibFraOBziYUkblCfaQJxbCZIFdptAEIzQq42fp-ztkwJnF9UC4rzxGduRktiQ/exec"
            + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo + "&source="
            + langFrom;
    URL url = new URL(urlStr);
    StringBuilder response = new StringBuilder();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }

  public static String translateEnToVi(String text) throws IOException {
    return getTranslateApi("en", "vi", text);
  }

  public static String translateViToEn(String text) throws IOException {
    return getTranslateApi("vi", "en", text);
  }
}
