import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class SpeechGraph {

    private UndirectedGraph<String, DefaultEdge> graph;

    public SpeechGraph() {

        graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
    }

    public UndirectedGraph getGraph(){
        return graph;
    }

    public void addVertex(String v){
        graph.addVertex(v);
    }

    public void addEdge(String v, String v1){
        graph.addEdge(v, v1);
    }
}
