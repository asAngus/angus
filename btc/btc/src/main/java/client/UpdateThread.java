package client;

import client.vo.BtVo;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by weipeng on 16/8/11.
 */
public class UpdateThread extends Thread {
    private MainFram fram;
    private BtVo btc;
    private BtVo etc;
    private BtVo eth;

    public UpdateThread(MainFram fram) {
        this.fram = fram;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BtVo btcPrice = BTCUtils.getBtcPrice(BtVo.class);
            BtVo ethPrice = BTCUtils.getEthPrice(BtVo.class);
            BtVo etcPrice = BTCUtils.getEtcPrice(BtVo.class);
            String btcValueStr = btcPrice.getCurrentPrice() + "";
            if (btc != null) {
                btcPrice.setUp(false);
                if (btc.getCurrentPrice() == btcPrice.getCurrentPrice()) {
                    fram.getBtcValue().setBackground(fram.getBtcValue().getBackground());
                } else if (btc.getCurrentPrice() < btcPrice.getCurrentPrice()) {
                    btcPrice.setUp(true);
                    fram.getBtcValue().setForeground(Color.RED);
                } else {
                    fram.getBtcValue().setForeground(Color.BLUE);
                }
                btcValueStr = "P:" + btc.getCurrentPrice() + " L:" + btcPrice.getCurrentPrice() + "";
            }
            btc = btcPrice;
            String etcValueStr = etcPrice.getCurrentPrice() + "";
            if (etc != null) {
                etcPrice.setUp(false);

                if (etc.getCurrentPrice() == etcPrice.getCurrentPrice()) {

                    fram.getEtcValue().setBackground(fram.getEtcValue().getBackground());
                } else if (etc.getCurrentPrice() < etcPrice.getCurrentPrice()) {
                    etcPrice.setUp(true);
                    fram.getEtcValue().setForeground(Color.RED);
                } else {
                    fram.getEtcValue().setForeground(Color.BLUE);
                }
                etcValueStr = "P:" + etc.getCurrentPrice() + " L:" + etcPrice.getCurrentPrice() + "";
            }
            etc = etcPrice;
            String ethValueStr = ethPrice.getCurrentPrice() + "";
            if (eth != null) {
                ethPrice.setUp(false);
                if (eth.getCurrentPrice() == ethPrice.getCurrentPrice()) {
                    fram.getEthValue().setBackground(fram.getEthValue().getBackground());
                } else if (eth.getCurrentPrice() < ethPrice.getCurrentPrice()) {
                    ethPrice.setUp(true);
                    fram.getEthValue().setForeground(Color.RED);
                } else {
                    fram.getEthValue().setForeground(Color.BLUE);
                }
                ethValueStr = "P:" + eth.getCurrentPrice() + " L:" + ethPrice.getCurrentPrice() + "";
            }
            eth = ethPrice;
            //panel.updateUI();
            fram.getEtcValue().setText(etcValueStr);
            fram.getBtcValue().setText(btcValueStr);
            fram.getEthValue().setText(ethValueStr);
        }
    }
}
