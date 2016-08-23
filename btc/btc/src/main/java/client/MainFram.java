package client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by weipeng on 16/8/10.
 */
public class MainFram extends JFrame {

    private JLabel etcValue = new JLabel("");

    private JLabel btcValue = new JLabel("");
    private JLabel ethValue = new JLabel("");

    public MainFram(String title) throws HeadlessException {
        super(title);
        this.setSize(850, 80);
        createPane();
    }

    private void createPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());

        this.add(panel);

        JLabel btcLab = new JLabel("BTC");
        panel.add(btcLab);

        panel.add(btcValue);
        JLabel ethLab = new JLabel("ETH");
        panel.add(ethLab);

        panel.add(ethValue);


        JLabel etcLab = new JLabel("ETC");
        panel.add(etcLab);

        panel.add(etcValue);
    }

    public JLabel getEtcValue() {
        return etcValue;
    }

    public JLabel getBtcValue() {
        return btcValue;
    }

    public JLabel getEthValue() {
        return ethValue;
    }
}
