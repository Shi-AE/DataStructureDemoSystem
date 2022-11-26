package com.AE.system.Main;

import com.AE.system.util.SpringUtil;
import com.AE.system.view.frame.MainFrame;
import com.AE.system.view.menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = SpringUtil.getBean("mainFrame", MainFrame.class);
        MainMenu mainMenu = SpringUtil.getBean("mainMenu", MainMenu.class);
        mainFrame.add(mainMenu);
    }
}
