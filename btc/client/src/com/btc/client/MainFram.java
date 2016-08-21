package com.btc.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by weipeng on 16/8/10.
 */
public class MainFram extends JFrame {


    public MainFram(String title) throws HeadlessException {
        super(title);
        this.setSize(200, 80);
        createPane();
    }

    private void createPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        String ethInfo = HttpUtils.getEthInfo();


        this.add(panel);

        Label btcLab = new Label("BTC");
        panel.add(btcLab);
        Label ethLab = new Label("ETH");
        panel.add(ethLab);
        Label etcLab = new Label("ETC");
        panel.add(etcLab);

    }


}
