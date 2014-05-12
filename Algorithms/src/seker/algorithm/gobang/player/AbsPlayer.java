/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.algorithm.gobang.player;

import java.util.List;

import seker.algorithm.gobang.chessboard.Cell;
import seker.algorithm.gobang.chessboard.ChessBoard;

/**
 * 抽象的五子棋玩家：Robot玩家/真实玩家 都将继承此类
 * @author liuxinjian
 * @since 2013-2-21
 */
public abstract class AbsPlayer {
    
    /**
     * 该玩家持有的棋子 {@link Chess}
     */
    protected byte chess;
    
    /**
     * 该玩家的名称
     */
    public final String name;
    
    /**
     * 构造方法
     * 
     * @param n     该玩家持有的棋子 {@link Chess}
     * @param c     该玩家的名称
     */
    public AbsPlayer(String n, byte c) {
        name = n;
        chess = c;
    }
    
    /**
     * 玩家在棋盘上落子 
     * 
     * @param chessboard    棋盘
     * @param cells         传出的参数，传入null会导致异常。
     *                      用于装载返回值为Game.GAME_OVER_WIN时，成5的五个棋子的Cell位置。
     * 
     * @return 是否游戏结束：Game.GAME_OVER_DEUCE、Game.GAME_PROCEED、Game.GAME_OVER_WIN
     */
    public abstract int play(ChessBoard chessboard, List<Cell> cells);
}
