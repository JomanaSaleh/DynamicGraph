public class DynamicGraph {

   protected list<GraphNode> nodes;
    public DynamicGraph() {
        this.nodes= new list<>();
    }



    GraphNode insertNode(int nodeKey){
        GraphNode newNode=new GraphNode(nodeKey);
        this.nodes.insert_first(newNode.getSelf());
        return newNode;
    }

    public void deleteNode(GraphNode node) {
        if (node.getInDegree() != 0 || node.getOutDegree() != 0) {
            return;
        }
        this.nodes.removee(node.getSelf());
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to) {
        GraphEdge newGraphEdge = new GraphEdge(from, to);
        from.addOutDegree(newGraphEdge.getDest());
        to.addInDegree(newGraphEdge.getScr());
        return newGraphEdge;
    }

    public void deleteEdge(GraphEdge edge) {
        edge.getScr().getData().removeOutDegreeVertex(edge.getDest());
        edge.getDest().getData().removeInDegreeVertex(edge.getScr());
    }

    public void clear_visited() {
        Node<GraphNode> curr = this.nodes.getFirst();
        while(curr != this.nodes.getTail()){
            curr.getData().clearVisited();
            curr = curr.getNext();
        }
    }

     public RootedTree bfs(GraphNode source){
        clear_visited();

        list<Pair> queue = new list<>();

        source.set_visited();

        Pair curr_pair = new Pair(source, null);

        queue.insert1(curr_pair);

        RootedTree tree = new RootedTree(source.getKey());

        while(queue.getSize() > 0){
            Pair pair = queue.pop_first();
            GraphNode node = pair.getNode();

            RootedTree curr_parent = tree;
            if (pair.getParent()!=null) {
                curr_parent = pair.getParent().insert_leftChild(node.getKey());
            }

            Node<GraphNode> curr_neighbour = node.getOutDegreeVertex().getFirst();
            while(curr_neighbour != node.getOutDegreeVertex().getTail()){
                if (curr_neighbour.getData().get_visited()) {
                    curr_neighbour = curr_neighbour.getNext();
                    continue;
                }
                curr_neighbour.getData().set_visited();
                curr_pair = new Pair(curr_neighbour.getData(), curr_parent);
                queue.insert1(curr_pair);
                curr_neighbour = curr_neighbour.getNext();
            }
        }
        return tree;
    }


    RootedTree scc()  {
        clear_visited();
        list<GraphNode> list = new list<>();
        RootedTree tree = new RootedTree(0);

        Node<GraphNode> curr = this.nodes.getFirst();
        while(curr != this.nodes.getTail()){
            if (!curr.getData().get_visited()) {
                dfs_to_list(curr.getData(), list);
            }
            curr = curr.getNext();
        }

        clear_visited();
        curr = list.getTail();
        while(curr != list.getFirst()){
            curr = curr.getPrev();

            if (!curr.getData().get_visited()) {
                tree.insert_sibling(dfs_to_tree(curr.getData()));
            }

        }
        tree.make_parent_of_rightSiblings();
        return tree;
    }

    static void dfs_to_list(GraphNode node, list<GraphNode> list) {

        list<GraphNode> neighbours = node.getOutDegreeVertex();
        Node<GraphNode> curr_neighbour = neighbours.getFirst();
        node.set_visited();
        while(curr_neighbour != neighbours.getTail()){
            if (!curr_neighbour.getData().get_visited()) {
                dfs_to_list(curr_neighbour.getData(), list);
            }
            curr_neighbour = curr_neighbour.getNext();
        }
        list.insert1(node);
    }

    static RootedTree dfs_to_tree(GraphNode node) {
        RootedTree tree = new RootedTree(node.getKey());

        list<GraphNode> neighbours = node.getInDegreeVertex();
        Node<GraphNode> curr_neighbour = neighbours.getFirst();
        node.set_visited();

        while(curr_neighbour != neighbours.getTail()){
            if (!curr_neighbour.getData().get_visited()) {
                tree.insert_sibling((dfs_to_tree(curr_neighbour.getData())));
            }
            curr_neighbour = curr_neighbour.getNext();
        }
        tree.make_parent_of_rightSiblings();
        return tree;
    }
}