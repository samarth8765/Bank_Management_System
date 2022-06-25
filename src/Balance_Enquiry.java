import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Balance_Enquiry extends JFrame implements ActionListener {
        String pin;
        Conn con = new Conn();
        JButton back;
        Balance_Enquiry(String pin) throws SQLException {
            this.pin = pin;

            setLayout(null);
            setSize(1920,1080);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocation(350,10);
            setTitle("Transaction Window");
            getContentPane().setBackground(Color.WHITE);

            JLabel heading = new JLabel("Your Current Balance is :");
            heading.setBounds(500,30,600,50);
            heading.setFont(new Font("", Font.BOLD,34));
            add(heading);

            String query = String.format("select balance from balance where pin = %s",pin);
            Statement stmt1 = con.c.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            rs.next();

            String amount = "Your current balance is Rs. " + rs.getInt("balance");

            JLabel showBalance = new JLabel(amount);
            showBalance.setBounds(400,150,1200,50);
            showBalance.setFont(new Font ("", Font.BOLD,38));
            add(showBalance);
            setVisible(true);

            //back button
            back = new JButton("Back");
            back.setBounds(600,300,200,50);
            back.setForeground(Color.WHITE);
            back.setBackground(Color.BLACK);
            add(back);
            back.addActionListener(this);

        }

        public void actionPerformed(ActionEvent e){
            if(e.getSource() == back ){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }



}
