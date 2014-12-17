import java.util.ArrayList;

public class Reasoner {

    private static String currentSpeaker = null;
    private static Person p = null;

    private Reasoner(){}

    public static void checksInteraction(String speaker, String party, ArrayList<Person> persons){
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
}
