import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Transactions extends JFrame implements ActionListener {
    JButton deposit,withdrawal,fastCash,miniStatement,pinChange,balance,exit;
    String pin;
    Transactions(String pin){
        this.pin = pin;

        //setting up the screen
        setLayout(null);
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(350,10);
        setTitle("Transaction Window");
        getContentPane().setBackground(Color.WHITE);

        //heading
        JLabel heading = new JLabel("Please select your Transaction");
        heading.setBounds(500,30,600,50);
        heading.setFont(new Font("", Font.BOLD,34));
        add(heading);

        deposit = new JButton("Deposit");
        deposit.setBounds(350,120,300,50);
        deposit.setBackground(Color.BLACK);
        deposit.setForeground(Color.WHITE);
        deposit.addActionListener(this);
        add(deposit);

        withdrawal = new JButton("Cash Withdrawal");
        withdrawal.setBounds(750,120,400,50);
        withdrawal.setBackground(Color.BLACK);
        withdrawal.setForeground(Color.WHITE);
        add(withdrawal);
        withdrawal.addActionListener(this);

        fastCash = new JButton("Fast Cash");
        fastCash.setBounds(350,210,300,50);
        fastCash.setBackground(Color.BLACK);
        fastCash.setForeground(Color.WHITE);
        add(fastCash);

        miniStatement = new JButton("Mini Statement");
        miniStatement.setBounds(750,210,400,50);
        miniStatement.setBackground(Color.BLACK);
        miniStatement.setForeground(Color.WHITE);
        add(miniStatement);
        miniStatement.addActionListener(this);

        pinChange = new JButton("Pin Change");
        pinChange.setBounds(350,300,300,50);
        pinChange.setBackground(Color.BLACK);
        pinChange.setForeground(Color.WHITE);
        add(pinChange);
        pinChange.addActionListener(this);

        balance = new JButton("Balance Enquiry");
        balance.setBounds(750,300,400,50);
        balance.setBackground(Color.BLACK);
        balance.setForeground(Color.WHITE);
        add(balance);
        balance.addActionListener(this);

        exit = new JButton("Exit");
        exit.setBounds(350,390,800,50);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        add(exit);
        exit.addActionListener(this);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == exit){
            System.exit(0);
        }
        else if(e.getSource() == deposit){
            setVisible(false);
            new Deposit(pin).setVisible(true);
        }
        else if(e.getSource() == withdrawal){
            setVisible(false);
            new Withdrawal(pin).setVisible(true);
        }else if(e.getSource() == pinChange){
            setVisible(false);
            new PinChange(pin).setVisible(true);
        }
        else if(e.getSource() == balance){
            setVisible(false);
            try {
                new Balance_Enquiry(pin).setVisible(true);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        else if(e.getSource() == miniStatement){
//            setVisible(false);
//            new MiniStatement().setVisible(true);
        }
    }


}
