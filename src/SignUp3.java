import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

public class SignUp3 extends JFrame implements ActionListener {
    int f_num;
    JRadioButton saving, fixDeposit, current, recurringDeposit;
    JButton submit, cancel, goBackToHomePage;
    JCheckBox atm, internetBanking, mobileBanking, emailSms, checkBook, eStatement;

    SignUp3(int f_num) {
        this.f_num = f_num;
        //Screen
        setLayout(null);
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Page-3");

        //heading
        JLabel heading = new JLabel("Page-3: Account Details");
        heading.setBounds(480, 30, 400, 50);
        heading.setFont(new Font("", 1, 26));
        add(heading);

        //Account Type
        JLabel accountType = new JLabel("Account Type:");
        accountType.setBounds(350, 140, 400, 50);
        accountType.setFont(new Font("", 1, 24));
        add(accountType);

        //Radio Buttons
        saving = new JRadioButton("Saving Account");
        saving.setBounds(355, 200, 200, 20);
        saving.setFont(new Font("", 1, 15));
        saving.setBackground(Color.WHITE);
        add(saving);

        fixDeposit = new JRadioButton("Fixed Deposit Account");
        fixDeposit.setBounds(660, 200, 200, 20);
        fixDeposit.setFont(new Font("", 1, 15));
        fixDeposit.setBackground(Color.WHITE);
        add(fixDeposit);

        current = new JRadioButton("Current Account");
        current.setBounds(355, 230, 200, 20);
        current.setFont(new Font("", 1, 15));
        current.setBackground(Color.WHITE);
        add(current);

        recurringDeposit = new JRadioButton("Recurring Deposit Account");
        recurringDeposit.setBounds(660, 230, 300, 20);
        recurringDeposit.setFont(new Font("", 1, 15));
        recurringDeposit.setBackground(Color.WHITE);
        add(recurringDeposit);

        ButtonGroup btngroup = new ButtonGroup();
        btngroup.add(saving);
        btngroup.add(current);
        btngroup.add(recurringDeposit);
        btngroup.add(fixDeposit);

        //Card Number
        JLabel cardNum = new JLabel("Card Number           XXXX-XXXX-XXXX-1234");
        cardNum.setBounds(350, 300, 600, 30);
        cardNum.setFont(new Font("", 1, 22));
        add(cardNum);

        //Pin
        JLabel pin = new JLabel("PIN:                           XXXX");
        pin.setBounds(350, 330, 600, 30);
        pin.setFont(new Font("", 1, 22));
        add(pin);

        //Services Required
        JLabel services = new JLabel("Services Required:");
        services.setBounds(350, 390, 500, 34);
        services.setFont(new Font("", 1, 34));
        add(services);

        atm = new JCheckBox("ATM Card");
        atm.setBounds(350, 430, 300, 20);
        atm.setFont(new Font("", 0, 16));
        atm.setBackground(Color.WHITE);
        add(atm);

        internetBanking = new JCheckBox("Internet Banking");
        internetBanking.setBounds(350, 470, 300, 20);
        internetBanking.setFont(new Font("", 0, 16));
        internetBanking.setBackground(Color.WHITE);
        add(internetBanking);

        mobileBanking = new JCheckBox("Mobile Banking");
        mobileBanking.setBounds(700, 430, 300, 20);
        mobileBanking.setBackground(Color.WHITE);
        mobileBanking.setFont(new Font("", 0, 16));
        add(mobileBanking);

        emailSms = new JCheckBox("Email & SMS");
        emailSms.setBounds(700, 470, 300, 20);
        emailSms.setBackground(Color.WHITE);
        emailSms.setFont(new Font("", 0, 16));
        add(emailSms);

        checkBook = new JCheckBox("Check Book");
        checkBook.setBounds(350, 510, 300, 20);
        checkBook.setFont(new Font("", 0, 16));
        checkBook.setBackground(Color.WHITE);
        add(checkBook);

        eStatement = new JCheckBox("E-Statement");
        eStatement.setBounds(700, 510, 300, 20);
        eStatement.setFont(new Font("", 0, 16));
        eStatement.setBackground(Color.WHITE);
        add(eStatement);

        //Buttons for submit and Cancel

        submit = new JButton("Submit");
        submit.setBounds(450, 560, 100, 40);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        add(submit);
        submit.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBounds(650, 560, 100, 40);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        goBackToHomePage = new JButton("Home Page");
        goBackToHomePage.setBounds(900, 30, 200, 40);
        goBackToHomePage.setBackground(Color.BLACK);
        goBackToHomePage.setForeground(Color.WHITE);
        goBackToHomePage.addActionListener(this);
        add(goBackToHomePage);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String account = null;
            if (saving.isSelected()) {
                account = "Saving";
            } else if (fixDeposit.isSelected()) {
                account = "fixDeposit";
            } else if (current.isSelected()) {
                account = "Current";
            } else if (recurringDeposit.isSelected()) {
                account = "Recurring Deposit";
            }
            //creating random numbers for card and pin
            Random rd = new Random();
            String cardNum = "" + Math.abs((rd.nextLong() % 90000000L)+ 5040936000000000L);
            String pin = "" + Math.abs((rd.nextLong() % 9000L) + 1000L);
            String facility = "";
            if (atm.isSelected()) {
                facility += " ATM Card ";
            }
            if (internetBanking.isSelected()) {
                facility += " Internet Banking ";
            }
            if (mobileBanking.isSelected()) {
                facility += " Mobile Banking ";
            }
            if (emailSms.isSelected()) {
                facility += " Email & SMS ";
            }
            if (checkBook.isSelected()) {
                facility += " Check Book ";
            }
            if (eStatement.isSelected()) {
                facility += " E-Statement ";
            }
            try {
                Conn con = new Conn();
                //for signUp3 table
                String query = "insert into signup3 values (?,?,?,?,?)";
                PreparedStatement stmt = con.c.prepareStatement(query);
                stmt.setInt(1, f_num);
                stmt.setString(2, account);
                stmt.setString(3, cardNum);
                stmt.setString(4, pin);
                stmt.setString(5, facility);
                stmt.execute();
                stmt.close();

                //for Login_table
                String query2 = "insert into login_table values(?,?,?)";
                PreparedStatement stmt1 = con.c.prepareStatement(query2);
                stmt1.setInt(1, f_num);
                stmt1.setString(2, cardNum);
                stmt1.setString(3, pin);
                stmt1.execute();
                stmt1.close();

                //creating an entry in balance table
                String query3 = "insert into balance values(?,?)";
                PreparedStatement stmt2 = con.c.prepareStatement(query3);
                stmt2.setString(1,pin);
                stmt2.setInt(2,0);
                stmt2.execute();
                stmt2.close();

                con.c.close();

                JOptionPane.showMessageDialog(null, "Card Number-> " + cardNum + "\n" + "PIN-> " + pin);
                setVisible(false);
                new Deposit(pin).setVisible(true);

                System.out.println("Successful");
            } catch (Exception exception) {
                System.out.println("Unsuccessful");
                System.out.println(exception);
            }
        }
        else if(e.getSource() == cancel){
            try {
                Conn con = new Conn();
                String deleteFromSignUp = String.format("DELETE from signUp WHERE App_Num = %s", f_num);
                String deleteFromSignUp2 = String.format("DELETE from signUp2 WHERE Form_Num = %s", f_num);
                Statement s1 = con.c.createStatement();
                Statement s2 = con.c.createStatement();
                s1.executeUpdate(deleteFromSignUp);
                s2.executeUpdate(deleteFromSignUp2);
                s1.close();
                s2.close();
                con.c.close();
            }catch(Exception exception){
                System.out.println(exception);
            }
            System.exit(0);
        }
        else if(e.getSource() == goBackToHomePage){
            setVisible(false);
            new Login().setVisible(true);
        }
    }
}
