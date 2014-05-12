package seker.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的性质
 * 二叉树具有以下重要性质：
 *  性质1.二叉树第i层上的结点数目最多为2^i-1(i≥1)。
 *  性质2.深度为k的二叉树至多有2^k-1个结点(k≥1)。
 *  性质3.在任意-棵二叉树中，若叶子结点（即度为0的结点）的个数为n0，度为1的结点数为n1，度为2的结点数为n2，则no=n2+1。
 *  性质4.具有n个结点的完全二叉树的深度为lg(n)+1或lg(n+1)
 */
public class BinaryTree<DATA, NODE extends Node2<DATA>> {
    protected int        size = 0;

    protected NODE root;
    
    public BinaryTree(NODE r, int s) {
        root = r;
        size = s;
    }

    public boolean empty() {
        return 0 == size;
    }

    public int size() {
        return size;
    }

    public List<DATA> preOrderTraversal() {
        List<DATA> list = null;
        if (!empty()) {
            list = new ArrayList<DATA>(size);
            preOrderTraversal(root, list);
        }
        return list;
    }

    protected void preOrderTraversal(Node2<DATA> root, List<DATA> list) {
        if (null != root) {
            list.add(root.data);
            preOrderTraversal(root.lch, list);
            preOrderTraversal(root.rch, list);
        }
    }

    public List<DATA> inOrderTraversal() {
        List<DATA> list = null;
        if (!empty()) {
            list = new ArrayList<DATA>(size);
            inOrderTraversal(root, list);
        }
        return list;
    }

    protected void inOrderTraversal(Node2<DATA> root, List<DATA> list) {
        if (null != root) {
            inOrderTraversal(root.lch, list);
            list.add(root.data);
            inOrderTraversal(root.rch, list);
        }
    }

    public List<DATA> postOrderTraversal() {
        List<DATA> list = null;
        if (!empty()) {
            list = new ArrayList<DATA>(size);
            postOrderTraversal(root, list);
        }
        return list;
    }

    protected void postOrderTraversal(Node2<DATA> root, List<DATA> list) {
        if (null != root) {
            postOrderTraversal(root.lch, list);
            postOrderTraversal(root.rch, list);
            list.add(root.data);
        }
    }
    
    public static void main(String[] args) {
       /**
         *         A
         *        / \
         *       B   C
         *      /   / \
         *     D   E   F
         */
        Node3<String> d = new Node3<String>("D", null, null);
        
        Node3<String> b = new Node3<String>("B", d, null);
        d.setParent(b);
        
        Node3<String> e = new Node3<String>("E", null, null);
        Node3<String> f = new Node3<String>("F", null, null);
        
        Node3<String> c = new Node3<String>("C", e, f);
        e.setParent(c);
        f.setParent(c);
        
        Node3<String> a = new Node3<String>("A", b, c);
        b.setParent(a);
        c.setParent(a);
        
        BinaryTree<String, Node3<String>> binarytree = new BinaryTree<String, Node3<String>>(a, 6);
        List<String> list = null;
        list = binarytree.preOrderTraversal();
        System.out.println(list.toString());
        list = binarytree.inOrderTraversal();
        System.out.println(list.toString());
        list = binarytree.postOrderTraversal();
        System.out.println(list.toString());
    }
}
