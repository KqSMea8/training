/*
 * Copyright (C) 2012 Baidu Inc. All rights reserved.
 */
package seker.algorithm.maze;

/**
 * @author liuxinjian
 * @since 2012-8-29
 */
public class Cell {
    int x;
    int y;
    int px;
    int py;
    
    public Cell(int x, int y, int px, int py) {
        this.x = x;
        this.y = y;
        this.px = px;
        this.py = py;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getPreX() {
        return px;
    }
    
    public int getPreY() {
        return py;
    }
    
    @Override
    public String toString() {
        return String.format("(x=%2d,y=%2d,px=%2d,py=%2d)", x, y, px, py);
    }
}
