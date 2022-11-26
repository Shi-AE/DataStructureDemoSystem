package com.AE.system.view.menu;

import com.AE.system.util.SpringUtil;
import com.AE.system.util.TitleUtil;
import com.AE.system.view.frame.MainFrame;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Controller
public class SortMenu extends JPanel {
    private final JButton backButton = new JButton("返回");
    private final Font buttonFont = new Font("宋体", Font.PLAIN, 20);

    @PostConstruct
    public void init() {
        TitleUtil.setTitle(this);
        JLabel titleLabel = new JLabel("排序", SwingConstants.CENTER);
        titleLabel.setFont(new Font("宋体", Font.BOLD, 25));
        add(titleLabel);
        setButton();
        add(backButton);
        setLayout(new GridLayout(getComponentCount(), 1));
    }

    public void setButton() {
        backButton.setFont(buttonFont);
        backButton.addActionListener(e -> {
            MainFrame mainFrame = SpringUtil.getBean("mainFrame", MainFrame.class);
            MainMenu mainMenu = SpringUtil.getBean("mainMenu", MainMenu.class);
            mainFrame.remove(this);
            mainFrame.add(mainMenu);
        });
    }
}
