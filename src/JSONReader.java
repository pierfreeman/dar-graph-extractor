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

    public void read(String path, SpeechGraph graph) {
        try {
            obj = (JSONObject) parser.parse(new FileReader(path));

            entries = (JSONArray) obj.get("entries");

            String currentSpeaker = null;
            ArrayList<Person> persons = new ArrayList<Person>();
            ArrayList <String> interventions = new ArrayList <String>();
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
                    if (currentSpeaker == null) {
                        p = new Person (speaker);
                        persons.add(p);
                        currentSpeaker = speaker;
                        //System.out.println("Speaker: " + speaker);
                    } else {
                        if (!speaker.equals(currentSpeaker)) {
                            p.setConnection(speaker);
                            boolean found = false;
                            for (Person per: persons) {
                                if (per.name.equals(speaker)) {
                                    p = per;
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                p = new Person (speaker);
                                persons.add(p);
                            }

                            currentSpeaker = speaker;
                            //System.out.println("Speaker: " + speaker);
                        }
                    }
                }

                // Collects intervention types
                if (speaker != null) {
                    if (!interventions.contains(type)) {
                        interventions.add(type);
                    }
                }
            }

            // Prints persons and connections

            for (Person per : persons) {
                System.out.println("Person: " + per.name);
                graph.addVertex(per.name);
                System.out.println("Connections: " + per.getConnections().toString());
            }
            for (Person per : persons)
                for (String con : per.getConnections())
                    graph.addEdge(per.name, con);
            /*
            // Print intervention types
            System.out.println("Intervetion Types: ");
            for (String ints : interventions) {
                System.out.println(ints);
            }*/

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