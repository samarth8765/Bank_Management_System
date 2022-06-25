import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;


public class Login extends JFrame implements ActionListener {
    JButton signIn,signUp;
    JPasswordField pinTextField;
    JTextField cardTextField;

    Login(){
        //to get custom layout
        setLayout(null);

        //to set title of the frame
        setTitle("ATM");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //to set the size of the frame
        setSize(1920,1080);


        //to set color of the frame
        getContentPane().setBackground(new Color(255,255,255));

        //we can use Jlabel class to write anything on the frame
        //heading
        JLabel heading = new JLabel("Welcome to the ATM");
        heading.setBounds(470,200,500,50);
        heading.setFont(new Font("", Font.BOLD,38));
        add(heading);

        //cardNumber
        JLabel cardNum = new JLabel("Card Number : ");
        cardNum.setBounds(400,300,200,50);
        cardNum.setFont(new Font("Courier", 0,24));
        add(cardNum);

        //textfield
        cardTextField = new JTextField();
        cardTextField.setBounds(580,310,300,30);
        add(cardTextField);

        //Pin
        JLabel pin = new JLabel("Pin : ");
        pin.setBounds(450,350,500,50);
        pin.setFont(new Font("Courier", 0,24));
        add(pin);

        //textfield
        pinTextField = new JPasswordField();
        pinTextField.setBounds(580,360,300,30);
        add(pinTextField);

        //Buttons for sign in, sign up, clear
        signIn = new JButton("SIGN IN");
        signIn.setBounds(580,400,300,30);
        signIn.setBackground(Color.BLACK);
        signIn.setForeground(Color.WHITE);
        signIn.addActionListener(this);
        add(signIn);

        //signUp
        signUp = new JButton("SIGN UP");
        signUp.setBounds(580,440,300,30);
        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.WHITE);
        signUp.addActionListener(this);
        add(signUp);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signIn){
            Conn con = new Conn();
            String cardNum = cardTextField.getText();
            String pin = pinTextField.getText();
            String query = "select * from login_table where Card_Num ='" + cardNum + "' and pin = '" + pin+"'";
            try {
                Statement stmt = con.c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
        else if(e.getSource() == signUp){
            setVisible(false);
            new SignUp().setVisible(true);
        }
    }


}
