package com.AE.system.view.menu;

import com.AE.system.util.SpringUtil;
import com.AE.system.util.TitleUtil;
import com.AE.system.view.frame.MainFrame;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Controller
public class MainMenu extends JPanel {

    private final JButton listButton = new JButton("1. 线性表;");
    private final JButton treeButton = new JButton("2. 树;");
    private final JButton graphButton = new JButton("3. 图;");
    private final JButton sortButton = new JButton("4. 排序;");
    private final Font buttonFont = new Font("宋体", Font.PLAIN, 20);

    @PostConstruct
    private void init() {
        TitleUtil.setTitle(this);
        setButton();
        add(listButton);
        add(treeButton);
        add(graphButton);
        add(sortButton);
        setLayout(new GridLayout(getComponentCount(), 1));
    }

    private void setButton() {
        listButton.setFont(buttonFont);
        treeButton.setFont(buttonFont);
        graphButton.setFont(buttonFont);
        sortButton.setFont(buttonFont);
        listButton.addActionListener(e -> {
            MainFrame mainFrame = SpringUtil.getBean("mainFrame", MainFrame.class);
            MainMenu mainMenu = SpringUtil.getBean("mainMenu", MainMenu.class);
            ListMenu listMenu = SpringUtil.getBean("listMenu", ListMenu.class);
            mainFrame.remove(mainMenu);
            mainFrame.add(listMenu);
        });
        treeButton.addActionListener(e -> {
            MainFrame mainFrame = SpringUtil.getBean("mainFrame", MainFrame.class);
            MainMenu mainMenu = SpringUtil.getBean("mainMenu", MainMenu.class);
            TreeMenu treeMenu = SpringUtil.getBean("treeMenu", TreeMenu.class);
            mainFrame.remove(mainMenu);
            mainFrame.add(treeMenu);
        });
        graphButton.addActionListener(e -> {
            MainFrame mainFrame = SpringUtil.getBean("mainFrame", MainFrame.class);
            MainMenu mainMenu = SpringUtil.getBean("mainMenu", MainMenu.class);
            GraphMenu graphMenu = SpringUtil.getBean("graphMenu", GraphMenu.class);
            mainFrame.remove(mainMenu);
            mainFrame.add(graphMenu);
        });
        sortButton.addActionListener(e -> {
            MainFrame mainFrame = SpringUtil.getBean("mainFrame", MainFrame.class);
            MainMenu mainMenu = SpringUtil.getBean("mainMenu", MainMenu.class);
            SortMenu sortMenu = SpringUtil.getBean("sortMenu", SortMenu.class);
            mainFrame.remove(mainMenu);
            mainFrame.add(sortMenu);
        });
    }
}
