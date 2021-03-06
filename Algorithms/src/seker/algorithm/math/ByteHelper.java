/*
 * Copyright (C) 2013 Baidu Inc. All rights reserved.
 */
package seker.algorithm.math;

/**
 * 字节单位转换辅助类
 * 
 * @author liuxinjian
 * @since 2013-3-12
 */
public final class ByteHelper {

    /** "." */
    private static final String DOT = ".";

    /** 以1024单位进制 */
    private static final int UNIT = 1024;

    /**
     * Byte的单位
     * 
     * @author liuxinjian
     * @since 2013-3-12
     */
    public enum UNITS {
     /** 字节  */
        Byte,
        /** 千字节 */
        KB, 
        /** 兆字节 */
        MB, 
        /** 吉字节 */
        GB, 
        /** 千吉字节 */
        TB, 
        /** PB */
        PB, 
        /** EB */
        EB, 
        /** ZB */
        ZB, 
        /** YB */
        YB, 
        /** NB */
        NB, 
        /** DB */
        DB, 
    }

    /**
     * Byte单位转换辅助类
     * 
     * @author liuxinjian
     * @since 2013-3-12
     */
    static class ByteUnitConverter {
        /** 字节值 */
        private short bt = 0;
        /** 千字节值 */
        private short kb = 0;
        /** 兆字节值 */
        private short mb = 0;
        /** 吉字节 */
        private short gb = 0;
        /** 千吉字节 */
        private short tb = 0;
        /** PB字节值 */
        private short pb = 0;
        /** EB字节值 */
        private short eb = 0;
        /** ZB字节值 */
        private short zb = 0;
        /** YB字节值 */
        private short yb = 0;
        /** NB字节值 */
        private short nb = 0;
        /** DB字节值 */
        private short db = 0;

