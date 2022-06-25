import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

public class PinChange extends JFrame implements ActionListener {
    JTextField newPin;
    JButton back, changePin;
    String pin;
    PinChange(String pin) {
        this.pin = pin;

        setLayout(null);
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(350,10);
        setTitle("Pin Change");
        getContentPane().setBackground(Color.WHITE);

        //heading
        JLabel heading = new JLabel("Enter the new pin number");
        heading.setBounds(400,30,600,50);
        heading.setFont(new Font("", Font.BOLD,34));
        add(heading);

        //textField
        newPin = new JTextField();
        newPin.setBounds(350,120,500,50);
        add(newPin);

        //Change Pin Button
        changePin = new JButton("Change Pin");
        changePin.setForeground(Color.WHITE);
        changePin.setBackground(Color.BLACK);
        changePin.setBounds(350,200,200,50);
        add(changePin);
        changePin.addActionListener(this);

        //back button
        back = new JButton("Back");
        back.setBounds(600,200,200,50);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        add(back);
        back.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == back){
            setVisible(false);
            new Transactions(pin).setVisible(true);
        }
        else if(e.getSource() == changePin){
            String change = newPin.getText();
            Conn con = new Conn();
            String query = "update deposit set pin ='" + change + "'where pin='"+ pin +"'";
            String query1 ="update login_table set pin ='" + change + "'where pin='"+ pin +"'";
            String query2 ="update signup3 set pin ='" + change + "'where pin='"+ pin +"'";
            String query3 = "update balance set pin ='" + change + "'where pin='"+ pin +"'";

            try {
                Statement stmt = con.c.createStatement();
                stmt.executeUpdate(query);
                stmt.executeUpdate(query1);
                stmt.executeUpdate(query2);
                stmt.executeUpdate(query3);
                stmt.close();
                con.c.close();

                JOptionPane.showMessageDialog(null,"PIN changed successfully");
                setVisible(false);
                new Transactions(change).setVisible(true);
            }
            catch (Exception exception) {
                System.out.println(exception);
            }

        }
    }
}
