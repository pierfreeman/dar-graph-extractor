import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONReader {

    private JSONParser parser;
    private JSONObject obj;
    private JSONArray entries;
    private JSONObject intervention;

    private String type;
    private String party;
    private String speaker;
    private String text;

    public JSONReader(){
        parser = new JSONParser();

    }

    public void read(String path, ArrayList<Person> persons) {
        try {
            obj = (JSONObject) parser.parse(new FileReader(path));

            entries = (JSONArray) obj.get("entries");

            for (Object o : entries) {
                intervention = (JSONObject) o;

                type = (String) intervention.get("type");
                party = (String) intervention.get("party");
                speaker = (String) intervention.get("speaker");
                text = (String) intervention.get("text");

                Reasoner.checksInteraction(speaker, party, persons);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}