import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void addPaths(ArrayList<String> listOfPaths){
        String path;
        int cont;

        for (int i=0; i<9; ++i){
            cont = i + 1;
            path = "dar_12_01_00" + cont + ".json";
            listOfPaths.add(path);
        }

        for (int i=9; i<99; ++i){
            cont = i + 1;
            path = "dar_12_01_0" + cont + ".json";
            listOfPaths.add(path);
        }

        for (int i=99; i<136; ++i){
            cont = i + 1;
            path = "dar_12_01_" + cont + ".json";
            listOfPaths.add(path);
        }

        for (int i=0; i<9; ++i){
            cont = i + 1;
            path = "dar_12_02_00" + cont + ".json";
            listOfPaths.add(path);
        }

        for (int i=9; i<19; ++i){
            cont = i + 1;
            path = "dar_12_02_0" + cont + ".json";
            listOfPaths.add(path);
        }
    }

    public static void main(String[] args) {
        JSONReader reader = new JSONReader();
        ArrayList<String> arrayPath = new ArrayList<String>(145);
        GMLWriter writer = new GMLWriter();
        SpeechGraph sg = new SpeechGraph();
        ArrayList<Person> persons = new ArrayList<Person>();

        addPaths(arrayPath);

        for (String p : arrayPath)
            reader.read("resources/dataset/" + p, persons);

        //Create the graph
        for (Person per : persons)
            sg.addVertex(per.name);

        for (Person per : persons)
            for (String con : per.getConnections()){
                sg.addEdge(per.name, con);
                sg.setWeight(per.name, con, per.getNumberConnection(con));
                System.out.println(per.name + " " + con + " " + sg.getWeight(per.name, con) + "\n");
            }


        try {
            //Write the .gml file
            writer.writeGraph(sg.getGraph());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
