public class GraphNode  {
    protected int Key;
    protected int outDegree;
    protected int inDegree;
    protected Node<GraphNode> self;
    protected list<GraphNode> outDegreeVertex;
    protected list<GraphNode> inDegreeVertex;
    protected boolean visited;

    public GraphNode() {}

    public GraphNode(int nodeKey) {
        this.Key = nodeKey;
        this.outDegree = 0;
        this.inDegree = 0;
        this.outDegreeVertex=new list<>();
        this.inDegreeVertex=new list<>();
        this.self=new Node<>(this);
        this.visited=false;
    }
    public int getKey() {
        return this.Key;
    }

    public int getInDegree() {
        return this.inDegree;
    }

    public int getOutDegree() {
        return this.outDegree;
    }

    public Node<GraphNode> getSelf(){return this.self;}

    public boolean get_visited(){ return this.visited; }

    public void set_visited(){ this.visited = true; }

    public void clearVisited(){ this.visited = false; }

    list<GraphNode> getOutDegreeVertex() { return this.outDegreeVertex; }

    list<GraphNode> getInDegreeVertex() { return this.inDegreeVertex; }


    void removeOutDegreeVertex(Node<GraphNode> neighbour){
        this.outDegreeVertex.removee(neighbour);
        this.outDegree--;
    }

    void removeInDegreeVertex(Node<GraphNode> neighbour){
        this.inDegreeVertex.removee(neighbour);
        this.inDegree--;
    }
    public void addOutDegree(Node<GraphNode> neighbour){
        this.outDegreeVertex.insert_first(neighbour);
        this.outDegree++;
    }
    public void addInDegree(Node<GraphNode>neighbour){
        this.inDegreeVertex.insert_first(neighbour);
        this.inDegree++;
    }
}
