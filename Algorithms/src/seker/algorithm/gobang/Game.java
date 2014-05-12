package seker.algorithm.gobang;

import java.util.ArrayList;
import java.util.List;

import seker.algorithm.gobang.chessboard.Cell;
import seker.algorithm.gobang.chessboard.Chess;
import seker.algorithm.gobang.chessboard.ChessBoard;
import seker.algorithm.gobang.player.RobotPlayer;

/**
 * 游戏类
 * 
 * @author seker
 */
public final class Game {
    
    /**
     * 私有构造方法
     */
    private Game() {
        
    }
    
    /**
     * 默认的棋盘size：15*15的棋盘线
     */
    public static final byte SIZE = 15;
    
    /**
     * 五子棋，顾名思义，只要连成了5个就成功
     */
    public static final byte FIVE = 5;
    
    /**
     * AI4的最大长度
     */
    public static final int FOUR = Game.FIVE - 1;
    
    /**
     * 用于链接两个AI4的缓冲：reverse(AI4) + ‘*’ + AI4
     */
    public static final int NINE = FOUR * 2 + 1;
    
    /**
     * 游戏结束：和
     */
    public static final byte GAME_DEUCE = -1;
    
    /**
     * 游戏结束：赢
     */
    public static final byte GAME_WIN = 1;
    
    /**
     * 游戏继续
     */
    public static final byte GAME_PROCEED = 0;
    
    /**
     * @param args  参数列表
     */
    public static void main(String[] args) {
        ChessBoard chessboard = new ChessBoard(SIZE);
        RobotPlayer player1 = new RobotPlayer("player1", Chess.WHITE);
        RobotPlayer player2 = new RobotPlayer("player2", Chess.BLACK);
        
        int state1 = GAME_PROCEED;
        int state2 = GAME_PROCEED;
        List<Cell> cells = new ArrayList<Cell>(FIVE);
        boolean gameover = false;
        while (!gameover) {
            state1 = player1.play(chessboard, cells);
            switch (state1) {
            case GAME_WIN:
                System.out.println("Game Over: " + player1.name + " win.");
                System.out.println(cells);
                gameover = true;
                break;
            case GAME_DEUCE:
                System.out.println(player1.name + " reach deuce.");
                break;
            default:
            case GAME_PROCEED:
                break;
            }
            if (gameover) {
                break;
            } else if (state1 == GAME_DEUCE && state2 == GAME_DEUCE) {
                System.out.println("Game Over: " + player1.name + " & " + player2.name + " reach deuce.");
                break;
            }
            
            state2 = player2.play(chessboard, cells);
            switch (state2) {
            case GAME_WIN:
                System.out.println("Game Over: " + player2.name + " win.");
                System.out.println(cells);
                gameover = true;
                break;
            case GAME_DEUCE:
                System.out.println(player2.name + " reach deuce.");
                break;
            default:
            case GAME_PROCEED:
                break;
            }
            
            if (state1 == GAME_DEUCE && state2 == GAME_DEUCE) {
                System.out.println("Game Over: " + player1.name + " & " + player2.name + " reach deuce.");
                break;
            }
        }
        chessboard.print();
    }
}
