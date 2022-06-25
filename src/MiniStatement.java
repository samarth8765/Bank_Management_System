import javax.swing.*;
import java.awt.*;

public class MiniStatement extends JFrame {
        MiniStatement(){
            setLayout(null);
            setSize(400,600);
            setLocation(20,20);
            setTitle("Mini Statement");
            getContentPane().setBackground(Color.WHITE);

            JLabel text = new JLabel();

            JLabel bank = new JLabel("JAVA Bank");
            bank.setBounds(150,20,100,20);
            add(bank);

            setVisible(true);

        }
}
