package com.AE.system.view.display;

import com.AE.system.dao.DirectWeightedGraph;
import com.AE.system.dao.DirectWeightedSide;
import com.AE.system.util.DrawUtil;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * 单源最短路径的算法展示
 *
 * @author A.E.
 * @version 1.0
 */
public class SSSPDisplay extends JFrame {
    /**
     * 绘制坐标
     */
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final JButton paintButton;

    /**
     * 图
     */
    private final DirectWeightedGraph graph;
    /**
     * 顶点绘画坐标
     */
    private HashMap<Integer, Point> drawPoints;
    /**
     * 顶点距离源点距离
     */
    private final HashMap<Integer, Integer> pointLength = new HashMap<>();
    /**
     * 未被计算的点
     */
    private final TreeSet<Integer> notCalculatePoint;
    /**
     * 已被计算的点
     */
    private final TreeSet<Integer> calculatePoint;
    /**
     * 顶点绘制半径
     */
    private final int pointRadius = 15;
    /**
     * 执行是否开始
     */
    private boolean isStart = false;
    /**
     * 暂停程序信号
     */
    private boolean stop = true;
    /**
     * 算法结束信号
     */
    private boolean end = false;

    public SSSPDisplay(DirectWeightedGraph graph) {
        //初始化显示框
        setSize(1000, 600);
        setFont(new Font("楷体", Font.PLAIN, 20));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        paintButton = new JButton("下一步");

        //初始化数据
        this.graph = graph;
        notCalculatePoint = new TreeSet<>(Arrays.asList(graph.getPoints()));
        notCalculatePoint.remove(graph.getSource());
        calculatePoint = new TreeSet<>(Collections.singleton(graph.getSource()));
        buildDrawPoint();
        buildLength();

        paintButton.addActionListener(l -> {
            if (!isStart) {
                isStart = true;
                Thread thread = new Thread(() -> {
                    //执行算法
                    setBreakPoint();
                    dijkstra();
                });
                thread.start();
            }
            if (!end) {
                stop = false;
            } else {
                JOptionPane.showMessageDialog(null, "计算已结束");
            }
        });
        add(paintButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * 构建距离表
     */
    private void buildLength() {
        Integer[] points = graph.getPoints();
        Integer source = graph.getSource();
        for (Integer point : points) {
            if (point.equals(source)) {
                pointLength.put(point, 0);
            } else {
                pointLength.put(point, Integer.MAX_VALUE);
            }
        }
    }

    /**
     * 构建点位
     */
    private void buildDrawPoint() {
        int pointNum = graph.getPointNum();
        Integer[] points = graph.getPoints();
        //圆弧长
        double graphAngle = 2 * Math.PI;
        //顶点相隔的弧长
        double pointAngle = graphAngle / pointNum;
        drawPoints = new HashMap<>();
        //顶点图围成的圆半径
        int graphRadius = 200;
        //相对于(0, 0)点的偏移量
        int graphTransformX = 450;
        int graphTransformY = 300;
        for (int i = 0; i < pointNum; i++) {
            int x = (int) (Math.cos(pointAngle * i) * graphRadius) + graphTransformX;
            int y = (int) (Math.sin(pointAngle * i) * graphRadius) + graphTransformY;
            drawPoints.put(points[i], new Point(x, y));
        }
    }

    /**
     * 迪杰斯特拉算法
     */
    private void dijkstra() {
        while (!notCalculatePoint.isEmpty()) {
            updateLength();
            updatePoint();
        }
        end = true;
    }

    /**
     * 更新距离信息
     */
    private void updateLength() {
        HashMap<Integer, ArrayList<DirectWeightedSide>> sidesMap = graph.getSides();
        calculatePoint.forEach(point -> {
            ArrayList<DirectWeightedSide> sides = sidesMap.get(point);
            int sourceWeight = pointLength.get(point);
            if (sides != null) {
                sides.forEach(side -> {
                    int target = side.getTarget();
                    if (notCalculatePoint.contains(target)) {
                        int weight = side.getWeight();
                        pointLength.replace(target, weight + sourceWeight);
                        setBreakPoint();
                    }
                });
            }
        });
    }

    /**
     * 更新顶点信息
     */
    private void updatePoint() {
        Integer minPoint = null;
        int minLength = Integer.MAX_VALUE;
        for (Integer point : notCalculatePoint) {
            Integer length = pointLength.get(point);
            if (length < minLength) {
                minLength = length;
                minPoint = point;
            }
        }
        try {
            notCalculatePoint.remove(minPoint);
            calculatePoint.add(minPoint);
        } catch (NullPointerException e) {
            end = true;
        }
        setBreakPoint();
    }

    /**
     * 绘制图
     *
     * @param g 绘制工具
     */
    private void drawGraph(Graphics g) {
        //绘制未被计算的点
        g.setColor(Color.RED);
        drawPoint(notCalculatePoint, g);
        //绘制已被计算的点
        g.setColor(Color.GREEN);
        drawPoint(calculatePoint, g);
        //绘制边
        g.setColor(Color.MAGENTA);
        graph.getSides().forEach((point, sides) -> {
            Point pointStart = drawPoints.get(point);
            sides.forEach(side -> {
                int target = side.getTarget();
                int weight = side.getWeight();
                Point pointTarget = drawPoints.get(target);
                DrawUtil.drawAL(pointStart.x, pointStart.y, pointTarget.x, pointTarget.y, pointRadius, g);
                g.drawString("距离:" + weight, (pointStart.x + pointTarget.x) >> 1, (pointStart.y + pointTarget.y) >> 1);
            });
        });
        //绘制距离
        g.setColor(Color.BLUE);
        pointLength.forEach((point, length) -> g.drawString(
                "距离源点:" + (length == Integer.MAX_VALUE ? "∞" : length),
                drawPoints.get(point).x - pointRadius,
                drawPoints.get(point).y - pointRadius));
    }

    /**
     * 绘制顶点
     *
     * @param notCalculatePoint 顶点集合
     * @param g                 绘图工具
     */
    private void drawPoint(TreeSet<Integer> notCalculatePoint, Graphics g) {
        notCalculatePoint.forEach(point -> {
            Point drawPoint = drawPoints.get(point);
            DrawUtil.drawCircle(drawPoint.x, drawPoint.y, pointRadius, g);
            g.drawString(point.toString(), drawPoint.x - pointRadius / 2 + 5, drawPoint.y - pointRadius / 2 + 5);
        });
    }

    /**
     * 设置断点
     */
    private void setBreakPoint() {
        if (!end) {
            try {
                repaint();
                do {
                    Thread.sleep(10);
                } while (stop);
                stop = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        drawGraph(g);
        paintButton.repaint();
        g.dispose();
//        System.out.println("paint");
    }

    public static void main(String[] args) {
        int n = 3;
        int m = 3;
        Integer[] points = {0, 1, 2};
        HashMap<Integer, ArrayList<DirectWeightedSide>> sides = new HashMap<>();
        sides.put(0, new ArrayList<>());
        sides.get(0).add(new DirectWeightedSide(1, 2));
        sides.put(1, new ArrayList<>());
        sides.get(1).add(new DirectWeightedSide(2, 3));
        sides.put(2, new ArrayList<>());
        sides.get(2).add(new DirectWeightedSide(0, 1));
        new SSSPDisplay(new DirectWeightedGraph(n, m, 0, points, sides));

    }
}
