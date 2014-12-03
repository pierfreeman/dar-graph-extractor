import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by piercarloserena on 03/12/14.
 */
public class JSONReader {

    JSONParser parser;

    public void read() {
        parser = new JSONParser();

        try {

            JSONObject obj = (JSONObject) parser.parse(new FileReader("resources/dataset/dar_12_01_001.json"));

            JSONArray a = (JSONArray) obj.get("entries");

            for (Object o : a) {
                JSONObject intervent = (JSONObject) o;

                String type = (String) intervent.get("type");
                String party = (String) intervent.get("party");
                String speaker = (String) intervent.get("speaker");
                String text = (String) intervent.get("text");

                System.out.println("Type: " + type + ", by: " + speaker + ", of: " + party + "\n");
                System.out.println(text);
                System.out.println("\n\n");
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
