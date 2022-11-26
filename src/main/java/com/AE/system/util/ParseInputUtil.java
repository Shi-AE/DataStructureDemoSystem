package com.AE.system.util;

import com.AE.system.dao.DirectWeightedGraph;
import com.AE.system.dao.DirectWeightedSide;
import com.AE.system.exception.InputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 解析用户输入工具
 *
 * @author A.E.
 * @version 1.0
 */
public class ParseInputUtil {
    private ParseInputUtil() {
    }

    /**
     * 解析有向加权图
     *
     * @param map 用户输入图
     * @param stringSource 源点字符串
     * @return 有向加权图
     */
    public static DirectWeightedGraph parseDirectWeightedGraph(String map,String stringSource) {
        String[] sides = map.split("\\n");
        int sideNum = sides.length;
        DirectWeightedGraph directWeightedGraph = null;
        HashMap<Integer, ArrayList<DirectWeightedSide>> directWeightedSides = new HashMap<>();
        HashSet<Integer> points = new HashSet<>();
        //解析边
        try {
            for (String s : sides) {
                String[] side = s.trim().split("\\s+");
                if (side.length != 3) {
                    throw new InputException("输入格式错误");
                }
                int start = Integer.parseInt(side[0]);
                int end = Integer.parseInt(side[1]);
                int weight = Integer.parseInt(side[2]);
                DirectWeightedSide directWeightedSide = new DirectWeightedSide(end, weight);
                if (!directWeightedSides.containsKey(start)) {
                    directWeightedSides.put(start, new ArrayList<>());
                }
                directWeightedSides.get(start).add(directWeightedSide);
                points.add(start);
                points.add(end);
            }
            Integer source = Integer.valueOf(stringSource);
            if (!points.contains(source)) {
                throw new InputException("源点不存在");
            }
            int pointNum = points.size();
            Integer[] directWeightedPoints = new Integer[pointNum];
            points.toArray(directWeightedPoints);
            directWeightedGraph = new DirectWeightedGraph(pointNum, sideNum, source, directWeightedPoints, directWeightedSides);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directWeightedGraph;
    }
}
