public class Pair {
    protected GraphNode node;

    protected RootedTree parent;

    public Pair(GraphNode node,RootedTree parent){
        this.node=node;
        this.parent=parent;
    }

    public GraphNode getNode(){
        return this.node;
    }

    public RootedTree getParent(){
        return this.parent;
    }
}
