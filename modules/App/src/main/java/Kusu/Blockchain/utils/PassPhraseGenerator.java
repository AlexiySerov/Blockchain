package Kusu.Blockchain.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class PassPhraseGenerator {

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

    public static String[] generate(){
        Random random = new Random();
        String[] passphrase = new String[24];

        int i = 0;
        while (passphrase[23] == null){
            int randomNumber = random.nextInt(getWords().size());
            if(Arrays.stream(passphrase).noneMatch(s -> Objects.equals(s, words.get(randomNumber)))){
                passphrase[i] = words.get(randomNumber);
                i++;
            }
        }

        return passphrase;
    }

    public static String passphraseToString(String[] passphrase){
        return String.join(" ", passphrase);
    }

}
