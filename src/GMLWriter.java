import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GMLWriter {

    private GmlCustomExporter exporter;
    private File file;
    private FileWriter fw;

    //instantiate the exporter
    public GMLWriter(){
        exporter = new GmlCustomExporter();
    }

    //export to file
    public void writeGraph(SimpleWeightedGraph g) throws IOException {
        file = new File("resources/graph.gml");
        fw = new FileWriter(file.getAbsoluteFile());
        exporter.setPrintLabels(exporter.PRINT_EDGE_VERTEX_LABELS);
        exporter.export(fw, g);
        fw.close();
    }
}
