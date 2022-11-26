package com.AE.system.view.menu;

import com.AE.system.util.SpringUtil;
import com.AE.system.util.TitleUtil;
import com.AE.system.view.data.GraphDataService;
import com.AE.system.view.frame.MainFrame;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Controller
public class GraphMenu extends JPanel {

    private final JButton backButton = new JButton("返回");
    private final JButton SSSPButton = new JButton("单源最短路径");

    private final Font buttonFont = new Font("宋体", Font.PLAIN, 20);

    @PostConstruct
    public void init() {
        TitleUtil.setTitle(this);
        JLabel titleLabel = new JLabel("图论", SwingConstants.CENTER);
        titleLabel.setFont(new Font("宋体", Font.BOLD, 25));
        this.add(titleLabel);
        setButton();
        add(SSSPButton);
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
        SSSPButton.setFont(buttonFont);
        SSSPButton.addActionListener(e -> {
            MainFrame mainFrame = SpringUtil.getBean("mainFrame", MainFrame.class);
            GraphDataService graphDataService = SpringUtil.getBean("graphDataService", GraphDataService.class);
            mainFrame.remove(this);
            mainFrame.add(graphDataService);
        });
    }
}
