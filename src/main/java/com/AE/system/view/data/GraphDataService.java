package com.AE.system.view.data;

import com.AE.system.dao.DirectWeightedGraph;
import com.AE.system.util.ParseInputUtil;
import com.AE.system.util.TitleUtil;
import com.AE.system.view.display.SSSPDisplay;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Service
public class GraphDataService extends JPanel {
    private final JTextArea dataText = new JTextArea();
    private final JButton submitButton = new JButton("提交");

    private final Font buttonFont = new Font("宋体", Font.PLAIN, 20);

    @PostConstruct
    public void init() {
        TitleUtil.setTitle(this);

        setButton();

        dataText.setFont(new Font("Consolas", Font.PLAIN, 20));
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(dataText);
        add(jScrollPane);
        add(submitButton);
        setLayout(new GridLayout(getComponentCount(), 1));
    }

    public void setButton() {
        submitButton.setFont(buttonFont);
        submitButton.addActionListener(l -> {
            //获取图文本
            String graph = dataText.getText();
            //获取源点
            String source = JOptionPane.showInputDialog("请输入源点");
            DirectWeightedGraph directWeightedGraph = ParseInputUtil.parseDirectWeightedGraph(graph,source);
            if (directWeightedGraph == null) {
                JOptionPane.showMessageDialog(null, "输入格式错误", "输入有误", JOptionPane.PLAIN_MESSAGE);
            } else {
                //动态展示
                SSSPDisplay ssspDisplay = new SSSPDisplay(directWeightedGraph);
                ssspDisplay.repaint();
            }
        });
    }
}
