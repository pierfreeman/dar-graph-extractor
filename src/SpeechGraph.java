import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class SpeechGraph {

    private SimpleWeightedGraph<Object, DefaultWeightedEdge> graph;

    public SpeechGraph() {
        graph = new SimpleWeightedGraph<Object, DefaultWeightedEdge>(DefaultWeightedEdge.class);
    }

    public SimpleWeightedGraph getGraph(){
        return graph;
    }

    public void addVertex(String v){
        graph.addVertex(v);
    }

    public void addEdge(String v, String v1){
        graph.addEdge(v, v1);
    }

    public void setWeight(String source, String target, double weight){
        graph.setEdgeWeight(graph.getEdge(source, target), weight);
    }

    public double getWeight(String source, String target){
        return graph.getEdgeWeight(graph.getEdge(source, target));
    }
}
