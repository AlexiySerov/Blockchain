package Kusu.Blockchain.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSerialization {
    private static Gson gson;

    public static Gson getInstance(){
        if (gson == null){
            gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
        }

        return gson;
    }
}