        /**
         * 转换以byte为单位的值
         * 
         * @param num    byte值
         */
        private void convertByte(double num) {
            if (num <= 0) {
                bt = 0;
            } else {
                if (num < UNIT) {
                    bt = (short) num;
                } else {
                    bt = (short) (num % UNIT);
                    num /= UNIT;
                    if (num < UNIT) {
                        kb = (short) num;
                    } else {
                        kb = (short) (num % UNIT);
                        num /= UNIT;
                        if (num < UNIT) {
                            mb = (short) num;
                        } else {
                            mb = (short) (num % UNIT);
                            num /= UNIT;
                            if (num < UNIT) {
                                gb = (short) num;
                            } else {
                                gb = (short) (num % UNIT);
                                num /= UNIT;
                                if (num < UNIT) {
                                    tb = (short) num;
                                } else {
                                    tb = (short) (num % UNIT);
                                    num /= UNIT;
                                    if (num < UNIT) {
                                        pb = (short) num;
                                    } else {
                                        pb = (short) (num % UNIT);
                                        num /= UNIT;
                                        if (num < UNIT) {
                                            eb = (short) num;
                                        } else {
                                            eb = (short) (num % UNIT);
                                            num /= UNIT;
                                            if (num < UNIT) {
                                                zb = (short) num;
                                            } else {
                                                zb = (short) (num % UNIT);
                                                num /= UNIT;
                                                if (num < UNIT) {
                                                    yb = (short) num;
                                                } else {
                                                    yb = (short) (num % UNIT);
                                                    num /= UNIT;
                                                    if (num < UNIT) {
                                                        nb = (short) num;
                                                    } else {
                                                        nb = (short) (num % UNIT);
                                                        num /= UNIT;
                                                        if (num < UNIT) {
                                                            db = (short) num;
                                                        } else {
                                                            db = (short) (num % UNIT);
                                                            num /= UNIT;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        /**
         * 转换以KB为单位的值
         * 
         * @param num    KB值
         */
        private void convertKiloByte(double num) {
            convertByte(num * UNIT);
        }
        
        /**
         * 转换以MB为单位的值
         * 
         * @param num    MB值
         */
        private void convertMegaByte(double num) {
            convertKiloByte(num * UNIT);
        }
        
        /**
         * 转换以GB为单位的值
         * 
         * @param num    GB值
         */
        private void convertGigaByte(double num) {
            convertMegaByte(num * UNIT);
        }
        
        /**
         * 转换以TB为单位的值
         * 
         * @param num    TB值
         */
        private void convertTeraByte(double num) {
            convertGigaByte(num * UNIT);
        }
        
        /**
         * 转换以PB为单位的值
         * 
         * @param num    PEB值
         */
        private void convertPetaByte(double num) {
            convertTeraByte(num * UNIT);
        }
        
        /**
         * 转换以EB为单位的值
         * 
         * @param num    EB值
         */
        private void convertExaByte(double num) {
            convertPetaByte(num * UNIT);
        }
        
        /**
         * 转换以ZB为单位的值
         * 
         * @param num    ZB值
         */
        private void convertZettaByte(double num) {
            convertPetaByte(num * UNIT);
        }
        
        /**
         * 转换以YB为单位的值
         * 
         * @param num    YB值
         */
        private void convertYottaByte(double num) {
            convertZettaByte(num * UNIT);
        }
        
        /**
         * 转换以NB为单位的值
         * 
         * @param num    NB值
         */
        private void convertNonaByte(double num) {
            convertYottaByte(num * UNIT);
        }
        
        /**
         * 转换以DB为单位的值
         * 
         * @param num    DB值
         */
        private void convertDoggaByte(double num) {
            convertNonaByte(num * UNIT);
        }

        /**
         * 构造方法
         * 
         * @param num
         *            byte值
         * @param unit
         *            单位
         */
        public ByteUnitConverter(double num, UNITS unit) {
            switch (unit) {
            case Byte:
                convertByte(num);
                break;
            case KB:
                convertKiloByte(num);
                break;
            case MB:
                convertMegaByte(num);
                break;
            case GB:
                convertGigaByte(num);
                break;
            case TB:
                convertTeraByte(num);
                break;
            case PB:
                convertPetaByte(num);
                break;
            case EB:
                convertExaByte(num);
                break;
            case ZB:
                convertZettaByte(num);
                break;
            case YB:
                convertYottaByte(num);
                break;
            case NB:
                convertNonaByte(num);
                break;
            case DB:
                convertDoggaByte(num);
                break;
            default:
                break;
            }
        }

        @Override
        public String toString() {
            if (db > 0) {
                return db + DOT + nb + UNITS.DB.name();
            } else if (nb > 0) {
                return nb + DOT + yb + UNITS.NB.name();
            } else if (yb > 0) {
                return yb + DOT + zb + UNITS.YB.name();
            } else if (zb > 0) {
                return zb + DOT + eb + UNITS.ZB.name();
            } else if (eb > 0) {
                return eb + DOT + pb + UNITS.EB.name();
            } else if (pb > 0) {
                return pb + DOT + tb + UNITS.PB.name();
            } else if (tb > 0) {
                return tb + DOT + gb + UNITS.TB.name();
            } else if (gb > 0) {
                return gb + DOT + mb + UNITS.GB.name();
            } else if (mb > 0) {
                return mb + DOT + kb + UNITS.MB.name();
            } else if (kb > 0) {
                return kb + DOT + bt + UNITS.KB.name();
            } else if (bt > 0) {
                return bt + UNITS.Byte.name();
            } else {
                return "0.00" + UNITS.Byte.name();
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new ByteUnitConverter(999000000, UNITS.Byte));
        System.out.println(new ByteUnitConverter(999999999, UNITS.KB));
        System.out.println(new ByteUnitConverter(0.00000009, UNITS.TB));
        System.out.println(new ByteUnitConverter(0.00000965, UNITS.GB));
    }
}
