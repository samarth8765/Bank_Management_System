import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class SignUp extends JFrame implements ActionListener{
    int random_int;
    JTextField nameTextField,fnameTextField,emailTextField,addressTextField,cityTextField,stateTextField,pinTextField;
    JButton nextBtn;
    JDateChooser setDate;
    JRadioButton male,female,married,unmarried,other;
    SignUp(){
        //setting out the signIn page
        setLayout(null);
        setSize(1920,1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(350,10);
        getContentPane().setBackground(Color.WHITE);

        //creating random 4 digit number
        int max = 9999;
        int min = 1000;
        random_int = (int)(Math.random() * (max - min + 1) + min);


        //heading
        JLabel heading = new JLabel("Application Form No. "+random_int);
        heading.setFont(new Font("",1,38));
        heading.setBounds(450,20,500,50);
        add(heading);

        JLabel page1 = new JLabel("Page-1: Personal Details...");
        page1.setFont(new Font("",1,22));
        page1.setBounds(550,70,400,50);
        add(page1);

        //name
        JLabel name = new JLabel("Name :");
        name.setFont(new Font("",1,20));
        name.setBounds(350,140,400,50);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway",0,14));
        nameTextField.setBounds(520,150,500,30);
        add(nameTextField);

        //fathers Name
        JLabel fatherName = new JLabel("Father's name :");
        fatherName.setFont(new Font("",1,20));
        fatherName.setBounds(350,180,400,50);
        add(fatherName);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway",0,14));
        fnameTextField.setBounds(520,190,500,30);
        add(fnameTextField);

        //Date of Birth
        JLabel DOB = new JLabel("DOB :");
        DOB.setFont(new Font("",1,20));
        DOB.setBounds(350,220,400,50);
        add(DOB);

        setDate = new JDateChooser();
        setDate.setBounds(520,230,400,30);
        add(setDate);

        //Gender
        JLabel Gender = new JLabel("Gender :");
        Gender.setFont(new Font("",1,20));
        Gender.setBounds(350,260,400,50);
        add(Gender);

        male = new JRadioButton("Male");
        male.setBounds(520,270,70,30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(650,270,70,30);
        female.setBackground(Color.WHITE);
        add(female);

        //grouping the buttons so that they one radio btn selects at a time
        ButtonGroup btngroup = new ButtonGroup();
        btngroup.add(male);
        btngroup.add(female);

        //Email
        JLabel email = new JLabel("Email :");
        email.setFont(new Font("",1,20));
        email.setBounds(350,300,400,50);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway",0,14));
        emailTextField.setBounds(520,310,500,30);
        add(emailTextField);

        //Marital Status
        JLabel marital = new JLabel("Marital Status :");
        marital.setFont(new Font("",1,20));
        marital.setBounds(350,340 ,400,50);
        add(marital);

        //married
        married = new JRadioButton("Married");
        married.setBounds(520,350,70,30);
        married.setBackground(Color.WHITE);
        add(married);

        //unmarried
        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(650,350,100,30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);

        //other
        other = new JRadioButton("Other");
        other.setBounds(820,350,70,30);
        other.setBackground(Color.WHITE);
        add(other);

        //grouping the buttons so that they one radio btn selects at a time
        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(other);


        //address
        JLabel address = new JLabel("Address :");
        address.setFont(new Font("",1,20));
        address.setBounds(350,380,400,50);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway",0,14));
        addressTextField.setBounds(520,390,500,30);
        add(addressTextField);

        //city
        JLabel city = new JLabel("City :");
        city.setFont(new Font("",1,20));
        city.setBounds(350,420 ,400,50);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway",0,14));
        cityTextField.setBounds(520,430,500,30);
        add(cityTextField);

        //state
        JLabel state = new JLabel("State :");
        state.setFont(new Font("",1,20));
        state.setBounds(350,460,400,50);
        add(state);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Raleway",0,14));
        stateTextField.setBounds(520,470,500,30);
        add(stateTextField);

        //pincode
        JLabel pincode = new JLabel("Pin Code :");
        pincode.setFont(new Font("",1,20));
        pincode.setBounds(350,500,400,50);
        add(pincode);

        pinTextField = new JTextField();
        pinTextField.setFont(new Font("Raleway",0,14));
        pinTextField.setBounds(520,510,500,30);
        add(pinTextField);

        //Next Button
        nextBtn = new JButton("Next");
        nextBtn.setBackground(Color.BLACK);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setBounds(950,560,70,30);
        add(nextBtn);
        nextBtn.addActionListener(this);
        setVisible(true);
    }
    //Action listener
    //Storing values in database when next is clicked
    public void actionPerformed(ActionEvent e){
        int form_num = random_int;
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        //JDateChooser to sql date
        java.util.Date date = setDate.getDate();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String gender = null;
        if(male.isSelected()){
            gender = "male";
        }else{
            gender = "female";
        }
        String email = emailTextField.getText();
        String marital = null;
        if(married.isSelected()){
            marital = "married";
        }else if(unmarried.isSelected()){
            marital = "unmarried";
        }else if(other.isSelected()){
            marital = "other";
        }
        String address = addressTextField.getText();
        String state = stateTextField.getText();
        String city = cityTextField.getText();
        String pincode = pinTextField.getText();

        try{
            Conn con = new Conn();
            String query = "insert into signUp values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.c.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setString(2,fname);
            stmt.setDate(3,sqlDate);
            stmt.setString(4,gender);
            stmt.setString(5,email);
            stmt.setString(6,marital);
            stmt.setString(7,address);
            stmt.setString(8,city);
            stmt.setString(9,state);
            stmt.setString(10,pincode);
            stmt.setInt(11,form_num);

            stmt.execute();
            stmt.close();
            con.c.close();
            setVisible(false);
            new SignUp2(form_num).setVisible(true);
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }
}
