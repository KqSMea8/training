package seker.algorithm.gobang.chessboard;

/**
 * 棋子的类型
 * 
 * @author seker
 */
public final class Chess {
    
    /**
     * 辅助类，私有构造方法
     */
    private Chess() {
        
    }

    /** 
     * 棋盘的一个棋盘线交叉点上放置着：白色棋子 
     */
    public static final byte BLACK = -1;

    /** 
     * 棋盘的一个棋盘线交叉点上放置着：空
     */
    public static final byte EMPTY = 0;

    /** 
     * 棋盘的一个棋盘线交叉点上放置着：白色棋子
     */
    public static final byte WHITE = 1;
    
    /**
     * 求对方的棋子
     * 
     * @param chess 棋子类型
     * @return      对手的棋子类型
     */
    public static byte contrary(byte chess) {
        return ((byte) -chess);
    }
}
