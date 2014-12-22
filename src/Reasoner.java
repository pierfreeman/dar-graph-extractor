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

            speaker = speaker.toLowerCase();
            boolean found = false;
            if (currentSpeaker == null) {
                // Checks if it is a revelant speaker and adds to persons
                if (!type.equals("secretario") && !type.equals("secretario_continuacao")
                    && !type.equals("presidente") && !type.equals("presidente_continuacao")
                    && !type.equals("presidente_aparte") && !type.equals("presidente_encerrada")
                    && !type.equals("presidente_aberta") && !type.equals("presidente_temapalavra")) {
                    found = false;
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

                        //sets interruption connection with its positive or negative weight
                        p.setConnection(speaker, evaluateConnection(text));

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

    private static int evaluateConnection(String text) {
        //Default weight assumes negative interaction
        int weight = -1;
        // Positive and supportive remarks
        if (text.equals("Muito bem!")
            || text.equals("Exactamente!")
            || text.equals("É verdade!")
            || text.equals("Exato!")
            || text.equals("Claro!")
            || text.equals("Isso é verdade!")
            || text.equals("Exacto, exacto!")
            || text.equals("É verdade! É verdade!")
            || text.equals("Boa pergunta!")
            || text.equals("Bem recordado!")
            || text.equals("Bem lembrado!")
            || text.equals("Ora bem!")
            || text.equals("Bem observado!")
            || text.equals("Muito bem lembrado!")
            || text.equals("Pois é!")
            || text.equals("Essa é que é a verdade!")
            || text.equals("Ah, pois é!")
            || text.equals("Ora aí está!")
            || text.equals("Pois claro!")) {
            weight = 3;
        } else if (text.equals("Sim!")) { // Neutral remarks
            weight = 0;
        }
        return weight;
    }
}
