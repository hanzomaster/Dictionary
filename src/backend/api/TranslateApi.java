package backend.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

/**
 * Translate English to Vietnamese Api.
 */
public class TranslateApi {

  /**
   * Call the google translate api from rakuten rapidapi (limit at 50 requests/day). Link:
   * https://english.api.rakuten.net/googlecloud/api/google-translate1?endpoint=apiendpoint_a5764907-04b6-4d61-869b-79dc5325c739
   * 
   * @param text Test that need to be translated
   * @return Translated text as json format
   */
  private HttpResponse<JsonNode> translateApi(final String text) {
    try {
      HttpResponse<JsonNode> response;
      response = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2")
          .header("content-type", "application/x-www-form-urlencoded")
          .header("accept-encoding", "application/gzip")
          .header("x-rapidapi-key", "0e63b3da9dmshe8b5fbf3522a616p10ed9bjsn9a2e055b4e9b")
          .header("x-rapidapi-host", "google-translate1.p.rapidapi.com")
          .body("q=" + text + "&target=vi&source=en").asJson();
      return response;
    } catch (UnirestException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Receive response from api and parse it to String.
   * 
   * @param text The {@code text} to be translated
   * @return Translated text as string
   */
  String getTranslatedText(final String text) {
    JSONObject responseBody = translateApi(text).getBody().getObject();
    final String translatedText = responseBody.getJSONObject("data").getJSONArray("translations")
        .getJSONObject(0).getString("translatedText");
    return translatedText;
  }

  /**
   * Translate text from user input.
   * 
   * @param text The {@code text} to be translated
   * @return Translated text
   */
  public String translate(String text) {
    String translatedText = getTranslatedText(text);
    return translatedText;
    // TODO: Check if API work on another computer that not sign in with rakuten rapidapi account
  }
}
