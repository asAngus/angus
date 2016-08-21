package com.btc.weipeng.btc.thread;


import android.app.Activity;
import android.widget.TextView;

import com.btc.weipeng.btc.R;
import com.btc.weipeng.btc.utils.BTCUtils;
import com.btc.weipeng.btc.vo.BtVo;

/**
 * Created by weipeng on 16/8/11.
 */
public class UpdateThread implements Runnable {

    private BtVo btc;
    private BtVo etc;
    private BtVo eth;
    private Activity activity;

    public UpdateThread(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final TextView btcValue = (TextView) activity.findViewById(R.id.btc_value_id);
            final TextView etcValue = (TextView) activity.findViewById(R.id.etc_value_id);
            final TextView ethValue = (TextView) activity.findViewById(R.id.eth_value_id);

            new Thread() {
                @Override
                public void run() {
                    BtVo btcPrice = BTCUtils.getBtcPrice(BtVo.class);
                    BtVo ethPrice = BTCUtils.getEthPrice(BtVo.class);
                    BtVo etcPrice = BTCUtils.getEtcPrice(BtVo.class);
                    String btcValueStr = btcPrice.getCurrentPrice() + "";
                    if (btc != null) {
                        btcPrice.setUp(false);
                        if (btc.getCurrentPrice() == btcPrice.getCurrentPrice()) {
//                    fram.getBtcValue().setBackground(fram.getBtcValue().getBackground());
                        } else if (btc.getCurrentPrice() < btcPrice.getCurrentPrice()) {
                            btcPrice.setUp(true);
//                    fram.getBtcValue().setForeground(Color.RED);
                        } else {
//                    fram.getBtcValue().setForeground(Color.BLUE);
                        }
                        btcValueStr = "P:" + btc.getCurrentPrice() + " L:" + btcPrice.getCurrentPrice() + "";
                    }
                    btc = btcPrice;
                    String etcValueStr = etcPrice.getCurrentPrice() + "";
                    if (etc != null) {
                        etcPrice.setUp(false);

                        if (etc.getCurrentPrice() == etcPrice.getCurrentPrice()) {

//                    fram.getEtcValue().setBackground(fram.getEtcValue().getBackground());
                        } else if (etc.getCurrentPrice() < etcPrice.getCurrentPrice()) {
                            etcPrice.setUp(true);
//                    fram.getEtcValue().setForeground(Color.RED);
                        } else {
//                    fram.getEtcValue().setForeground(Color.BLUE);
                        }
                        etcValueStr = "P:" + etc.getCurrentPrice() + " L:" + etcPrice.getCurrentPrice() + "";
                    }
                    etc = etcPrice;
                    String ethValueStr = ethPrice.getCurrentPrice() + "";
                    if (eth != null) {
                        ethPrice.setUp(false);
                        if (eth.getCurrentPrice() == ethPrice.getCurrentPrice()) {
//                    fram.getEthValue().setBackground(fram.getEthValue().getBackground());
                        } else if (eth.getCurrentPrice() < ethPrice.getCurrentPrice()) {
                            ethPrice.setUp(true);
//                    fram.getEthValue().setForeground(Color.RED);
                        } else {
//                    fram.getEthValue().setForeground(Color.BLUE);
                        }
                        ethValueStr = "P:" + eth.getCurrentPrice() + " L:" + ethPrice.getCurrentPrice() + "";
                    }
                    eth = ethPrice;
                    final String finalBtcValueStr = btcValueStr;
                    final String finalEthValueStr = ethValueStr;
                    final String finalEtcValueStr = etcValueStr;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btcValue.setText(finalBtcValueStr);
                            ethValue.setText(finalEthValueStr);
                            etcValue.setText(finalEtcValueStr);
                        }
                    });

                }
            }.start();

        }
    }
}
