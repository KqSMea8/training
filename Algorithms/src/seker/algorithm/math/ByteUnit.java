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
public class ByteUnit {

    // "."
    private static final String DOT = ".";
    
    // "0"
    private static final String ZERO = "0";
    
    // 字节
    public static final String B = "BYTE";
    // 千字节
    public static final String K = "KB";
    // 兆字节
    public static final String M = "MB";
    // 吉字节
    public static final String G = "GB";
    // 千吉字节
    public static final String T = "TB";
    // PB
    public static final String P = "PB";
    // EB
    public static final String E = "EB";
    // ZB
    public static final String Z = "ZB";
    // YB
    public static final String Y = "YB";
    // NB
    public static final String N = "NB";
    // DB
    public static final String D = "DB";

    // 以1024单位进制
    public static final int UNIT = 1024;

    // 字节
    private String bytes = ZERO;
    // 千字节
    private String kiloByte = ZERO;
    // 兆字节
    private String megaByte = ZERO;
    // 吉字节
    private String gigaByte = ZERO;
    // 太字节
    private String teraByte = ZERO;
    // 拍字节
    private String petaByte = ZERO;
    // EB
    private String exaByte = ZERO;
    // ZB
    private String zettaByte = ZERO;
    // YB
    private String yottaByte = ZERO;
    // NB
    private String nonaByte = ZERO;
    // DB
    private String doggaByte = ZERO;

    /**
     * 构造方法
     * 
     * @param num
     *            byte值
     * @param unit
     *            单位
     */

