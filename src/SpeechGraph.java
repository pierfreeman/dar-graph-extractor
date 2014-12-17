import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class SpeechGraph {

    private SimpleGraph<Object, DefaultEdge> graph;

    public SpeechGraph() {
        graph = new SimpleGraph<Object, DefaultEdge>(DefaultEdge.class);
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
