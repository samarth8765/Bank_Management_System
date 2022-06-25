import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignUp2 extends JFrame implements ActionListener {
    int form_num;
    JTextField adhaarTextField,panTextField;
    JButton nextBtn;
    JRadioButton statusYes,statusNo,seniorYes,seniorNo;
    JComboBox religionCombo,categoryCombo,eduCombo,occupationCombo,incomeCombo;
    SignUp2(int form_num){
        //Inter-relating signUp and signUp2 page with formNum
        this.form_num = form_num;
        //setting out the signIn page
        setLayout(null);
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(350,10);
        getContentPane().setBackground(Color.WHITE);

        //heading
        JLabel page1 = new JLabel("Page-2: Additional Details...");
        page1.setFont(new Font("",1,22));
        page1.setBounds(570,30,400,50);
        add(page1);

        //Religion
        JLabel religion = new JLabel("Religion :");
        religion.setFont(new Font("",1,20));
        religion.setBounds(350,140,400,50);
        add(religion);

        String[] varReligion = {"Hindu","Muslim","Sikh","Christian","Other"};
        religionCombo = new JComboBox(varReligion);
        religionCombo.setFont(new Font("Raleway",0,14));
        religionCombo.setBounds(540,150,500,30);
        religionCombo.setBackground(Color.WHITE);
        add(religionCombo);


        //Category
        JLabel category = new JLabel("Category :");
        category.setFont(new Font("",1,20));
        category.setBounds(350,190,400,50);
        add(category);

        String [] Category = {"General", "OBC", "SC", "ST","Other"};
        categoryCombo = new JComboBox(Category);
        categoryCombo.setFont(new Font("Raleway",0,14));
        categoryCombo.setBounds(540,200,500,30);
        categoryCombo.setBackground(Color.WHITE);
        add(categoryCombo);

        //Income
        JLabel income = new JLabel("Income :");
        income.setFont(new Font("",1,20));
        income.setBounds(350,240,400,50);
        add(income);

        String [] Income = {"Null", "<1,500,00", "<5,000,00", "<10,000,00",">10,000,00"};
        incomeCombo = new JComboBox(Income);
        incomeCombo.setFont(new Font("Raleway",0,14));
        incomeCombo.setBounds(540,250,500,30);
        incomeCombo.setBackground(Color.WHITE);
        add(incomeCombo);

        //Education
        JLabel edu = new JLabel("Education :");
        edu.setFont(new Font("",1,20));
        edu.setBounds(350,290,400,50);
        add(edu);

        String [] Education = {"Graduated", "School", "Post-Graduated", "Doctorate","Other"};
        eduCombo = new JComboBox(Education);
        eduCombo.setFont(new Font("Raleway",0,14));
        eduCombo.setBounds(540,300,500,30);
        eduCombo.setBackground(Color.WHITE);
        add(eduCombo);


        //Occupation
        JLabel occupation = new JLabel("Occupation :");
        occupation.setFont(new Font("",1,20));
        occupation.setBounds(350,340,400,50);
        add(occupation);

        String [] Occupation = {"Salaried", "Self-employed", "Business", "Student","Other"};
        occupationCombo = new JComboBox(Occupation);
        occupationCombo.setFont(new Font("Raleway",0,14));
        occupationCombo.setBounds(540,350,500,30);
        occupationCombo.setBackground(Color.WHITE);
        add(occupationCombo);


        //PAN Number
        JLabel pan_num = new JLabel("PAN Number :");
        pan_num.setFont(new Font("",1,20));
        pan_num.setBounds(350,390 ,400,50);
        add(pan_num);

        panTextField = new JTextField();
        panTextField.setFont(new Font("Raleway",0,14));
        panTextField.setBounds(540,400,500,30);
        add(panTextField);


        //address
        JLabel adhaar_num = new JLabel("Adhaar Number :");
        adhaar_num.setFont(new Font("",1,20));
        adhaar_num.setBounds(350,440,400,50);
        add(adhaar_num);

        adhaarTextField = new JTextField();
        adhaarTextField.setFont(new Font("Raleway",0,14));
        adhaarTextField.setBounds(540,450,500,30);
        add(adhaarTextField);

        //SeniorCitizen
        JLabel seniorCitizen = new JLabel("Senior Citizen:");
        seniorCitizen.setFont(new Font("",1,20));
        seniorCitizen.setBounds(350,490 ,400,50);
        add(seniorCitizen);

        seniorYes = new JRadioButton("Yes");
        seniorYes.setBounds(540,500,70,30);
        seniorYes.setBackground(Color.WHITE);
        add(seniorYes);

        seniorNo = new JRadioButton("No");
        seniorNo.setBounds(650,500,70,30);
        seniorNo.setBackground(Color.WHITE);
        add(seniorNo);

        //grouping the buttons so that they one radio btn selects at a time
        ButtonGroup btngroup = new ButtonGroup();
        btngroup.add(seniorYes);
        btngroup.add(seniorNo);


        //status
        JLabel status = new JLabel("Existing Account :");
        status.setFont(new Font("",1,20));
        status.setBounds(350,540,400,50);
        add(status);

        statusYes = new JRadioButton("Yes");
        statusYes.setBounds(540,550,70,30);
        statusYes.setBackground(Color.WHITE);
        add(statusYes);

        statusNo = new JRadioButton("No");
        statusNo.setBounds(650,550,70,30);
        statusNo.setBackground(Color.WHITE);
        add(statusNo);

        //grouping the buttons so that they one radio btn selects at a time
        ButtonGroup btngroup1 = new ButtonGroup();
        btngroup1.add(statusYes);
        btngroup1.add(statusNo);


        //Next Button
        nextBtn = new JButton("Next");
        nextBtn.setBackground(Color.BLACK);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setBounds(1050,580,100,40);
        add(nextBtn);
        nextBtn.addActionListener(this);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        //getting info to store it into the database
        String religion = (String)religionCombo.getSelectedItem();
        String category = (String)categoryCombo.getSelectedItem();
        String income = (String)incomeCombo.getSelectedItem();
        String edu = (String)eduCombo.getSelectedItem();
        String occupation = (String)occupationCombo.getSelectedItem();
        String pan = panTextField.getText();
        String adhaar = panTextField.getText();
        String senior = null;
        if(seniorYes.isSelected()){
            senior = "Yes";
        }
        else if(seniorNo.isSelected()){
            senior = "No";
        }

        String status = null;
        if(statusYes.isSelected()){
            status = "Yes";
        }
        else if(statusNo.isSelected()){
            status = "No";
        }

        try{
            //Database
            Conn con = new Conn();
            String query = "insert into signup2 values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.c.prepareStatement(query);
            stmt.setInt(1,form_num);
            stmt.setString(2,religion);
            stmt.setString(3,category);
            stmt.setString(4,income);
            stmt.setString(5,edu);
            stmt.setString(6,occupation);
            stmt.setString(7,pan);
            stmt.setString(8,adhaar);
            stmt.setString(9,senior);
            stmt.setString(10,status);

            stmt.execute();
            stmt.close();
            con.c.close();
            System.out.println("Successfully");
            setVisible(false);
            new SignUp3(form_num).setVisible(true);

        }
        catch(Exception exception){
            System.out.println(exception);
        }


    }
}
