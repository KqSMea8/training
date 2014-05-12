package seker.algorithm.gobang.ai;

import seker.algorithm.gobang.chessboard.Cell;
import seker.algorithm.gobang.chessboard.ChessBoard;

/**
 * 五子棋的人工智能接口
 * 
 * @author seker
 */
public interface AI {
    /**
     * 找到最佳的落子点
     * 
     * @param chessboard    棋盘
     * @param chess         要落下的棋子：AI本身是没有立场的，所以需要传入要落下的棋子。
     * @return              AI找出自认为最佳的落子位置，如果返回null,表示棋盘满为止。
     */
    Cell findBestCell(ChessBoard chessboard, byte chess);
}
