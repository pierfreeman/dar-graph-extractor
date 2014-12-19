import java.util.ArrayList;

public class Reasoner {

    private static String currentSpeaker = null;
    private static Person p = null;
    private static Boolean importantSpeaker = false;

    private Reasoner(){}

    public static void checksInteraction(String speaker, String party, ArrayList<Person> persons, String type, String text){

        //Checks if there is someone speaking and if it is an actual person
        if (speaker != null && !type.equals("documento") && !type.equals("inicio_documento")
            && !speaker.contains("Vozes") && !type.equals("vozes_aparte")){

            boolean found = false;
            if (currentSpeaker == null) {
                // Checks if it is a revelant speaker and adds to persons
                if (!type.equals("secretario") && !type.equals("secretario_continuacao")
                    && !type.equals("presidente") && !type.equals("presidente_continuacao")
                    && !type.equals("presidente_aparte") && !type.equals("presidente_encerrada")
                    && !type.equals("presidente_aberta") && !type.equals("presidente_temapalavra")) {
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
                        //System.out.println("Speaker: " + speaker);
                    }
                    importantSpeaker = true;
                }
            } else if (importantSpeaker){ //Ministers were talking
                //System.out.println("MINISTER: " + currentSpeaker + " Next Speaker: " + speaker + " Type: " + type);
                if (!speaker.equals(currentSpeaker)) { // Avoids repetitions
                    // Checks if minister is interrupted by another minister
                    if (!type.equals("secretario") && !type.equals("secretario_continuacao")
                        && !type.equals("presidente") && !type.equals("presidente_continuacao")
                        && !type.equals("presidente_aparte") && !type.equals("presidente_encerrada")
                        && !type.equals("presidente_aberta") && !type.equals("presidente_temapalavra")) {

                        p.setConnection(speaker); //sets interruption connection
                        //System.out.println (text);
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
                            //System.out.println("Speaker Added: " + speaker);
                        }
                    } else {
                        importantSpeaker = false;
                    }
                }
            } else { //President or secretary was the previous person talking
                //System.out.println("PRESIDENT: " + currentSpeaker + " Next Speaker: " + speaker + " Type: " + type);
                if (!type.equals("secretario") && !type.equals("secretario_continuacao")
                    && !type.equals("presidente") && !type.equals("presidente_continuacao")
                    && !type.equals("presidente_aparte") && !type.equals("presidente_encerrada")
                    && !type.equals("presidente_aberta") && !type.equals("presidente_temapalavra")) {
                    importantSpeaker = true;
                    found = false;
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
                        //System.out.println("Speaker added: " + speaker);
                    }
                }
            }
            currentSpeaker = speaker;
            //System.out.println("Speaker: " + speaker);
        }
    }
}
