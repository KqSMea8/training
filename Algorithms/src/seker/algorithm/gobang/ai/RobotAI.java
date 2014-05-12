package seker.algorithm.gobang.ai;

import java.util.List;
import java.util.Random;

import seker.algorithm.gobang.Game;
import seker.algorithm.gobang.chessboard.Cell;
import seker.algorithm.gobang.chessboard.Chess;
import seker.algorithm.gobang.chessboard.ChessBoard;
import seker.algorithm.gobang.chessboard.Direction;

/**
 * 五子棋的人工智能的实现
 * 
 * @author seker
 */
public class RobotAI implements AI {
    
    /**
     * 用于组成单个AI4的缓冲
     */
    private StringBuilder buf = new StringBuilder(Game.FOUR);
    
    /**
     * 四个方向上的AI9
     */
    private AI9[] mAI9s = new AI9[Direction.DIRECTIONS.length];
    
    /**
     * 同一个方向上，前进/后退，两个AI4
     */
    private AI4[] mAI4s = new AI4[Direction.STEPS.length];
    
    /**
     * 权重计算器
     */
    private AIWeighter mWeighter = new AIWeighter();
    
    /**
     * RobotPlayer的名字，用于输入log用
     */
    protected final String mName;
    
    /**
     * 对于权值相同的Cell，随机的决定要不要赋值为“最大权值”
     */
    private Random mRandom = new Random(System.currentTimeMillis());
    
    /**
     * 随机赋值为“最大权值”的开关，用于开启和关闭{@link mRandom}
     */
    private boolean mRandomSwitch = true;
    
    /**
     * 构造方法
     * 
     * @param name  RobotPlayer的名字
     */
    public RobotAI(String name) {
        mName = name;
    }
    
    @Override
    public Cell findBestCell(ChessBoard chessboard, byte chess) {
        if (null == chessboard) {
            throw new IllegalArgumentException("RobotAI.findBestCell(null == chessboard)");
        }
        
        final Cell[][] cells = chessboard.getCells();
        if (null == cells) {
            throw new IllegalArgumentException("RobotAI.findBestCell(null == chessboard.getBoard())");
        }
        
        int weights = 0;
        int temp = 0;
        Cell cell = null;
        List<Cell> result = null;
        for (byte i = 0; i < cells.length; i++) {
            for (byte j = 0; j < cells[0].length; j++) {
                if (cells[i][j].isEmpty()) {
                    result = chessboard.down(cells[i][j].x, cells[i][j].y, chess);
                    if (null != result) {
                        cell = cells[i][j];
                        chessboard.up();
                        return cell;
                    }
                    
                    temp = traverse(true, cells, cells[i][j]) + traverse(false, cells, cells[i][j]);
                    if (weights < temp) {
                        weights = temp;
                        cell = cells[i][j];
                    } else if (weights == temp) {
                        if (mRandomSwitch && (0 == Math.abs(mRandom.nextInt()) % 2)) {
                            weights = temp;
                            cell = cells[i][j];
                        }
                    }
                    chessboard.up();
                }
            }
        }
        if (weights < AI9.AI9_1_0.weight) {
            System.out.println(mName + " can't win any more, so try to deuce.");
        }
        return cell;
    }
    
    /**
     * 在单个Cell上，朝4个方向试探
     * 
     * @param attack    true，进攻；false防守
     * @param cells     棋盘上的Cell二位数组
     * @param cell      当前要试探的Cell
     * @return          该Cell的权值（进攻权值或防守权值）
     */
    private int traverse(boolean attack, Cell[][] cells, Cell cell) {
        int x = 0;
        int y = 0;
        int deltaX = 0;
        int deltaY = 0;
        final byte chess = attack ? cell.getChess() : Chess.contrary(cell.getChess());
        for (byte direction : Direction.DIRECTIONS) {                                   // 在某一个方向上着探索
            for (int i = 0, n = Direction.STEPS.length; i < n; i++) {                   // 在某一个方向上"前进/后退"探索
                deltaX = Direction.DELTAS[direction][0] * Direction.STEPS[i];
                deltaY = Direction.DELTAS[direction][1] * Direction.STEPS[i];
                
                x = cell.x + deltaX;
                y = cell.y + deltaY;
                
                buf.delete(0, buf.length());
                for (int index = 0; index < Game.FOUR; index++) {
                    if (x < 0 || x == cells.length || y < 0 || y == cells[0].length) {  // 到达棋盘的边界
                        buf.append(AI4.STOP);
                        break;
                    } else {
                        if (chess == cells[x][y].getChess()) {                          // 己方棋子
                            buf.append(AI4.SELF);
                        }  else if (Chess.EMPTY == cells[x][y].getChess()) {            // 空格
                            buf.append(AI4.EMPTY);
                        } else {                                                        // 对方棋子
                            buf.append(AI4.STOP);
                            break;
                        }
                    }
                    x += deltaX;
                    y += deltaY;
                }
                mAI4s[i] = AI4.vauleToAI4(buf.toString());
            }
            
            System.out.print(String.format("%1$7s(%2$s).%3$s.", mName, attack ? "attack" : "define", cell.position()));
            mAI9s[direction] = mWeighter.convertAI4sToAI9(mAI4s[0], mAI4s[1]);
        }
        return mWeighter.wights(attack, mAI9s[0], mAI9s[1], mAI9s[2], mAI9s[3]); // SUPPRESS CHECKSTYLE
    }
}