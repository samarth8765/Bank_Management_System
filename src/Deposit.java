import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    JFormattedTextField amount;
    JButton deposit,back;
    String pin;
    Deposit(String pin){
        this.pin = pin;

        //setting up the screen
        setLayout(null);
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(350,10);
        getContentPane().setBackground(Color.WHITE);

        //heading
        JLabel heading = new JLabel("Enter the amount you want to deposit");
        heading.setFont(new Font("",1,22));
        heading.setBounds(350,30,400,50);
        add(heading);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);

        amount =  new JFormattedTextField(formatter);
        amount.setBounds(350,120,500,50);
        add(amount);


        deposit = new JButton("Deposit");
        deposit.setForeground(Color.WHITE);
        deposit.setBackground(Color.BLACK);
        deposit.setBounds(350,200,200,50);
        add(deposit);
        deposit.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(600,200,200,50);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        add(back);
        back.addActionListener(this);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == deposit){
            int amount_ =  (int) amount.getValue();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            if(amount_ == 0){
                JOptionPane.showMessageDialog(null,"Enter Amount First");
            }else{
                Conn con = new Conn();
                String query = "Insert into deposit values (?,?,?,?)";
                String query2 = String.format("select balance from balance where pin = %s",pin);

                try {
                    PreparedStatement stmt = con.c.prepareStatement(query);
                    stmt.setString(1,pin);
                    stmt.setDate(2,sqlDate);
                    stmt.setString(3,"Deposit");
                    stmt.setInt(4,amount_);
                    stmt.execute();
                    JOptionPane.showMessageDialog(null, "Rs "+amount_ + " Deposited");

                    Statement stmt1 = con.c.createStatement();
                    ResultSet rs = stmt1.executeQuery(query2);
                    rs.next();
                    int newBalance = rs.getInt("balance") + amount_;

                    String query3 = "update balance set balance ='" + newBalance + "'where pin='"+ pin +"'";
                    stmt1.executeUpdate(query3);
                    stmt.close();
                    stmt1.close();
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                } catch (SQLException exception) {
                    System.out.println(exception);
                }
            }


        }
        else if(e.getSource() == back){
            setVisible(false);
            new Transactions(pin).setVisible(true);
        }

    }
}
