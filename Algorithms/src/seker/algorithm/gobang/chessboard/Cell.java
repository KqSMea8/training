/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.algorithm.gobang.chessboard;


/**
 * 棋盘上的单个Cell，落子点
 * 
 * @author liuxinjian
 * @since 2013-2-21
 */
public class Cell {

    /**
     * 当前的棋子类型{@link Chess}
     */
    private byte mChess;

    /**
     * Cell在棋盘上的X坐标
     */
    public final byte x;

    /**
     * Cell在棋盘上的Y坐标
     */
    public final byte y;

    /**
     * 构造方法
     * 
     * @param xx Cell在棋盘上的X坐标
     * @param xy Cell在棋盘上的Y坐标
     */
    public Cell(byte xx, byte xy) {
        this(xx, xy, Chess.EMPTY);
    }

    /**
     * 构造方法
     * 
     * @param xx    Cell在棋盘上的X坐标
     * @param yy    Cell在棋盘上的Y坐标
     * @param chess 棋子类型{@link Chess}
     */
    public Cell(byte xx, byte yy, byte chess) {
        x = xx;
        y = yy;
        mChess = chess;
    }

    /**
     * 取得棋子类型
     * 
     * @return 棋子类型{@link Chess}
     */
    public byte getChess() {
        return mChess;
    }

    /**
     * 设置棋子类型
     * 
     * @param chess 棋子类型{@link Chess}
     */
    public void setChess(byte chess) {
        mChess = chess;
    }
    
    /**
     * 当前Cell是否为空格
     * 
     * @return  true：空格，可以落子；false，非空格，不可落子
     */
    public boolean isEmpty() {
        return Chess.EMPTY == mChess;
    } 

    @Override
    public String toString() {
        return String.format("(x=%1$2d,y=%2$2d,chess=%3$2d)", x, y, mChess);
    }
    
    /**
     * 棋子的坐标
     * 
     * @return 棋子的坐标
     */
    public String position() {
        return String.format("(x=%1$2d,y=%2$2d)", x, y);
    }
}
