import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {

    JSONParser parser;
    JSONObject obj;
    JSONArray entries;
    JSONObject intervention;

    String type;
    String party;
    String speaker;
    String text;

    public JSONReader(){
        parser = new JSONParser();

    }

    public void read(String path) {
        try {
            obj = (JSONObject) parser.parse(new FileReader(path));

            entries = (JSONArray) obj.get("entries");

            for (Object o : entries) {
                intervention = (JSONObject) o;

                type = (String) intervention.get("type");
                party = (String) intervention.get("party");
                speaker = (String) intervention.get("speaker");
                text = (String) intervention.get("text");

                //adding criterion
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void printOnConsole(){
        System.out.println("Type: " + type + ", by: " + speaker + ", of: " + party + "\n");
        System.out.println(text);
        System.out.println("\n\n");
    }

}