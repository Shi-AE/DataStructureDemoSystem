package com.AE.system.util;

import java.awt.*;

/**
 * 画图拓展工具
 *
 * @author A.E.
 * @version 1.0
 */
public class DrawUtil {
    static class Point {
        int x;
        int y;
    }

    private DrawUtil() {
    }

    /**
     * 带箭头的直线
     *
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @param g  绘图工具
     */
    public static void drawAL(int x1, int y1, int x2, int y2,int r, Graphics g) {
        double a = Math.abs(x1 - x2);
        double b = Math.abs(y1 - y2);
        double c = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
        int dx = (int) (r * a / c);
        int dy = (int) (r * b / c);
        if (x1 > x2) {
            x1 -= dx;
            x2 += dx;
        } else {
            x1 += dx;
            x2 -= dx;
        }
        if (y1 > y2) {
            y1 -= dy;
            y2 += dy;
        } else {
            y1 += dy;
            y2 -= dy;
        }
        double H = 10;  // 箭头高度
        double L = 6; // 底边的一半
        double arrowAngle = Math.atan(L / H);  // 箭头角度
        double arrowLength = Math.sqrt(L * L + H * H); // 箭头的长度
        Point point1 = rotateVec(x2 - x1, y2 - y1, arrowAngle, arrowLength);
        Point point2 = rotateVec(x2 - x1, y2 - y1, -arrowAngle, arrowLength);
        // 画线
        g.drawLine(x1, y1, x2, y2);
        // 画箭头的一半
        g.drawLine(x2, y2, x2 - point1.x, y2 - point1.y);
        // 画箭头的另一半
        g.drawLine(x2, y2, x2 - point2.x, y2 - point2.y);

    }

    /**
     * 计算箭头表现状态
     *
     * @param x      x分量
     * @param y      y分量
     * @param ang    旋转角
     * @param newLen 箭头的长度
     * @return 描述箭头状态坐标
     */
    private static Point rotateVec(int x, int y, double ang, double newLen) {
        Point point = new Point();
        double vx = x * Math.cos(ang) - y * Math.sin(ang);
        double vy = x * Math.sin(ang) + y * Math.cos(ang);
        double d = Math.sqrt(vx * vx + vy * vy);
        vx = vx / d * newLen;
        vy = vy / d * newLen;
        point.x = (int) vx;
        point.y = (int) vy;
        return point;
    }

    /**
     * 画制圆
     *
     * @param x 坐标x
     * @param y 坐标y
     * @param r 半径
     * @param g 绘制工具
     */
    public static void drawCircle(int x, int y, int r, Graphics g) {
        g.drawOval(x - r,y - r,r * 2,r * 2);
    }
}
