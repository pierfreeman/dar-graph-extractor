import java.util.ArrayList;
import java.util.HashMap;

public class Person {
    
    String name, affiliation;
    HashMap <String, Integer> connections;
    
    public Person (String name, String affiliation) {
        this.name = name;
        this.affiliation = affiliation;
        connections = new HashMap <String, Integer>();
    }
    
    public void setConnection (String name) {
        if (!connections.containsKey(name)) {
            connections.put(name, 1);
            //System.out.println("Connection added: " + this.name + " to " + name);
        } else {
            Integer value = (Integer) connections.get(name);
            connections.put(name, value+1);
        }
    }
    
    public ArrayList<String> getConnections () {
        return new ArrayList <String> (connections.keySet());
    }

    public ArrayList<Integer> getWeights () {
        return new ArrayList <Integer> (connections.values());
    }

    public HashMap <String, Integer> getHashMap() {
        return connections;
    }

    public double getNumberConnection (String target){
        return (double) connections.get(target);
    }
}
