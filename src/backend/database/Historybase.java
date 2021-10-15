package backend.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Historybase {
  private static List<String> history = new ArrayList<>();

  public static void getHistoryData() {
    history.clear();
    // url file dictionaries.txt
    String url = "src\\resources\\History\\history.txt";
    // Read data from File with BufferedReader.
    try (FileInputStream fileInputStream = new FileInputStream(url);
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(fileInputStream))) {
      String line = bufferedReader.readLine();
      while (line != null) {
        final String similarString = line;
        history.removeIf(word -> (word.equals(similarString)));

        history.add(line);
        line = bufferedReader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void insertWordtoHistory(String word) {
    String url = "src\\resources\\History\\history.txt";
    /* Write word to file. */
    try (FileWriter fileWriter = new FileWriter(url, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
      bufferedWriter.write(word);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static List<String> getHistorySet() {
    ArrayList<String> newHistory = new ArrayList<>(history.size());
    for (int i = history.size() - 1; i >= 0; i--) {
      newHistory.add(history.get(i));
    }
    return newHistory;
  }
}
