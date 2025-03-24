import Kusu.Blockchain.utils.PassPhraseGenerator;
import Kusu.Blockchain.utils.ResourceReader;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PassPhraseTest {


    private static List<String> words;

    private static List<String> getWords(){
        if(words == null){
            try {
                words = ResourceReader.readResourceFile("words2.txt");
            } catch (Exception e){
                return null;
            }
        }

        return words;
    }

    public PassPhraseTest() {
        words = getWords();
    }


    @Test
    public void uniquePassPhrase() {

        for (int i = 0; i< words.size(); i++){
            assertEquals(Set.of(PassPhraseGenerator.generate()).size(), 24);

        }
    }

}
