package ionix;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Login {
public static String username;
public static String password;
public static void main(String[] args) throws IOException
{
JFrame f=new JFrame("Login");
f.dispose();

JButton adminLogin=new JButton("Admin login");
adminLogin.setBounds(50, 100, 100, 60);
f.add(adminLogin);

JButton agentLogin=new JButton("Agent login");
agentLogin.setBounds(200, 100, 100, 60);
f.add(agentLogin);

JButton btExit=new JButton("Exit");
btExit.setBounds(350, 100, 100, 60);
f.add(btExit);
btExit.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {
f.dispose();
System.exit(0);

}
});

agentLogin.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {
f.dispose();

JFrame fLogin=new JFrame("Agent Login");
fLogin.dispose();

JButton btLogin=new JButton("Login");
JLabel uname=new JLabel("UserName");
uname.setBounds(50, 100, 100, 30);
fLogin.add(uname);
JTextField uTxt=new JTextField();
uTxt.setBounds(200, 100, 200, 30);
fLogin.add(uTxt);

JLabel pass=new JLabel("Password");
pass.setBounds(50, 150, 100, 30);
fLogin.add(pass);
JPasswordField passTxt=new JPasswordField();
passTxt.setBounds(200, 150, 200, 30);
fLogin.add(passTxt);

btLogin.setBounds(200, 200, 150, 30);
fLogin.add(btLogin);
btLogin.addActionListener(new ActionListener() {


public void actionPerformed(ActionEvent e) {

ConnectionManager conM=new ConnectionManager();
try {
Connection c=conM.connection();
Statement stmnt=c.createStatement();

username=uTxt.getText();
password=passTxt.getText();

ResultSet res=stmnt.executeQuery("select username,password from agent where username='"+username+"' and password='"+password+"'");

//System.out.println("select username,password from agent where username='"+username+"' and password='"+password+"'");
if(res.next())
{
JOptionPane.showMessageDialog(fLogin, "Successfull login");
fLogin.dispose();
Agent ag=new Agent();
ag.menudriven();

}
else
{
JOptionPane.showMessageDialog(fLogin, "Login failed! please try again");
uTxt.setText("");
passTxt.setText("");
}
}
catch (Exception ex) {
// TODO: handle exception
}

}
});
fLogin.setDefaultCloseOperation(fLogin.EXIT_ON_CLOSE);
fLogin.setLayout(null);
fLogin.setSize(600, 400);
fLogin.setVisible(true);
}
});
adminLogin.addActionListener(new ActionListener() {


public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub

f.dispose();
JFrame fLogin=new JFrame("Admin Login");
JButton btLogin=new JButton("Login");
JLabel uname=new JLabel("UserName");
uname.setBounds(50, 100, 100, 30);
fLogin.add(uname);
JTextField uTxt=new JTextField();
uTxt.setBounds(200, 100, 200, 30);
fLogin.add(uTxt);

JLabel pass=new JLabel("Password");
pass.setBounds(50, 150, 100, 30);
fLogin.add(pass);
JPasswordField passTxt=new JPasswordField();
passTxt.setBounds(200, 150, 200, 30);
fLogin.add(passTxt);

btLogin.setBounds(200, 200, 150, 30);
fLogin.add(btLogin);
btLogin.addActionListener(new ActionListener() {


public void actionPerformed(ActionEvent e) {
ConnectionManager conM=new ConnectionManager();
try {
Connection c=conM.connection();
Statement stmnt=c.createStatement();

username=uTxt.getText();
password=passTxt.getText();
ResultSet res=stmnt.executeQuery("select username,password from admin where username='"+username+"' and password='"+password+"'");
if(res.next())
{
JOptionPane.showMessageDialog(fLogin, "Successfull login");
fLogin.dispose();
Admin ad=new Admin();
ad.menuDriven();


}
else
{
JOptionPane.showMessageDialog(fLogin, "Login failed! please try again");
uTxt.setText("");
passTxt.setText("");
}
}
catch (Exception ex) {
// TODO: handle exception
}

}
});
if(Admin.dis==0) {
fLogin.dispose();
}
fLogin.setDefaultCloseOperation(fLogin.DISPOSE_ON_CLOSE);
fLogin.setLayout(null);
fLogin.setSize(600, 400);
fLogin.setVisible(true);
}
});

f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
f.setLayout(null);
f.setSize(600, 400);
f.setVisible(true);
}
}

