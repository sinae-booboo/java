package ioopm.inl4;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Sphinx extends Person {
    private ArrayList<String> responses;

    /**
     * Creates a sphinx with the name "The Sphinx"
     */

    public Sphinx(){
        super("The Sphinx");
        initResponses();
    }

    /**
     * Prints a random response
     */

    public void talk(){
        int idx = new Random().nextInt(responses.size());
        System.out.println(responses.get(idx));
    }

    private void initResponses(){
        responses = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream("txt/sphinx.txt")){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line;
            while ((line = buffer.readLine()) != null) {
                responses.add(line);
            }
        }catch(FileNotFoundException e){
            System.out.println("txt/sphinx.txt not found!");
            System.exit(0);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