    public ByteUnit(double num, String unit) {
        double bt = 0d;
        double kb = 0d;
        double mb = 0d;
        double gb = 0d;
        double tb = 0d;
        double pb = 0d;
        double eb = 0d;
        double zb = 0d;
        double yb = 0d;
        double nb = 0d;
        double db = 0d;

        if (0 < num && unit != null && D.equals(unit)) {
            db = num;
            nb = db * ByteUnit.UNIT;
            yb = nb * ByteUnit.UNIT;
            zb = yb * ByteUnit.UNIT;
            eb = zb * ByteUnit.UNIT;
            pb = eb * ByteUnit.UNIT;
            tb = pb * ByteUnit.UNIT;
            gb = tb * ByteUnit.UNIT;
            mb = gb * ByteUnit.UNIT;
            kb = mb * ByteUnit.UNIT;
            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && N.equals(unit)) {
            nb = num;

            db = nb / ByteUnit.UNIT;

            yb = nb * ByteUnit.UNIT;
            zb = yb * ByteUnit.UNIT;
            eb = zb * ByteUnit.UNIT;
            pb = eb * ByteUnit.UNIT;
            tb = pb * ByteUnit.UNIT;
            gb = tb * ByteUnit.UNIT;
            mb = gb * ByteUnit.UNIT;
            kb = mb * ByteUnit.UNIT;
            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && Y.equals(unit)) {
            yb = num;

            nb = yb / ByteUnit.UNIT;
            db = nb / ByteUnit.UNIT;

            zb = yb * ByteUnit.UNIT;
            eb = zb * ByteUnit.UNIT;
            pb = eb * ByteUnit.UNIT;
            tb = pb * ByteUnit.UNIT;
            gb = tb * ByteUnit.UNIT;
            mb = gb * ByteUnit.UNIT;
            kb = mb * ByteUnit.UNIT;
            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && Z.equals(unit)) {
            zb = num;

            yb = zb / ByteUnit.UNIT;
            nb = yb / ByteUnit.UNIT;
            db = nb / ByteUnit.UNIT;

            eb = zb * ByteUnit.UNIT;
            pb = eb * ByteUnit.UNIT;
            tb = pb * ByteUnit.UNIT;
            gb = tb * ByteUnit.UNIT;
            mb = gb * ByteUnit.UNIT;
            kb = mb * ByteUnit.UNIT;
            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && E.equals(unit)) {
            eb = num;

            zb = eb / ByteUnit.UNIT;
            yb = zb / ByteUnit.UNIT;
            nb = yb / ByteUnit.UNIT;
            db = nb / ByteUnit.UNIT;

            pb = eb * ByteUnit.UNIT;
            tb = pb * ByteUnit.UNIT;
            gb = tb * ByteUnit.UNIT;
            mb = gb * ByteUnit.UNIT;
            kb = mb * ByteUnit.UNIT;
            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && P.equals(unit)) {
            pb = num;

            eb = pb / ByteUnit.UNIT;
            zb = eb / ByteUnit.UNIT;
            yb = zb / ByteUnit.UNIT;
            nb = yb / ByteUnit.UNIT;
            db = nb / ByteUnit.UNIT;

            tb = pb * ByteUnit.UNIT;
            gb = tb * ByteUnit.UNIT;
            mb = gb * ByteUnit.UNIT;
            kb = mb * ByteUnit.UNIT;
            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && T.equals(unit)) {
            tb = num;

            pb = tb / ByteUnit.UNIT;
            eb = pb / ByteUnit.UNIT;
            zb = eb / ByteUnit.UNIT;
            yb = zb / ByteUnit.UNIT;
            nb = yb / ByteUnit.UNIT;
            db = nb / ByteUnit.UNIT;

            gb = tb * ByteUnit.UNIT;
            mb = gb * ByteUnit.UNIT;
            kb = mb * ByteUnit.UNIT;
            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && G.equals(unit)) {
            gb = num;

            tb = gb / ByteUnit.UNIT;
            pb = tb / ByteUnit.UNIT;
            eb = pb / ByteUnit.UNIT;
            zb = eb / ByteUnit.UNIT;
            yb = zb / ByteUnit.UNIT;
            nb = yb / ByteUnit.UNIT;
            db = nb / ByteUnit.UNIT;

            mb = gb * ByteUnit.UNIT;
            kb = mb * ByteUnit.UNIT;
            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && M.equals(unit)) {
            mb = num;

            gb = mb / ByteUnit.UNIT;
            tb = gb / ByteUnit.UNIT;
            pb = tb / ByteUnit.UNIT;
            eb = pb / ByteUnit.UNIT;
            zb = eb / ByteUnit.UNIT;
            yb = zb / ByteUnit.UNIT;
            nb = yb / ByteUnit.UNIT;
            db = nb / ByteUnit.UNIT;

            kb = mb * ByteUnit.UNIT;
            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && K.equals(unit)) {
            kb = num;

            mb = kb / ByteUnit.UNIT;
            gb = mb / ByteUnit.UNIT;
            tb = gb / ByteUnit.UNIT;
            pb = tb / ByteUnit.UNIT;
            eb = pb / ByteUnit.UNIT;
            zb = eb / ByteUnit.UNIT;
            yb = zb / ByteUnit.UNIT;
            nb = yb / ByteUnit.UNIT;
            db = nb / ByteUnit.UNIT;

            bt = kb * ByteUnit.UNIT;
        } else if (num != 0 && unit != null && B.equals(unit)) {
            bt = num;

            kb = bt / ByteUnit.UNIT;
            mb = kb / ByteUnit.UNIT;
            gb = mb / ByteUnit.UNIT;
            tb = gb / ByteUnit.UNIT;
            pb = tb / ByteUnit.UNIT;
            eb = pb / ByteUnit.UNIT;
            zb = eb / ByteUnit.UNIT;
            yb = zb / ByteUnit.UNIT;
            nb = yb / ByteUnit.UNIT;
            db = nb / ByteUnit.UNIT;
        }

        if (bt != 0) {
            String sValue = String.valueOf(bt);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setBytes(sValue);
            } else {
                this.setBytes(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setBytes(String.valueOf(bt));
        }

        if (kb != 0) {
            String sValue = String.valueOf(kb);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setKiloByte(sValue);
            } else {
                this.setKiloByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setKiloByte(String.valueOf(kb));
        }

        if (mb != 0) {
            String sValue = String.valueOf(mb);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setMegaByte(sValue);
            } else {
                this.setMegaByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setMegaByte(String.valueOf(mb));
        }

        if (gb != 0) {
            String sValue = String.valueOf(gb);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setGigaByte(sValue);
            } else {
                this.setGigaByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setGigaByte(String.valueOf(gb));
        }

        if (tb != 0) {
            String sValue = String.valueOf(tb);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setTeraByte(sValue);
            } else {
                this.setTeraByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setTeraByte(String.valueOf(tb));
        }

        if (pb != 0) {
            String sValue = String.valueOf(pb);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setPetaByte(sValue);
            } else {
                this.setPetaByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setPetaByte(String.valueOf(pb));
        }

        if (eb != 0) {
            String sValue = String.valueOf(eb);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setExaByte(sValue);
            } else {
                this.setExaByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setExaByte(String.valueOf(eb));
        }

        if (zb != 0) {
            String sValue = String.valueOf(zb);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setZettaByte(sValue);
            } else {
                this.setZettaByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setZettaByte(String.valueOf(zb));
        }

        if (yb != 0) {
            String sValue = String.valueOf(yb);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setYottaByte(sValue);
            } else {
                this.setYottaByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setYottaByte(String.valueOf(yb));
        }

        if (nb != 0) {
            String sValue = String.valueOf(nb);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setNonaByte(sValue);
            } else {
                this.setNonaByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setNonaByte(String.valueOf(nb));
        }

        if (db != 0) {
            String sValue = String.valueOf(db);
            if (!ZERO.equals(sValue.substring(sValue.indexOf(DOT) + 1))) {
                this.setDoggaByte(sValue);
            } else {
                this.setDoggaByte(sValue.substring(0, sValue.indexOf(DOT)));
            }
        } else {
            this.setDoggaByte(String.valueOf(db));
        }

    }

    public String getBytes() {
        return bytes;
    }

    private void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getKiloByte() {
        return kiloByte;
    }

    private void setKiloByte(String kiloByte) {
        this.kiloByte = kiloByte;
    }

    public String getMegaByte() {
        return megaByte;
    }

    private void setMegaByte(String megaByte) {
        this.megaByte = megaByte;
    }

    public String getGigaByte() {
        return gigaByte;
    }

    private void setGigaByte(String gigaByte) {
        this.gigaByte = gigaByte;
    }

    public String getTeraByte() {
        return teraByte;
    }

    private void setTeraByte(String teraByte) {
        this.teraByte = teraByte;
    }

    public String getPetaByte() {
        return petaByte;
    }

    private void setPetaByte(String petaByte) {
        this.petaByte = petaByte;
    }

    public String getExaByte() {
        return exaByte;
    }

    private void setExaByte(String exaByte) {
        this.exaByte = exaByte;
    }

    public String getZettaByte() {
        return zettaByte;
    }

    private void setZettaByte(String zettaByte) {
        this.zettaByte = zettaByte;
    }

    public String getYottaByte() {
        return yottaByte;
    }

    private void setYottaByte(String yottaByte) {
        this.yottaByte = yottaByte;
    }

    public String getNonaByte() {
        return nonaByte;
    }

    private void setNonaByte(String nonaByte) {
        this.nonaByte = nonaByte;
    }

    public String getDoggaByte() {
        return doggaByte;
    }

    private void setDoggaByte(String doggaByte) {
        this.doggaByte = doggaByte;
    }
    
    @Override
    public String toString() {
        return null;
    }

    public static void main(String[] args) {
        int k = 30000;
        final String unit = ByteUnit.K;
        ByteUnit bu = new ByteUnit(k, unit);

        System.out.println(k + " " + unit + " = " + bu.getBytes() + " " + ByteUnit.B);
        System.out.println(k + " " + unit + " = " + bu.getKiloByte() + " " + ByteUnit.K);
        System.out.println(k + " " + unit + " = " + bu.getMegaByte() + " " + ByteUnit.M);
        System.out.println(k + " " + unit + " = " + bu.getGigaByte() + " " + ByteUnit.G);
        System.out.println(k + " " + unit + " = " + bu.getTeraByte() + " " + ByteUnit.T);
        System.out.println(k + " " + unit + " = " + bu.getPetaByte() + " " + ByteUnit.P);
        System.out.println(k + " " + unit + " = " + bu.getExaByte() + " " + ByteUnit.E);
        System.out.println(k + " " + unit + " = " + bu.getZettaByte() + " " + ByteUnit.Z);
        System.out.println(k + " " + unit + " = " + bu.getYottaByte() + " " + ByteUnit.Y);
        System.out.println(k + " " + unit + " = " + bu.getNonaByte() + " " + ByteUnit.N);
        System.out.println(k + " " + unit + " = " + bu.getDoggaByte() + " " + ByteUnit.D);
    }

}
