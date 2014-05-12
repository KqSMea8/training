package seker.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 一、线索二叉树 
 * 定义: 
 *      n个结点的二叉链表中含有n+1个空指针域。利用二叉链表中的空指针域，存放指向结点在某种遍历次序下的前趋和后继结点的指针（这种附加的指针称为"线索"）。
 *      这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。 
 *      根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种。 
 * 注意:
 *      线索链表解决了二叉链表找左、右孩子困难的问题，出现了无法直接找到该结点在某种遍历序列中的前趋和后继结点的问题。 
 * 
 * 二、二叉树的线索化 
 *      1．线索化和线索化实质 将二叉树变为线索二叉树的过程称为线索化。
 *          按某种次序将二叉树线索化的实质是:按该次序遍历二叉树，在遍历过程中用线索取代空指针。
 */
public class ThreadedBinaryTree<DATA> extends BinaryTree<DATA, ThreadedNode3<DATA>> {

    public static final int LINK   = 0;
    public static final int THREAD = 1;

    public ThreadedBinaryTree(ThreadedNode3<DATA> r, int s) {
        super(r, s);
    }

    private ThreadedNode3<DATA> pre; // 全局量

    public void inOrderThreading() {
        inOrderThreading(root);
    }

    protected void inOrderThreading(ThreadedNode3<DATA> root) {
        if (null != root) {
            inOrderThreading((ThreadedNode3<DATA>) root.lch);
            root.ltag = (null == root.lch) ? LINK : THREAD;
            root.rtag = (null == root.rch) ? LINK : THREAD;
            if (null != pre) {
                if (pre.rtag == THREAD) {
                    pre.rch = root;
                }
                if (root.ltag == THREAD) {
                    root.lch = pre;
                }
            }
            pre = root;
            inOrderThreading((ThreadedNode3<DATA>) root.rch);
        }
    }
    
    
    
    /**
     * 遍历线索二叉树
     * 
     * 遍历某种次序的线索二叉树，只要从该次序下的开始结点开发，反复找到结点在该次序下的后继，直至终端结点。
     */
    public List<DATA> inOrderTraversal() {
        List<DATA> list = null;
        if (!empty()) {
            list = new ArrayList<DATA>(size);
            inOrderTraversal(root, list);
        }
        return list;
    }

    protected void inOrderTraversal(ThreadedNode3<DATA> root, List<DATA> list) {
        if(null != root) {       //树非空
            while(root.ltag==LINK) 
            root = (ThreadedNode3<DATA>) root.lch; //从根往下找最左下结点，即中序序列的开始结点
            do {
                list.add(root.data);//访问结点
                root = InorderSuccessor(root); //找*p的中序后继
            }while(null != root);
        }
    }
    
    /**
     * 在中序线索二叉树中求中序后继结点的过程
     */
    protected ThreadedNode3<DATA> InorderSuccessor(ThreadedNode3<DATA> root) {
        // 在中序线索树中找结点*p的中序后继，设p非空
        ThreadedNode3<DATA> q;
        if (root.rtag == THREAD) { // *p的右子树为空
            return (ThreadedNode3<DATA>) root.rch; // 返回右线索所指的中序后继
        } else {
            q = (ThreadedNode3<DATA>) root.rch; // 从*p的右孩子开始查找
            while (q.ltag == LINK)
                q = (ThreadedNode3<DATA>) q.lch; // 左子树非空时，沿左链往下查找
            return q; // 当q的左子树为空时，它就是最左下结点
        } // end if
    }
    
    public static void main(String[] args) {
        /**
          *         A
          *        / \
          *       B   C
          *      /   / \
          *     D   E   F
          */
         ThreadedNode3<String> d = new ThreadedNode3<String>("D", null, null);
         
         ThreadedNode3<String> b = new ThreadedNode3<String>("B", d, null);
         d.setParent(b);
         
         ThreadedNode3<String> e = new ThreadedNode3<String>("E", null, null);
         ThreadedNode3<String> f = new ThreadedNode3<String>("F", null, null);
         
         ThreadedNode3<String> c = new ThreadedNode3<String>("C", e, f);
         e.setParent(c);
         f.setParent(c);
         
         ThreadedNode3<String> a = new ThreadedNode3<String>("A", b, c);
         b.setParent(a);
         c.setParent(a);
         
         ThreadedBinaryTree<String> binarytree = new ThreadedBinaryTree<String>(a, 6);
         List<String> list = null;
         binarytree.inOrderThreading();
         list = binarytree.inOrderTraversal();
         System.out.println(list.toString());
//         list = binarytree.preOrderTraversal();
//         System.out.println(list.toString());
//         list = binarytree.inOrderTraversal();
//         System.out.println(list.toString());
//         list = binarytree.postOrderTraversal();
//         System.out.println(list.toString());
         
         
     }
}
