package seker.datastructure.tree;

public class Node2<DATA> {
    public DATA       data;
    public Node2<DATA> lch;
    public Node2<DATA> rch;
    
    public Node2(DATA d, Node2<DATA> l, Node2<DATA> r) {
        data = d;
        lch = l;
        rch = r;
    }
}
