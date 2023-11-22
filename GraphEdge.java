public class GraphEdge {
    protected Node<GraphNode> scr;
    protected Node<GraphNode> dest;

    public GraphEdge(GraphNode v1, GraphNode v2) {
        this.scr=new Node<>(v1);
        this.dest=new Node<>(v2);
    }

    public Node<GraphNode> getScr() {return scr;}

    public Node<GraphNode> getDest() {return dest;}
}
