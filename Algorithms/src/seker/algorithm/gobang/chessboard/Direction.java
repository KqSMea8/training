/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.algorithm.gobang.chessboard;

/**
 * 定义方向相关的常量
 * 
 * @author liuxinjian
 * @since 2013-2-21
 */
public final class Direction {
    
    /**
     * 辅助类，私有构造方法
     */
    private Direction() {
        
    }
    
    /**
     * 从左到右
     */
    private static final byte L2R = 0;
    
    /**
     * 从上到下
     */
    private static final byte T2B = 1;
    
    /**
     * 从左上到右下
     */
    private static final byte LT2RB = 2;
    
    /**
     * 从左下到右上
     */
    private static final byte LB2RT = 3;
    
    /**
     * 四个方向
     */
    public static final byte[] DIRECTIONS = {Direction.L2R,  Direction.T2B, Direction.LT2RB, Direction.LB2RT};
    
    /**
     * 四个方向偏移量
     */
    public static final byte[][] DELTAS = {{0, 1},  {1, 0}, {1, 1}, {-1, 1}};
    
    /**
     * 探索方向：前进/后退
     */
    public static final byte[] STEPS = {1, -1};
}
