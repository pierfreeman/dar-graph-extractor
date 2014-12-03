import java.util.ArrayList;

public class Main {

    public static void addPaths(ArrayList<String> listOfPaths){
        String path;
        int cont = 0;

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

    public static void main(String[] args){
        JSONReader reader = new JSONReader();
        ArrayList<String> arrayPath = new ArrayList<String>(145);

        addPaths(arrayPath);

        for (String p : arrayPath)
            reader.read("resources/dataset/" + p);
    }
}
