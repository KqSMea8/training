package seker.datastructure.tree;

public class ThreadedNode3<DATA> extends Node3<DATA> {
    public int ltag;

    public int rtag;
    
    /**
     * ltag = {
     *  0:  lch是指向节点的左孩子的指针
     *  1:  lch是指向节点的前驱的左线索
     * }
     * 
     * rtag = {
     *  0:  rch是指向节点的右孩子的指针
     *  1:  rch是指向节点的后驱的右线索
     * }
     */

    public ThreadedNode3(DATA d, Node3<DATA> l, Node3<DATA> r) {
        super(d, l, r);
    }

    public ThreadedNode3(DATA d, Node3<DATA> l, Node3<DATA> r, Node3<DATA> p) {
        super(d, l, r, p);
    }

}
