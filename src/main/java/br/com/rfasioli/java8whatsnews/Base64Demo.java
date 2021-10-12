package br.com.rfasioli.java8whatsnews;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class Base64Demo {
    public static void main(String[] args) {

        try {
            // Encode using basic encoder
            String base64encodedString = Base64.getEncoder().encodeToString(
                "TutorialsPoint?java8".getBytes("utf-8"));
            System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);

            // Decode
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);

            System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
            base64encodedString = Base64.getUrlEncoder().encodeToString(
                "TutorialsPoint?java8".getBytes("utf-8"));
            System.out.println("Base64 Encoded String (URL) :" + base64encodedString);

            StringBuilder stringBuilder = new StringBuilder();

            new Random().ints().limit(10l).forEach((i) ->
                stringBuilder.append(UUID.randomUUID().toString()));

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 Encoded String (MIME) :" + mimeEncodedString);

        } catch(UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }
}
