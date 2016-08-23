package client;

/**
 * Created by weipeng on 16/8/10.
 */
public class Main {
    public static void main(String[] args) {
        MainFram main = new MainFram("BTC");
        main.setVisible(true);
        main.getBtcValue();
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append("");
        StringBuffer append = sb.append("");


        main.getBtcValue();

        UpdateThread updateThread = new UpdateThread(main);
        updateThread.run();
    }

}
