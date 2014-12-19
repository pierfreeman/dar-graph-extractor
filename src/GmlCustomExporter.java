//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package org.jgrapht.ext;

import org.jgrapht.Graph;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.IntegerEdgeNameProvider;
import org.jgrapht.ext.IntegerNameProvider;
import org.jgrapht.ext.VertexNameProvider;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;

public class GmlCustomExporter<V, E> {
    private static final String creator = "JGraphT GML Exporter";
    private static final String version = "1";
    private static final String delim = " ";
    private static final String tab1 = "\t";
    private static final String tab2 = "\t\t";
    public static final Integer PRINT_NO_LABELS = Integer.valueOf(1);
    public static final Integer PRINT_EDGE_LABELS = Integer.valueOf(2);
    public static final Integer PRINT_EDGE_VERTEX_LABELS = Integer.valueOf(3);
    public static final Integer PRINT_VERTEX_LABELS = Integer.valueOf(4);
    private Integer printLabels;
    private VertexNameProvider<V> vertexIDProvider;
    private VertexNameProvider<V> vertexLabelProvider;
    private EdgeNameProvider<E> edgeIDProvider;
    private EdgeNameProvider<E> edgeLabelProvider;

    public GmlCustomExporter() {
        this(new IntegerNameProvider(), (VertexNameProvider)null, new IntegerEdgeNameProvider(), (EdgeNameProvider)null);
    }

    public GmlCustomExporter(VertexNameProvider<V> vertexIDProvider, VertexNameProvider<V> vertexLabelProvider, EdgeNameProvider<E> edgeIDProvider, EdgeNameProvider<E> edgeLabelProvider) {
        this.printLabels = PRINT_NO_LABELS;
        this.vertexIDProvider = vertexIDProvider;
        this.vertexLabelProvider = vertexLabelProvider;
        this.edgeIDProvider = edgeIDProvider;
        this.edgeLabelProvider = edgeLabelProvider;
    }

    private String quoted(String s) {
        return "\"" + s + "\"";
    }

    private void exportHeader(PrintWriter out) {
        out.println("Creator " + this.quoted("JGraphT GML Exporter"));
        out.println("Version 1");
    }

    private void exportVertices(PrintWriter out, Graph<V, E> g) {
        for(Iterator i$ = g.vertexSet().iterator(); i$.hasNext(); out.println("\t]")) {
            Object from = i$.next();
            out.println("\tnode");
            out.println("\t[");
            out.println("\t\tid " + this.vertexIDProvider.getVertexName((V)from));
            if(this.printLabels == PRINT_VERTEX_LABELS || this.printLabels == PRINT_EDGE_VERTEX_LABELS) {
                String label = this.vertexLabelProvider == null?from.toString():this.vertexLabelProvider.getVertexName((V)from);
                out.println("\t\tlabel " + this.quoted(label));
            }
        }

    }

    private void exportEdges(PrintWriter out, SimpleWeightedGraph<V, E> g) {
        for(Iterator i$ = g.edgeSet().iterator(); i$.hasNext(); out.println("\t]")) {
            Object edge = i$.next();
            out.println("\tedge");
            out.println("\t[");
            String id = this.edgeIDProvider.getEdgeName((E)edge);
            out.println("\t\tid " + id);
            String s = this.vertexIDProvider.getVertexName(g.getEdgeSource((E)edge));
            out.println("\t\tsource " + s);
            String t = this.vertexIDProvider.getVertexName(g.getEdgeTarget((E)edge));
            out.println("\t\ttarget " + t);
            if(this.printLabels == PRINT_EDGE_LABELS || this.printLabels == PRINT_EDGE_VERTEX_LABELS) {
                String label = this.edgeLabelProvider == null?edge.toString():this.edgeLabelProvider.getEdgeName((E)edge);
                out.println("\t\tlabel " + this.quoted(label));
            }
            Double w = g.getEdgeWeight((E)edge);
            out.println("\t\tweight " + w);
        }

    }

    private void export(Writer output, SimpleWeightedGraph<V, E> g, boolean directed) {
        PrintWriter out = new PrintWriter(output);
        Iterator i$ = g.vertexSet().iterator();

        while(i$.hasNext()) {
            Object from = i$.next();
            this.vertexIDProvider.getVertexName((V)from);
        }

        this.exportHeader(out);
        out.println("graph");
        out.println("[");
        out.println("\tlabel " + this.quoted(""));
        if(directed) {
            out.println("\tdirected 1");
        } else {
            out.println("\tdirected 0");
        }

        this.exportVertices(out, g);
        this.exportEdges(out, g);
        out.println("]");
        out.flush();
    }

//    public void export(Writer output, UndirectedGraph<V, E> ug) {
//        this.export(output, ug, false);
//    }
//
//    public void export(Writer output, DirectedGraph<V, E> ug) {
//        this.export(output, ug, true);
//    }

    public void export(Writer output, SimpleWeightedGraph<V, E> g) {
        this.export(output, g, false);
    }

    public void setPrintLabels(Integer i) {
        if(i != PRINT_NO_LABELS && i != PRINT_EDGE_LABELS && i != PRINT_EDGE_VERTEX_LABELS && i != PRINT_VERTEX_LABELS) {
            throw new IllegalArgumentException("Non-supported parameter value: " + Integer.toString(i.intValue()));
        } else {
            this.printLabels = i;
        }
    }

    public Integer getPrintLabels() {
        return this.printLabels;
    }
}
