import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.GmlExporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GMLWriter {

    private GmlExporter exporter;
    private File file;
    private FileWriter fw;

    //instantiate the exporter
    public GMLWriter(){
        exporter = new GmlExporter();
    }

    //export to file
    public void writeGraph(UndirectedGraph g) throws IOException {
        file = new File("resources/graph.gml");
        fw = new FileWriter(file.getAbsoluteFile());
        exporter.setPrintLabels(exporter.PRINT_EDGE_VERTEX_LABELS);
        exporter.export(fw, g);
        fw.close();
    }
}
