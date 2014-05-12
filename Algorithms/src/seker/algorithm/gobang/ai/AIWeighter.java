package seker.algorithm.gobang.ai;

import java.util.HashMap;

import seker.algorithm.gobang.Game;

/**
 * 权重计算器：RobotAI的辅助类，主要用于：
 * 1、AI4组合成AI9
 * 2、计算多个AI9的权重
 * 
 * @author seker
 */
final class AIWeighter {
    
    /**
     * 构造方法，包内可见
     */
    AIWeighter() {
    }
    
    /**
     * 用于链接两个AI4的缓冲
     */
    private StringBuilder buf = new StringBuilder(Game.NINE);
    
    /**
     * 链接两个AI4会组成一个String，此HashMap为String转换成AI9的哈希表
     */
    private static final HashMap<String, AI9> AI9MAP;

    // 遍历规则：'|' -> '_' -> '&'
    static {
        AI9MAP = new HashMap<String, AI9>((AI4.values().length + 1) * AI4.values().length / 2);
        
        AI9MAP.put("|*|",       AI9.AI9_0);
        AI9MAP.put("|*_|",      AI9.AI9_0);
        AI9MAP.put("|*&|",      AI9.AI9_0);
        AI9MAP.put("|*__|",     AI9.AI9_0);
        AI9MAP.put("|*&_|",     AI9.AI9_0);
        AI9MAP.put("|*_&|",     AI9.AI9_0);
        AI9MAP.put("|*&&|",     AI9.AI9_0);
        AI9MAP.put("|*___|",    AI9.AI9_0);
        AI9MAP.put("|*&__|",    AI9.AI9_0);
        AI9MAP.put("|*_&_|",    AI9.AI9_0);
        AI9MAP.put("|*&&_|",    AI9.AI9_0);
        AI9MAP.put("|*__&|",    AI9.AI9_0);
        AI9MAP.put("|*&_&|",    AI9.AI9_0);
        AI9MAP.put("|*_&&|",    AI9.AI9_0);
        AI9MAP.put("|*&&&|",    AI9.AI9_0);
        AI9MAP.put("|*____",    AI9.AI9_1_0);
        AI9MAP.put("|*&___",    AI9.AI9_2_0);
        AI9MAP.put("|*_&__",    AI9.AI9_2_0);
        AI9MAP.put("|*&&__",    AI9.AI9_3_0);
        AI9MAP.put("|*__&_",    AI9.AI9_2_0);
        AI9MAP.put("|*&_&_",    AI9.AI9_3_0);
        AI9MAP.put("|*_&&_",    AI9.AI9_3_0);
        AI9MAP.put("|*&&&_",    AI9.AI9_4_0);
        AI9MAP.put("|*___&",    AI9.AI9_2_1);
        AI9MAP.put("|*&__&",    AI9.AI9_3_1);
        AI9MAP.put("|*_&_&",    AI9.AI9_3_1);
        AI9MAP.put("|*&&_&",    AI9.AI9_4_1);
        AI9MAP.put("|*__&&",    AI9.AI9_3_2);
        AI9MAP.put("|*&_&&",    AI9.AI9_4_2);
        AI9MAP.put("|*_&&&",    AI9.AI9_4_3);
        AI9MAP.put("|*&&&&",    AI9.AI9_5);
        
        AI9MAP.put("|_*_|",     AI9.AI9_0);
        AI9MAP.put("|_*&|",     AI9.AI9_0);
        AI9MAP.put("|_*__|",    AI9.AI9_0);
        AI9MAP.put("|_*&_|",    AI9.AI9_0);
        AI9MAP.put("|_*_&|",    AI9.AI9_0);
        AI9MAP.put("|_*&&|",    AI9.AI9_0);
        AI9MAP.put("|_*___|",   AI9.AI9_1_0);
        AI9MAP.put("|_*&__|",   AI9.AI9_2_0);
        AI9MAP.put("|_*_&_|",   AI9.AI9_2_0);
        AI9MAP.put("|_*&&_|",   AI9.AI9_3_0);
        AI9MAP.put("|_*__&|",   AI9.AI9_2_0);
        AI9MAP.put("|_*&_&|",   AI9.AI9_3_0);
        AI9MAP.put("|_*_&&|",   AI9.AI9_3_0);
        AI9MAP.put("|_*&&&|",   AI9.AI9_4_0);
        AI9MAP.put("|_*____",   AI9.AI9_1_0);
        AI9MAP.put("|_*&___",   AI9.AI9_2_0);
        AI9MAP.put("|_*_&__",   AI9.AI9_2_0);
        AI9MAP.put("|_*&&__",   AI9.AI9_3_0);
        AI9MAP.put("|_*__&_",   AI9.AI9_2_1);
        AI9MAP.put("|_*&_&_",   AI9.AI9_3_1);
        AI9MAP.put("|_*_&&_",   AI9.AI9_3_2);
        AI9MAP.put("|_*&&&_",   AI9.AI9_4_4);
        AI9MAP.put("|_*___&",   AI9.AI9_2_1);
        AI9MAP.put("|_*&__&",   AI9.AI9_3_1);
        AI9MAP.put("|_*_&_&",   AI9.AI9_3_1);
        AI9MAP.put("|_*&&_&",   AI9.AI9_4_1);
        AI9MAP.put("|_*__&&",   AI9.AI9_3_2);
        AI9MAP.put("|_*&_&&",   AI9.AI9_4_2);
        AI9MAP.put("|_*_&&&",   AI9.AI9_4_3);
        AI9MAP.put("|_*&&&&",   AI9.AI9_5);
        
        AI9MAP.put("|&*&|",     AI9.AI9_0);
        AI9MAP.put("|&*__|",    AI9.AI9_0);
        AI9MAP.put("|&*&_|",    AI9.AI9_0);
        AI9MAP.put("|&*_&|",    AI9.AI9_0);
        AI9MAP.put("|&*&&|",    AI9.AI9_0);
        AI9MAP.put("|&*___|",   AI9.AI9_2_0);
        AI9MAP.put("|&*&__|",   AI9.AI9_3_0);
        AI9MAP.put("|&*_&_|",   AI9.AI9_3_0);
        AI9MAP.put("|&*&&_|",   AI9.AI9_4_0);
        AI9MAP.put("|&*__&|",   AI9.AI9_3_0);
        AI9MAP.put("|&*&_&|",   AI9.AI9_4_0);
        AI9MAP.put("|&*_&&|",   AI9.AI9_4_0);
        AI9MAP.put("|&*&&&|",   AI9.AI9_5);
        AI9MAP.put("|&*____",   AI9.AI9_2_0);
        AI9MAP.put("|&*&___",   AI9.AI9_3_0);
        AI9MAP.put("|&*_&__",   AI9.AI9_3_0);
        AI9MAP.put("|&*&&__",   AI9.AI9_4_0);
        AI9MAP.put("|&*__&_",   AI9.AI9_3_1);
        AI9MAP.put("|&*&_&_",   AI9.AI9_4_1);
        AI9MAP.put("|&*_&&_",   AI9.AI9_4_2);
        AI9MAP.put("|&*&&&_",   AI9.AI9_5);
        AI9MAP.put("|&*___&",   AI9.AI9_2_1);
        AI9MAP.put("|&*&__&",   AI9.AI9_3_1);
        AI9MAP.put("|&*_&_&",   AI9.AI9_3_1);
        AI9MAP.put("|&*&&_&",   AI9.AI9_4_1);
        AI9MAP.put("|&*__&&",   AI9.AI9_3_2);
        AI9MAP.put("|&*&_&&",   AI9.AI9_4_2);
        AI9MAP.put("|&*_&&&",   AI9.AI9_4_3);
        AI9MAP.put("|&*&&&&",   AI9.AI9_5);
        
        AI9MAP.put("|__*__|",   AI9.AI9_1_0);
        AI9MAP.put("|__*&_|",   AI9.AI9_2_0);
        AI9MAP.put("|__*_&|",   AI9.AI9_2_0);
        AI9MAP.put("|__*&&|",   AI9.AI9_3_0);
        AI9MAP.put("|__*___|",  AI9.AI9_1_0);
        AI9MAP.put("|__*&__|",  AI9.AI9_2_0);
        AI9MAP.put("|__*_&_|",  AI9.AI9_2_0);
        AI9MAP.put("|__*&&_|",  AI9.AI9_3_0);
        AI9MAP.put("|__*__&|",  AI9.AI9_2_0);
        AI9MAP.put("|__*&_&|",  AI9.AI9_3_0);
        AI9MAP.put("|__*_&&|",  AI9.AI9_3_0);
        AI9MAP.put("|__*&&&|",  AI9.AI9_4_0);
        AI9MAP.put("|__*____",  AI9.AI9_1_0);
        AI9MAP.put("|__*&___",  AI9.AI9_2_0);
        AI9MAP.put("|__*_&__",  AI9.AI9_2_1);
        AI9MAP.put("|__*&&__",  AI9.AI9_3_3);
        AI9MAP.put("|__*__&_",  AI9.AI9_2_1);
        AI9MAP.put("|__*&_&_",  AI9.AI9_3_1);
        AI9MAP.put("|__*_&&_",  AI9.AI9_3_2);
        AI9MAP.put("|__*&&&_",  AI9.AI9_4_4);
        AI9MAP.put("|__*___&",  AI9.AI9_2_1);
        AI9MAP.put("|__*&__&",  AI9.AI9_3_1);
        AI9MAP.put("|__*_&_&",  AI9.AI9_3_1);
        AI9MAP.put("|__*&&_&",  AI9.AI9_4_3);
        AI9MAP.put("|__*__&&",  AI9.AI9_3_2);
        AI9MAP.put("|__*&_&&",  AI9.AI9_4_2);
        AI9MAP.put("|__*_&&&",  AI9.AI9_4_3);
        AI9MAP.put("|__*&&&&",  AI9.AI9_5);
        
        AI9MAP.put("|_&*&_|",   AI9.AI9_3_0);
        AI9MAP.put("|_&*_&|",   AI9.AI9_3_0);
        AI9MAP.put("|_&*&&|",   AI9.AI9_4_0);
        AI9MAP.put("|_&*___|",  AI9.AI9_2_0);
        AI9MAP.put("|_&*&__|",  AI9.AI9_3_0);
        AI9MAP.put("|_&*_&_|",  AI9.AI9_3_0);
        AI9MAP.put("|_&*&&_|",  AI9.AI9_4_4);
        AI9MAP.put("|_&*__&|",  AI9.AI9_3_0);
        AI9MAP.put("|_&*&_&|",  AI9.AI9_4_0);
        AI9MAP.put("|_&*_&&|",  AI9.AI9_4_0);
        AI9MAP.put("|_&*&&&|",  AI9.AI9_5);
        AI9MAP.put("|_&*____",  AI9.AI9_2_0);
        AI9MAP.put("|_&*&___",  AI9.AI9_3_0);
        AI9MAP.put("|_&*_&__",  AI9.AI9_3_0);
        AI9MAP.put("|_&*&&__",  AI9.AI9_4_4);
        AI9MAP.put("|_&*__&_",  AI9.AI9_3_1);
        AI9MAP.put("|_&*&_&_",  AI9.AI9_4_1);
        AI9MAP.put("|_&*_&&_",  AI9.AI9_4_2);
        AI9MAP.put("|_&*&&&_",  AI9.AI9_4_4);
        AI9MAP.put("|_&*___&",  AI9.AI9_2_1);
        AI9MAP.put("|_&*&__&",  AI9.AI9_3_1);
        AI9MAP.put("|_&*_&_&",  AI9.AI9_3_2);
        AI9MAP.put("|_&*&&_&",  AI9.AI9_4_4);
        AI9MAP.put("|_&*__&&",  AI9.AI9_3_2);
        AI9MAP.put("|_&*&_&&",  AI9.AI9_4_2);
        AI9MAP.put("|_&*_&&&",  AI9.AI9_4_3);
        AI9MAP.put("|_&*&&&&",  AI9.AI9_5);
        
        AI9MAP.put("|&_*_&|",   AI9.AI9_3_0);
        AI9MAP.put("|&_*&&|",   AI9.AI9_4_0);
        AI9MAP.put("|&_*___|",  AI9.AI9_2_0);
        AI9MAP.put("|&_*&__|",  AI9.AI9_3_0);
        AI9MAP.put("|&_*_&_|",  AI9.AI9_3_0);
        AI9MAP.put("|&_*&&_|",  AI9.AI9_4_0);
        AI9MAP.put("|&_*__&|",  AI9.AI9_2_0);
        AI9MAP.put("|&_*&_&|",  AI9.AI9_3_0);
        AI9MAP.put("|&_*_&&|",  AI9.AI9_3_0);
        AI9MAP.put("|&_*&&&|",  AI9.AI9_4_0);
        AI9MAP.put("|&_*____",  AI9.AI9_2_0);
        AI9MAP.put("|&_*&___",  AI9.AI9_3_0);
        AI9MAP.put("|&_*_&__",  AI9.AI9_3_1);
        AI9MAP.put("|&_*&&__",  AI9.AI9_4_3);
        AI9MAP.put("|&_*__&_",  AI9.AI9_2_1);
        AI9MAP.put("|&_*&_&_",  AI9.AI9_3_1);
        AI9MAP.put("|&_*_&&_",  AI9.AI9_3_2);
        AI9MAP.put("|&_*&&&_",  AI9.AI9_4_4);
        AI9MAP.put("|&_*___&",  AI9.AI9_2_1);
        AI9MAP.put("|&_*&__&",  AI9.AI9_3_1);
        AI9MAP.put("|&_*_&_&",  AI9.AI9_3_2);
        AI9MAP.put("|&_*&&_&",  AI9.AI9_4_4);
        AI9MAP.put("|&_*__&&",  AI9.AI9_3_2);
        AI9MAP.put("|&_*&_&&",  AI9.AI9_4_2);
        AI9MAP.put("|&_*_&&&",  AI9.AI9_4_3);
        AI9MAP.put("|&_*&&&&",  AI9.AI9_5);
        
        AI9MAP.put("|&&*&&|",   AI9.AI9_5);
        AI9MAP.put("|&&*___|",  AI9.AI9_3_0);
        AI9MAP.put("|&&*&__|",  AI9.AI9_4_0);
        AI9MAP.put("|&&*_&_|",  AI9.AI9_4_0);
        AI9MAP.put("|&&*&&_|",  AI9.AI9_5);
        AI9MAP.put("|&&*__&|",  AI9.AI9_3_0);
        AI9MAP.put("|&&*&_&|",  AI9.AI9_4_0);
        AI9MAP.put("|&&*_&&|",  AI9.AI9_4_0);
        AI9MAP.put("|&&*&&&|",  AI9.AI9_5);
        AI9MAP.put("|&&*____",  AI9.AI9_3_0);
        AI9MAP.put("|&&*&___",  AI9.AI9_4_0);
        AI9MAP.put("|&&*_&__",  AI9.AI9_4_1);
        AI9MAP.put("|&&*&&__",  AI9.AI9_5);
        AI9MAP.put("|&&*__&_",  AI9.AI9_3_1);
        AI9MAP.put("|&&*&_&_",  AI9.AI9_4_1);
        AI9MAP.put("|&&*_&&_",  AI9.AI9_4_2);
        AI9MAP.put("|&&*&&&_",  AI9.AI9_5);
        AI9MAP.put("|&&*___&",  AI9.AI9_3_1);
        AI9MAP.put("|&&*&__&",  AI9.AI9_4_1);
        AI9MAP.put("|&&*_&_&",  AI9.AI9_4_2);
        AI9MAP.put("|&&*&&_&",  AI9.AI9_5);
        AI9MAP.put("|&&*__&&",  AI9.AI9_3_2);
        AI9MAP.put("|&&*&_&&",  AI9.AI9_4_2);
        AI9MAP.put("|&&*_&&&",  AI9.AI9_4_3);
        AI9MAP.put("|&&*&&&&",  AI9.AI9_5);
        
        AI9MAP.put("|___*___|", AI9.AI9_1_0);
        AI9MAP.put("|___*&__|", AI9.AI9_2_0);
        AI9MAP.put("|___*_&_|", AI9.AI9_2_0);
        AI9MAP.put("|___*&&_|", AI9.AI9_3_0);
        AI9MAP.put("|___*__&|", AI9.AI9_2_0);
        AI9MAP.put("|___*&_&|", AI9.AI9_3_0);
        AI9MAP.put("|___*_&&|", AI9.AI9_3_0);
        AI9MAP.put("|___*&&&|", AI9.AI9_4_0);
        AI9MAP.put("|___*____", AI9.AI9_1_0);
        AI9MAP.put("|___*&___", AI9.AI9_2_2);
        AI9MAP.put("|___*_&__", AI9.AI9_2_1);
        AI9MAP.put("|___*&&__", AI9.AI9_3_3);
        AI9MAP.put("|___*__&_", AI9.AI9_2_1);
        AI9MAP.put("|___*&_&_", AI9.AI9_3_2);
        AI9MAP.put("|___*_&&_", AI9.AI9_3_2);
        AI9MAP.put("|___*&&&_", AI9.AI9_4_4);
        AI9MAP.put("|___*___&", AI9.AI9_2_1);
        AI9MAP.put("|___*&__&", AI9.AI9_3_2);
        AI9MAP.put("|___*_&_&", AI9.AI9_3_2);
        AI9MAP.put("|___*&&_&", AI9.AI9_4_3);
        AI9MAP.put("|___*__&&", AI9.AI9_3_2);
        AI9MAP.put("|___*&_&&", AI9.AI9_4_2);
        AI9MAP.put("|___*_&&&", AI9.AI9_4_3);
        AI9MAP.put("|___*&&&&", AI9.AI9_5);
        
        AI9MAP.put("|__&*&__|", AI9.AI9_3_3);
        AI9MAP.put("|__&*_&_|", AI9.AI9_3_0);
        AI9MAP.put("|__&*&&_|", AI9.AI9_4_4);
        AI9MAP.put("|__&*__&|", AI9.AI9_3_0);
        AI9MAP.put("|__&*&_&|", AI9.AI9_4_3);
        AI9MAP.put("|__&*_&&|", AI9.AI9_4_0);
        AI9MAP.put("|__&*&&&|", AI9.AI9_5);
        AI9MAP.put("|__&*____", AI9.AI9_2_0);
        AI9MAP.put("|__&*&___", AI9.AI9_3_3);
        AI9MAP.put("|__&*_&__", AI9.AI9_4_0);
        AI9MAP.put("|__&*&&__", AI9.AI9_4_4);
        AI9MAP.put("|__&*__&_", AI9.AI9_3_1);
        AI9MAP.put("|__&*&_&_", AI9.AI9_4_3);
        AI9MAP.put("|__&*_&&_", AI9.AI9_4_2);
        AI9MAP.put("|__&*&&&_", AI9.AI9_5);
        AI9MAP.put("|__&*___&", AI9.AI9_2_1);
        AI9MAP.put("|__&*&__&", AI9.AI9_3_3);
        AI9MAP.put("|__&*_&_&", AI9.AI9_3_2);
        AI9MAP.put("|__&*&&_&", AI9.AI9_4_4);
        AI9MAP.put("|__&*__&&", AI9.AI9_3_2);
        AI9MAP.put("|__&*&_&&", AI9.AI9_4_3);
        AI9MAP.put("|__&*_&&&", AI9.AI9_4_3);
        AI9MAP.put("|__&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("|_&_*_&_|", AI9.AI9_3_0);
        AI9MAP.put("|_&_*&&_|", AI9.AI9_4_0);
        AI9MAP.put("|_&_*__&|", AI9.AI9_2_0);
        AI9MAP.put("|_&_*&_&|", AI9.AI9_3_0);
        AI9MAP.put("|_&_*_&&|", AI9.AI9_3_0);
        AI9MAP.put("|_&_*&&&|", AI9.AI9_4_0);
        AI9MAP.put("|_&_*____", AI9.AI9_2_0);
        AI9MAP.put("|_&_*&___", AI9.AI9_3_2);
        AI9MAP.put("|_&_*_&__", AI9.AI9_3_1);
        AI9MAP.put("|_&_*&&__", AI9.AI9_4_3);
        AI9MAP.put("|_&_*__&_", AI9.AI9_2_1);
        AI9MAP.put("|_&_*&_&_", AI9.AI9_3_3);
        AI9MAP.put("|_&_*_&&_", AI9.AI9_3_2);
        AI9MAP.put("|_&_*&&&_", AI9.AI9_4_4);
        AI9MAP.put("|_&_*___&", AI9.AI9_2_1);
        AI9MAP.put("|_&_*&__&", AI9.AI9_3_3);
        AI9MAP.put("|_&_*_&_&", AI9.AI9_3_2);
        AI9MAP.put("|_&_*&&_&", AI9.AI9_4_4);
        AI9MAP.put("|_&_*__&&", AI9.AI9_3_2);
        AI9MAP.put("|_&_*&_&&", AI9.AI9_4_3);
        AI9MAP.put("|_&_*_&&&", AI9.AI9_4_3);
        AI9MAP.put("|_&_*&&&&", AI9.AI9_5);
        
        AI9MAP.put("|_&&*&&_|", AI9.AI9_5);
        AI9MAP.put("|_&&*__&|", AI9.AI9_3_1);
        AI9MAP.put("|_&&*&_&|", AI9.AI9_4_4);
        AI9MAP.put("|_&&*_&&|", AI9.AI9_4_0);
        AI9MAP.put("|_&&*&&&|", AI9.AI9_5);
        AI9MAP.put("|_&&*____", AI9.AI9_3_0);
        AI9MAP.put("|_&&*&___", AI9.AI9_4_4);
        AI9MAP.put("|_&&*_&__", AI9.AI9_4_1);
        AI9MAP.put("|_&&*&&__", AI9.AI9_5);
        AI9MAP.put("|_&&*__&_", AI9.AI9_3_1);
        AI9MAP.put("|_&&*&_&_", AI9.AI9_4_4);
        AI9MAP.put("|_&&*_&&_", AI9.AI9_4_2);
        AI9MAP.put("|_&&*&&&_", AI9.AI9_5);
        AI9MAP.put("|_&&*___&", AI9.AI9_3_1);
        AI9MAP.put("|_&&*&__&", AI9.AI9_4_4);
        AI9MAP.put("|_&&*_&_&", AI9.AI9_4_2);
        AI9MAP.put("|_&&*&&_&", AI9.AI9_5);
        AI9MAP.put("|_&&*__&&", AI9.AI9_3_2);
        AI9MAP.put("|_&&*&_&&", AI9.AI9_4_4);
        AI9MAP.put("|_&&*_&&&", AI9.AI9_4_3);
        AI9MAP.put("|_&&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("|&__*__&|", AI9.AI9_2_0);
        AI9MAP.put("|&__*&_&|", AI9.AI9_3_0);
        AI9MAP.put("|&__*_&&|", AI9.AI9_3_0);
        AI9MAP.put("|&__*&&&|", AI9.AI9_4_0);
        AI9MAP.put("|&__*____", AI9.AI9_2_0);
        AI9MAP.put("|&__*&___", AI9.AI9_2_2);
        AI9MAP.put("|&__*_&__", AI9.AI9_2_0);
        AI9MAP.put("|&__*&&__", AI9.AI9_3_3);
        AI9MAP.put("|&__*__&_", AI9.AI9_2_1);
        AI9MAP.put("|&__*&_&_", AI9.AI9_3_3);
        AI9MAP.put("|&__*_&&_", AI9.AI9_3_2);
        AI9MAP.put("|&__*&&&_", AI9.AI9_4_4);
        AI9MAP.put("|&__*___&", AI9.AI9_2_1);
        AI9MAP.put("|&__*&__&", AI9.AI9_3_3);
        AI9MAP.put("|&__*_&_&", AI9.AI9_3_2);
        AI9MAP.put("|&__*&&_&", AI9.AI9_4_3);
        AI9MAP.put("|&__*__&&", AI9.AI9_3_2);
        AI9MAP.put("|&__*&_&&", AI9.AI9_4_3);
        AI9MAP.put("|&__*_&&&", AI9.AI9_4_3);
        AI9MAP.put("|&__*&&&&", AI9.AI9_5);
        
        AI9MAP.put("|&_&*&_&|", AI9.AI9_4_4);
        AI9MAP.put("|&_&*_&&|", AI9.AI9_4_0);
        AI9MAP.put("|&_&*&&&|", AI9.AI9_5);
        AI9MAP.put("|&_&*____", AI9.AI9_3_0);
        AI9MAP.put("|&_&*&___", AI9.AI9_4_3);
        AI9MAP.put("|&_&*_&__", AI9.AI9_3_1);
        AI9MAP.put("|&_&*&&__", AI9.AI9_4_4);
        AI9MAP.put("|&_&*__&_", AI9.AI9_3_1);
        AI9MAP.put("|&_&*&_&_", AI9.AI9_4_4);
        AI9MAP.put("|&_&*_&&_", AI9.AI9_4_2);
        AI9MAP.put("|&_&*&&&_", AI9.AI9_5);
        AI9MAP.put("|&_&*___&", AI9.AI9_3_1);
        AI9MAP.put("|&_&*&__&", AI9.AI9_3_3);
        AI9MAP.put("|&_&*_&_&", AI9.AI9_3_2);
        AI9MAP.put("|&_&*&&_&", AI9.AI9_4_4);
        AI9MAP.put("|&_&*__&&", AI9.AI9_3_2);
        AI9MAP.put("|&_&*&_&&", AI9.AI9_4_4);
        AI9MAP.put("|&_&*_&&&", AI9.AI9_4_3);
        AI9MAP.put("|&_&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("|&&_*_&&|", AI9.AI9_3_0);
        AI9MAP.put("|&&_*&&&|", AI9.AI9_4_0);
        AI9MAP.put("|&&_*____", AI9.AI9_3_0);
        AI9MAP.put("|&&_*&___", AI9.AI9_4_2);
        AI9MAP.put("|&&_*_&__", AI9.AI9_3_1);
        AI9MAP.put("|&&_*&&__", AI9.AI9_4_3);
        AI9MAP.put("|&&_*__&_", AI9.AI9_3_1);
        AI9MAP.put("|&&_*&_&_", AI9.AI9_4_3);
        AI9MAP.put("|&&_*_&&_", AI9.AI9_3_2);
        AI9MAP.put("|&&_*&&&_", AI9.AI9_4_4);
        AI9MAP.put("|&&_*___&", AI9.AI9_3_1);
        AI9MAP.put("|&&_*&__&", AI9.AI9_4_3);
        AI9MAP.put("|&&_*_&_&", AI9.AI9_3_2);
        AI9MAP.put("|&&_*&&_&", AI9.AI9_4_4);
        AI9MAP.put("|&&_*__&&", AI9.AI9_3_2);
        AI9MAP.put("|&&_*&_&&", AI9.AI9_4_4);
        AI9MAP.put("|&&_*_&&&", AI9.AI9_4_3);
        AI9MAP.put("|&&_*&&&&", AI9.AI9_5);
        
        AI9MAP.put("|&&&*&&&|", AI9.AI9_5);
        AI9MAP.put("|&&&*____", AI9.AI9_4_0);
        AI9MAP.put("|&&&*&___", AI9.AI9_5);
        AI9MAP.put("|&&&*_&__", AI9.AI9_4_1);
        AI9MAP.put("|&&&*&&__", AI9.AI9_5);
        AI9MAP.put("|&&&*__&_", AI9.AI9_4_1);
        AI9MAP.put("|&&&*&_&_", AI9.AI9_5);
        AI9MAP.put("|&&&*_&&_", AI9.AI9_4_2);
        AI9MAP.put("|&&&*&&&_", AI9.AI9_5);
        AI9MAP.put("|&&&*___&", AI9.AI9_4_1);
        AI9MAP.put("|&&&*&__&", AI9.AI9_5);
        AI9MAP.put("|&&&*_&_&", AI9.AI9_4_2);
        AI9MAP.put("|&&&*&&_&", AI9.AI9_5);
        AI9MAP.put("|&&&*__&&", AI9.AI9_4_2);
        AI9MAP.put("|&&&*&_&&", AI9.AI9_5);
        AI9MAP.put("|&&&*_&&&", AI9.AI9_4_3);
        AI9MAP.put("|&&&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("____*____", AI9.AI9_1_1);
        AI9MAP.put("____*&___", AI9.AI9_2_2);
        AI9MAP.put("____*_&__", AI9.AI9_2_1);
        AI9MAP.put("____*&&__", AI9.AI9_3_3);
        AI9MAP.put("____*__&_", AI9.AI9_2_1);
        AI9MAP.put("____*&_&_", AI9.AI9_3_2);
        AI9MAP.put("____*_&&_", AI9.AI9_3_2);
        AI9MAP.put("____*&&&_", AI9.AI9_4_4);
        AI9MAP.put("____*___&", AI9.AI9_2_1);
        AI9MAP.put("____*&__&", AI9.AI9_3_2);
        AI9MAP.put("____*_&_&", AI9.AI9_3_2);
        AI9MAP.put("____*&&_&", AI9.AI9_4_3);
        AI9MAP.put("____*__&&", AI9.AI9_3_2);
        AI9MAP.put("____*&_&&", AI9.AI9_4_2);
        AI9MAP.put("____*_&&&", AI9.AI9_4_3);
        AI9MAP.put("____*&&&&", AI9.AI9_5);
        
        AI9MAP.put("___&*&___", AI9.AI9_3_3);
        AI9MAP.put("___&*_&__", AI9.AI9_3_2);
        AI9MAP.put("___&*&&__", AI9.AI9_4_4);
        AI9MAP.put("___&*__&_", AI9.AI9_3_2);
        AI9MAP.put("___&*&_&_", AI9.AI9_4_3);
        AI9MAP.put("___&*_&&_", AI9.AI9_4_2);
        AI9MAP.put("___&*&&&_", AI9.AI9_5);
        AI9MAP.put("___&*___&", AI9.AI9_2_2);
        AI9MAP.put("___&*&__&", AI9.AI9_3_3);
        AI9MAP.put("___&*_&_&", AI9.AI9_3_2);
        AI9MAP.put("___&*&&_&", AI9.AI9_4_4);
        AI9MAP.put("___&*__&&", AI9.AI9_3_2);
        AI9MAP.put("___&*&_&&", AI9.AI9_4_3);
        AI9MAP.put("___&*_&&&", AI9.AI9_4_3);
        AI9MAP.put("___&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("__&_*_&__", AI9.AI9_3_2);
        AI9MAP.put("__&_*&&__", AI9.AI9_4_3);
        AI9MAP.put("__&_*__&_", AI9.AI9_2_2);
        AI9MAP.put("__&_*&_&_", AI9.AI9_3_3);
        AI9MAP.put("__&_*_&&_", AI9.AI9_3_2);
        AI9MAP.put("__&_*&&&_", AI9.AI9_4_4);
        AI9MAP.put("__&_*___&", AI9.AI9_2_2);
        AI9MAP.put("__&_*&__&", AI9.AI9_3_3);
        AI9MAP.put("__&_*_&_&", AI9.AI9_3_2);
        AI9MAP.put("__&_*&&_&", AI9.AI9_4_4);
        AI9MAP.put("__&_*__&&", AI9.AI9_2_2);
        AI9MAP.put("__&_*&_&&", AI9.AI9_4_3);
        AI9MAP.put("__&_*_&&&", AI9.AI9_4_3);
        AI9MAP.put("__&_*&&&&", AI9.AI9_5);
        
        AI9MAP.put("__&&*&&__", AI9.AI9_5);
        AI9MAP.put("__&&*__&_", AI9.AI9_3_3);
        AI9MAP.put("__&&*&_&_", AI9.AI9_4_4);
        AI9MAP.put("__&&*_&&_", AI9.AI9_4_3);
        AI9MAP.put("__&&*&&&_", AI9.AI9_5);
        AI9MAP.put("__&&*___&", AI9.AI9_3_3);
        AI9MAP.put("__&&*&__&", AI9.AI9_4_4);
        AI9MAP.put("__&&*_&_&", AI9.AI9_4_3);
        AI9MAP.put("__&&*&&_&", AI9.AI9_5);
        AI9MAP.put("__&&*__&&", AI9.AI9_3_3);
        AI9MAP.put("__&&*&_&&", AI9.AI9_4_4);
        AI9MAP.put("__&&*_&&&", AI9.AI9_4_3);
        AI9MAP.put("__&&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("_&__*__&_", AI9.AI9_2_2);
        AI9MAP.put("_&__*&_&_", AI9.AI9_3_2);
        AI9MAP.put("_&__*_&&_", AI9.AI9_3_2);
        AI9MAP.put("_&__*&&&_", AI9.AI9_4_4);
        AI9MAP.put("_&__*___&", AI9.AI9_2_2);
        AI9MAP.put("_&__*&__&", AI9.AI9_3_3);
        AI9MAP.put("_&__*_&_&", AI9.AI9_3_2);
        AI9MAP.put("_&__*&&_&", AI9.AI9_4_3);
        AI9MAP.put("_&__*__&&", AI9.AI9_3_2);
        AI9MAP.put("_&__*&_&&", AI9.AI9_4_3);
        AI9MAP.put("_&__*_&&&", AI9.AI9_4_3);
        AI9MAP.put("_&__*&&&&", AI9.AI9_5);
        
        AI9MAP.put("_&_&*&_&_", AI9.AI9_4_4);
        AI9MAP.put("_&_&*_&&_", AI9.AI9_4_3);
        AI9MAP.put("_&_&*&&&_", AI9.AI9_5);
        AI9MAP.put("_&_&*___&", AI9.AI9_3_2);
        AI9MAP.put("_&_&*&__&", AI9.AI9_4_3);
        AI9MAP.put("_&_&*_&_&", AI9.AI9_3_3);
        AI9MAP.put("_&_&*&&_&", AI9.AI9_4_4);
        AI9MAP.put("_&_&*__&&", AI9.AI9_3_3);
        AI9MAP.put("_&_&*&_&&", AI9.AI9_4_4);
        AI9MAP.put("_&_&*_&&&", AI9.AI9_4_3);
        AI9MAP.put("_&_&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("_&&_*_&&_", AI9.AI9_3_3);
        AI9MAP.put("_&&_*&&&_", AI9.AI9_4_4);
        AI9MAP.put("_&&_*___&", AI9.AI9_3_2);
        AI9MAP.put("_&&_*&__&", AI9.AI9_4_3);
        AI9MAP.put("_&&_*_&_&", AI9.AI9_3_3);
        AI9MAP.put("_&&_*&&_&", AI9.AI9_4_4);
        AI9MAP.put("_&&_*__&&", AI9.AI9_3_3);
        AI9MAP.put("_&&_*&_&&", AI9.AI9_4_4);
        AI9MAP.put("_&&_*_&&&", AI9.AI9_4_3);
        AI9MAP.put("_&&_*&&&&", AI9.AI9_5);
        
        AI9MAP.put("_&&&*&&&_", AI9.AI9_5);
        AI9MAP.put("_&&&*___&", AI9.AI9_4_4);
        AI9MAP.put("_&&&*&__&", AI9.AI9_5);
        AI9MAP.put("_&&&*_&_&", AI9.AI9_4_4);
        AI9MAP.put("_&&&*&&_&", AI9.AI9_5);
        AI9MAP.put("_&&&*__&&", AI9.AI9_4_4);
        AI9MAP.put("_&&&*&_&&", AI9.AI9_5);
        AI9MAP.put("_&&&*_&&&", AI9.AI9_4_4);
        AI9MAP.put("_&&&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("&___*___&", AI9.AI9_2_2);
        AI9MAP.put("&___*&__&", AI9.AI9_3_2);
        AI9MAP.put("&___*_&_&", AI9.AI9_3_2);
        AI9MAP.put("&___*&&_&", AI9.AI9_4_3);
        AI9MAP.put("&___*__&&", AI9.AI9_3_2);
        AI9MAP.put("&___*&_&&", AI9.AI9_4_2);
        AI9MAP.put("&___*_&&&", AI9.AI9_4_3);
        AI9MAP.put("&___*&&&&", AI9.AI9_5);
        
        AI9MAP.put("&__&*&__&", AI9.AI9_3_3);
        AI9MAP.put("&__&*_&_&", AI9.AI9_3_3);
        AI9MAP.put("&__&*&&_&", AI9.AI9_4_4);
        AI9MAP.put("&__&*__&&", AI9.AI9_3_3);
        AI9MAP.put("&__&*&_&&", AI9.AI9_4_3);
        AI9MAP.put("&__&*_&&&", AI9.AI9_4_3);
        AI9MAP.put("&__&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("&_&_*_&_&", AI9.AI9_3_3);
        AI9MAP.put("&_&_*&&_&", AI9.AI9_4_4);
        AI9MAP.put("&_&_*__&&", AI9.AI9_3_3);
        AI9MAP.put("&_&_*&_&&", AI9.AI9_4_3);
        AI9MAP.put("&_&_*_&&&", AI9.AI9_4_3);
        AI9MAP.put("&_&_*&&&&", AI9.AI9_5);
        
        AI9MAP.put("&_&&*&&_&", AI9.AI9_5);
        AI9MAP.put("&_&&*__&&", AI9.AI9_4_3);
        AI9MAP.put("&_&&*&_&&", AI9.AI9_4_4);
        AI9MAP.put("&_&&*_&&&", AI9.AI9_4_4);
        AI9MAP.put("&_&&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("&&__*__&&", AI9.AI9_3_3);
        AI9MAP.put("&&__*&_&&", AI9.AI9_4_3);
        AI9MAP.put("&&__*_&&&", AI9.AI9_4_3);
        AI9MAP.put("&&__*&&&&", AI9.AI9_5);
        
        AI9MAP.put("&&_&*&_&&", AI9.AI9_4_4);
        AI9MAP.put("&&_&*_&&&", AI9.AI9_4_4);
        AI9MAP.put("&&_&*&&&&", AI9.AI9_5);
        
        AI9MAP.put("&&&_*_&&&", AI9.AI9_4_4);
        AI9MAP.put("&&&_*&&&&", AI9.AI9_5);
        
        AI9MAP.put("&&&&*&&&&", AI9.AI9_5);
    }
    
    /**
     * 两个AI4合成一个AI9
     * 
     * @param forward   向前的AI4
     * @param backward  向后的AI4
     * @return  合成的AI9
     */
    AI9 convertAI4sToAI9(AI4 forward, AI4 backward) {
        buf.delete(0, buf.length());
        
        buf.append(backward.value).reverse();
        buf.append(AI4.CURR);
        buf.append(forward.value);
        String strAI9 = buf.toString();
        
        AI9 ai9 = AI9MAP.get(strAI9);
        if (null == ai9) {
            strAI9 = buf.reverse().toString();
            ai9 = AI9MAP.get(strAI9);
            if (null == ai9) {
                throw new RuntimeException("sAI9s.get(" + strAI9 + ") == null");
            }
            System.out.println(String.format("ai9=(%1$-9s, %2$-7s, reverse)", strAI9, ai9));
        } else {
            System.out.println(String.format("ai9=(%1$-9s, %2$-7s, )", strAI9, ai9));
        }
        return ai9;
    }
    
    /**
     * 4个AI9，组合在一起，进行权重评估
     * 
     * @param attack    true:攻击, false:防守
     * @param ai91      从左到右的AI9
     * @param ai92      从上到下的AI9
     * @param ai93      从左上到右下的AI9
     * @param ai94      从左下到右上的AI9
     * @return          4个AI9组合在一起的权重
     */
    int wights(boolean attack, AI9 ai91, AI9 ai92, AI9 ai93, AI9 ai94) {
        int wights = ai91.weight + ai92.weight + ai93.weight + ai94.weight;
        if (!attack) {
            wights /= 2;
        }
        return wights;
    }
}
