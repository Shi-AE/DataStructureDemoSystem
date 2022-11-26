package com.AE.system.util;

import javax.swing.*;
import java.awt.*;

/**
 * 添加标头
 * @author A.E.
 * @version 1.0
 */
public class TitleUtil {
    private TitleUtil() {}

    /**
     * 添加系统表头
     * @param comp 目标容器
     */
    public static void setTitle(JComponent comp) {
        JLabel titleLabel = new JLabel("欢迎使用数据结构算法动态演示系统", SwingConstants.CENTER);
        JLabel authorLabel = new JLabel("作者：施亦翔", SwingConstants.CENTER);

        titleLabel.setFont(new Font("宋体", Font.BOLD, 25));
        authorLabel.setFont(new Font("宋体", Font.PLAIN, 20));

        comp.add(titleLabel);
        comp.add(authorLabel);
    }
}
