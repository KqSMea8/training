package seker.datastructure.tree;

public class Node3<DATA> extends Node2<DATA> {
    public Node3<DATA> pch;

    public Node3(DATA d, Node3<DATA> l, Node3<DATA> r) {
        super(d, l, r);
    }
    
    public Node3(DATA d, Node3<DATA> l, Node3<DATA> r, Node3<DATA> p) {
        this(d, l, r);
        setParent(p);
    }

    public void setParent(Node3<DATA> p) {
        pch = p;
    }
}
