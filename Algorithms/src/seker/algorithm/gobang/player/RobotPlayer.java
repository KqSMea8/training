/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.algorithm.gobang.player;

import java.util.List;

import seker.algorithm.gobang.Game;
import seker.algorithm.gobang.ai.AI;
import seker.algorithm.gobang.ai.RobotAI;
import seker.algorithm.gobang.chessboard.Cell;
import seker.algorithm.gobang.chessboard.ChessBoard;

/**
 * @author liuxinjian
 * @since 2013-2-21
 */
public class RobotPlayer extends AbsPlayer {

    /**
     * Robot玩家的人工智能
     */
    protected final AI ai;

    /**
     * 构造方法
     * 
     * @param name  该玩家持有的棋子 {@link Chess}
     * @param chess 该玩家的名称
     */
    public RobotPlayer(String name, byte chess) {
        super(name, chess);
        ai = new RobotAI(name);
    }

    @Override
    public int play(ChessBoard chessboard, List<Cell> cells) {
        if (null == cells) {
            throw new IllegalArgumentException("RobotPlayer.play(cells == null)");
        }
        
        cells.clear();
        Cell best = ai.findBestCell(chessboard, chess);
        if (null == best) {
            return Game.GAME_DEUCE;
        } else {
            List<Cell> cs = chessboard.down(best.x, best.y, chess);
            System.out.println(name + String.format(".down(x=%1$2d,y=%2$2d,chess=%3$2d)", best.x, best.y, chess));
            chessboard.print();
            if (null == cs) {
                return Game.GAME_PROCEED;
            } else {
                cells.add(best);
                cells.addAll(cs);
                return Game.GAME_WIN;
            }
        }
    }
}
