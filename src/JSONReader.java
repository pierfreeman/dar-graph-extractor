import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public void read(String path, SpeechGraph graph, ArrayList<Person> persons) {
        try {
            obj = (JSONObject) parser.parse(new FileReader(path));

            entries = (JSONArray) obj.get("entries");

            String currentSpeaker = null;
            Person p = null;

            for (Object o : entries) {
                intervention = (JSONObject) o;

                type = (String) intervention.get("type");
                party = (String) intervention.get("party");
                speaker = (String) intervention.get("speaker");
                text = (String) intervention.get("text");

                //printOnConsole();

                // Checks interactions between speakers
                if (speaker != null) {
                    boolean found = false;
                    if (currentSpeaker == null) {
                        for (Person per: persons) {
                            if (per.name.equals(speaker)) {
                                p = per;
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            p = new Person (speaker, party);
                            persons.add(p);
                        }
                        currentSpeaker = speaker;
                        //System.out.println("Speaker: " + speaker);
                    } else {
                        if (!speaker.equals(currentSpeaker)) {
                            p.setConnection(speaker);
                            for (Person per: persons) {
                                if (per.name.equals(speaker)) {
                                    p = per;
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                p = new Person(speaker, party);
                                persons.add(p);
                            }

                            currentSpeaker = speaker;
                            //System.out.println("Speaker: " + speaker);
                        }
                    }
                }

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