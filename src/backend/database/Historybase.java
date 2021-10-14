package backend.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Historybase {

  public static List<String> history = new ArrayList<>();

  public static void getHistoryData() {
    history.clear();
    // url file dictionaries.txt
    String url = "src\\resources\\History\\history.txt";
    // Read data from File with BufferedReader.
    FileInputStream fileInputStream = null;
    BufferedReader bufferedReader = null;
    try {
      fileInputStream = new FileInputStream(url);
      bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
      String line = bufferedReader.readLine();
      while (line != null) {
        // Separte word and explain.
        history.add(line);
        line = bufferedReader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // close file.
      try {
        bufferedReader.close();
        fileInputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void insertWordtoHistory(String word) {
    String url = "src\\resources\\History\\history.txt";
    /* Write word to file. */
    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;
    try {
      fileWriter = new FileWriter(url, true);
      bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(word);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fileWriter.close();
        bufferedWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
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
