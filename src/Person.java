import java.util.ArrayList;

public class Person {
    
    String name, affiliation;
    ArrayList<String> connections;
    
    public Person (String name, String affiliation) {
        this.name = name;
        this.affiliation = affiliation;
        connections = new ArrayList<String>();
    }
    
    public void setConnection (String name) {
        if (!connections.contains(name)) {
            connections.add(name);
            //System.out.println("Connection added: " + this.name + " to " + name);
        }
    }
    
    public ArrayList<String> getConnections () {
        return connections;
    }
}
