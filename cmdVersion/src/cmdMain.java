import java.util.ArrayList;
import java.util.List;

public class cmdMain {
    public static void main(String[] args) {
        DictionaryManagement.insertFromFile();
        // DictionaryCommandline.dictionarySeacher();
        int i = 1;
        List<Word> newList = new ArrayList<>();
        newList = DictionaryCommandline.dictionarySeacher();

        for (Word word : newList) {
            System.out.println(i + "\t|" + word.getWordTarget() + "\t|" + word.getWordExplain());
            i++;
        }
        DictionaryManagement.exportToFile();
        // DictionaryCommandline.showAllWords();
    }
}

