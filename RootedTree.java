import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    protected int key;
    protected RootedTree leftChild;
    protected RootedTree rightSibling;
    


    public RootedTree() {
    }

    public RootedTree(int key) {
        this.key = key;
        this.leftChild = null;
        this.rightSibling = null;
    }

    RootedTree insert_sibling(RootedTree rightSibling) {
        RootedTree prev_sibling = this.rightSibling;
        this.rightSibling = rightSibling;
        rightSibling.rightSibling = prev_sibling;
        return rightSibling;
    }


    public int getKey() {
        return this.key;
    }


    void make_parent_of_rightSiblings() {
        this.leftChild = this.rightSibling;
        this.rightSibling = null;
    }

    RootedTree insert_leftChild(int key) {
        RootedTree leftChild = new RootedTree(key);
        RootedTree pre_leftChild = this.leftChild;
        this.leftChild = leftChild;
        leftChild.rightSibling = pre_leftChild;
        return leftChild;
    }

    void printByLayer(DataOutputStream out) throws IOException {
        String str = "";
        String want = "";
        list<RootedTree> queue = new list<>();
        queue.insert1(this);
        while (queue.getSize() > 0) {
            Node<RootedTree> curr = queue.getFirst();

            while (curr != queue.getTail()) {
                RootedTree data = curr.getData();
                str = str + (String.valueOf(data.key));
                RootedTree child = data.leftChild;

                while (child != null) {
                    queue.insert_before1(child, curr);
                    child = child.rightSibling;
                }
                Node<RootedTree> next = curr.getNext();
                queue.removee(curr);
                curr = next;
                if (next != queue.getTail()) {
                    str = str + ",";

                }
            }
            if (queue.getSize() > 0) {
                String[] f = str.split(",");
                for (int i = f.length - 1; i > 0; i--) {
                    want = want + f[i] + ",";
                }
                want = want + f[0];
                want = want + "\n";
                str = "";

            }

        }
        if (!str.isEmpty()) {
            String[] f = str.split(",");
            for (int i = f.length - 1; i > 0; i--) {
                want = want + f[i] + ",";
            }
            want = want + f[0];
            out.writeBytes(want);
            str = "";
        } else
            out.writeBytes(want);
    }
    private String recPreOrder(RootedTree curr,String string)
    {
        if(curr == null)
        {
            return string;
        }

        string = recPreOrder(curr.leftChild, string);
        string = string + curr.getKey() + ",";

        string = recPreOrder(curr.rightSibling, string);
        return string;
    }



    public void preorderPrint(DataOutputStream out) throws IOException {
        String want="";
        String x=recPreOrder(this,"");
        if (!x.isEmpty()) {
            String[] f = x.split(",");
            for (int i = f.length - 1; i > 0; i--) {
                want = want + f[i] + ",";
            }
            want = want + f[0];
            out.writeBytes(want);
        } else
            out.writeBytes(want);
    }
}





