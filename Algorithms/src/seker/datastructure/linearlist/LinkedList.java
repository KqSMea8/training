package seker.datastructure.linearlist;

/**
 * 线性表的定义
 * 线性表是最简单、最常用的一种数据结构。是由n（n≥0）个数据元素（结点）a1，a2，…，an组成的有限序列。
 * 对于非空的线性表：
 *   ① 有且仅有一个开始结点a1，没有直接前趋，有且仅有一个直接后继a2;
 *   ② 有且仅有一个终结结点an，没有直接后继，有且仅有一个直接前趋an-1;
 *   ③ 其余的内部结点ai（2≤i≤n-1）都有且仅有一个直接前趋ai-1。
 *   ④ 其余的内部结点ai（2≤i≤n-1）都有且仅有一个后继结点ai+1。
 *   
 * 线性表各种运算简介
 *  对于线性表要经常进行的基本运算主要有以下几种：
 *      （1）求线性表的表长。
 *      （2）取线性表中的第i个元素。
 *      （3）修改线性表中的第i个元素。
 *      （4）删除线性表中的第i个元素。
 *      （5）在线性表中第i个元素之后（或之前）插入一个新的元素。
 *      （6）按某种要求重新排列线性表中各元素的顺序。
 *      （7）按某个特定值查找线性表中元素。
 *  线性表的其他复杂运算还有：线性表的合并；对有序表的插入、删除等。
 */
public class LinkedList<E> {
    
    protected java.util.LinkedList<E> list;
}
