package com.AE.system.view.frame;

import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Controller
public class MainFrame extends JFrame {

    public MainFrame() {}

    @PostConstruct
    private void init() {
        this.setVisible(true);
        this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public Component add(Component comp) {
        comp = super.add(comp);
        comp.revalidate();
        comp.repaint();
        return comp;
    }

    @Override
    public void remove(Component comp) {
        super.remove(comp);
        super.revalidate();
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("mainPaint");
        super.paint(g);
    }
}
