/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.algorithm.gobang.chessboard;

import java.util.ArrayList;
import java.util.List;

import seker.algorithm.gobang.Game;
import seker.datastructure.linearlist.Stack;

/**
 * 
 * @author liuxinjian
 * @since 2013-2-21
 */
public class ChessBoard {
    
    /**
     * 只存放两个AI4的棋子，不存放当前落下的棋子的
     */
    private List<Cell> cache = new ArrayList<Cell>(Game.FOUR);
    
    /**
     * 存储已落下的棋子的栈，用于悔棋
     */
    private Stack<Cell> stack = new Stack<Cell>();
    
    /**
     * 棋盘上的Cell二维数组
     */
    private final Cell[][] cells;
    
    /**
     * 构造方法
     * 
     * @param size  棋盘的横竖尺寸
     */
    public ChessBoard(final byte size) {
        cells = new Cell[size][size];
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }
    
    /**
     * 取得棋盘上的Cell二维数组
     * 
     * @return  棋盘上的Cell二维数组
     */
    public Cell[][] getCells() {
        return cells;
    }
    
    /**
     * 落棋子
     * @param x         目标X坐标
     * @param y         目标Y坐标
     * @param chess     目标棋子{@link Chess}}
     * @return null:此处不能落棋子
     *         不为null，但是size为1，落子成功，但是未连珠
     *         不为null，但是size大于1，落子成功，且连珠成功
     */
    public List<Cell> down(byte x, byte y, byte chess) {
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length) {
            throw new IllegalArgumentException(String.format("ChessBoard.down(x=%2d,y=%2d,chess=%2d)", x, y, chess));
        } else if (Chess.EMPTY == chess) {
            throw new IllegalArgumentException("ChessBoard.down(Chess.EMPTY == chess)");
        } else if (Chess.EMPTY != cells[x][y].getChess()) {
            print();
            throw new IllegalArgumentException("ChessBoard.down(Target cell is not Chess.EMPTY:" + cells[x][y] + ")");
        } else {
            cells[x][y].setChess(chess);
            stack.push(cells[x][y]);

            return collect(cells[x][y]);
        }
    }
    
    /**
     * 悔棋：半回合：单步
     * @return  悔出的棋子
     */
    public Cell up() {
        Cell cell = stack.pop();
        if (null != cell) {
            cell.setChess(Chess.EMPTY);
        } else {
            throw new RuntimeException("queue is empty.");
        }
        return cell;
    }
    
    /**
     * 悔棋：一回合：两步
     * @return  悔出的第二步的棋子
     */
    public Cell up2() {
        up();
        return up();
    }
    
    /**
     * 棋盘上的最后一次落子
     * @return  最后一次落子
     */
    public Cell getLastDown() {
        return stack.peek();
    }
    
    /**
     * 收集棋子以判断是否连珠成功
     * @param cell  当前的落子
     * @return      如果落子后成{@Game.FIVE}了，则返回相应的四个棋子
     */
    private List<Cell> collect(final Cell cell) {
        /*朝各个方向试探*/
        int x = 0;
        int y = 0;
        int deltaX = 0;
        int deltaY = 0;
        
        for (byte direction : Direction.DIRECTIONS) {  // 在某一个方向上着探索
            cache.clear();
            
            for (byte step : Direction.STEPS) {       // 在某一个方向上前进/后退着探索
                deltaX = Direction.DELTAS[direction][0] * step;
                deltaY = Direction.DELTAS[direction][1] * step;
                
                x = cell.x + deltaX;
                y = cell.y + deltaY;

                while (x >= 0 && x < cells.length 
                    && y >= 0 && y < cells[0].length 
                    && cell.getChess() == cells[x][y].getChess()) {
                    
                    cache.add(cells[x][y]);
                    
                    if (cache.size() == Game.FOUR) {
                        return new ArrayList<Cell>(cache);
                    }
                    
                    x += deltaX;
                    y += deltaY;
                }
            }
        }
        return null;
    }
    
    /**
     * 打印棋盘，调试用
     */
    public void print() {
        for (byte i = 0; i < cells.length; i++) {
            for (byte j = 0; j < cells[0].length; j++) {
                System.out.print(String.format("%1$2d", cells[i][j].getChess()));
            }
            System.out.println();
        }
    }
}
