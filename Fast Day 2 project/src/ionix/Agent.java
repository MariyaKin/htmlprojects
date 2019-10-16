package ionix;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Agent {
public int total_cost=0;
public void menudriven()
{

//System.out.println("agent");



ConnectionManager conM=new ConnectionManager();
JButton subBtn=new JButton("Submit");
JFrame agentf=new JFrame("home");
//agentf.dispose();

JButton buy=new JButton("Buy/Sell");
buy.setBounds(100, 50, 100, 30);
agentf.add(buy);

JButton show=new JButton("Show history");
show.setBounds(200, 50, 100, 30);
agentf.add(show);

JButton log=new JButton("Logout");
log.setBounds(300, 50, 100, 30);
agentf.add(log);

log.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
agentf.dispose();
try {
Login.main(null);
} catch (IOException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}


}
});

show.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent arg0) {

DefaultTableModel model = new DefaultTableModel();
   //Container cnt = this.getContentPane();
 
   
ConnectionManager conM=new ConnectionManager();
try
{

Connection c=conM.connection();
Statement stmnt=c.createStatement();
ResultSet res=stmnt.executeQuery("Select * from admin,agent where agent.product_id=admin.product_id and agent.username='"+Login.username+"'");
//String[][] str=new String[30][30];
JLabel lab=new JLabel("History");
lab.setBounds(350, 100, 500, 200);
agentf.getContentPane().add(lab);

JTable jtbl = new JTable(model);
jtbl.setBounds(200, 250, 500, 200);
agentf.getContentPane().add(jtbl);

model.addColumn("Product Id");
model.addColumn("Product Name");
model.addColumn("Transaction");
model.addColumn("Quantity");
model.addColumn("Price");
model.addColumn("Total cost");
jtbl.setModel(model);
while(res.next())
{
int i=0;
//for(int j=0;j<)
model.addRow(new Object[] {res.getString("agent.product_id"),res.getString("product_name"),res.getString("transaction"),res.getString("quantity"),res.getString("price"),res.getString("total_price")});
}
// agentf.add(jtbl);

}
catch (Exception ex) {
// TODO: handle exception
}

}
});

buy.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub

JLabel pdId,pdtrans,pname,price,quant,pdMob,addpd;

addpd=new JLabel("Transaction");
addpd.setBounds(150, 100, 200, 30);
agentf.add(addpd);
pdId=new JLabel("Product Id");
pdId.setBounds(50, 150, 200, 30);
agentf.add(pdId);
JComboBox idTxt=new JComboBox();
idTxt.addItem("Select...");

JLabel totalTxt=new JLabel();
totalTxt.setBounds(300,350, 100, 30);
agentf.add(totalTxt);

try
{
Connection c=conM.connection();
Statement stmnt=c.createStatement();
ResultSet res=stmnt.executeQuery("Select product_id from admin");
while(res.next())
{
idTxt.addItem(res.getString("product_id"));
}
}
catch (Exception ex) {
// TODO: handle exception
}
idTxt.setBounds(300,150, 100, 30);
agentf.add(idTxt);

pname=new JLabel("Product Name");
pname.setBounds(450, 150, 200, 30);
agentf.add(pname);

price=new JLabel("Price");
price.setBounds(550, 150, 200, 30);
agentf.add(price);

idTxt.addItemListener(new ItemListener() {

@Override
public void itemStateChanged(ItemEvent e) {
try
{
Connection c=conM.connection();
Statement stmnt=c.createStatement();
//String pid=idTxt.getSelectedItem();
ResultSet res=stmnt.executeQuery("Select product_name,price from admin where product_id='"+idTxt.getSelectedItem()+"'");

while(res.next())
{
pname.setText(res.getString("product_name"));
price.setText(res.getString("price"));
}
}
catch (Exception ex) {
// TODO: handle exception
}

}
});
pdtrans=new JLabel("Transaction");
pdtrans.setBounds(50, 200, 200, 30);
agentf.add(pdtrans);
JComboBox trans=new JComboBox();
trans.setBounds(300,200, 100, 30);
trans.addItem("Buy");
trans.addItem("Sell");
agentf.add(trans);


quant=new JLabel("Quantity");
quant.setBounds(50, 250, 200, 30);
agentf.add(quant);
JTextField quantTxt=new JTextField();
quantTxt.setBounds(300,250, 100, 30);
agentf.add(quantTxt);

quantTxt.addKeyListener(new KeyListener() {

@Override
public void keyTyped(KeyEvent e) {
// TODO Auto-generated method stub

}

@Override
public void keyReleased(KeyEvent e) {
// TODO Auto-generated method stub
int min_quant=0;
int q=0;
int quant=Integer.parseInt(quantTxt.getText());
try
{
Connection c=conM.connection();
Statement stmnt=c.createStatement();
ResultSet res=stmnt.executeQuery("Select min_sell_quantity, quantity from admin where product_id='"+idTxt.getSelectedItem()+"'");

while(res.next())
{
min_quant=Integer.parseInt(res.getString("min_sell_quantity"));
q=Integer.parseInt(res.getString("quantity"));
}
if((quant>=min_quant)&&(quant<=q))
{


//int user_quant=0;
//user_quant=Integer.parseInt(quantTxt.getText());
int amt=0;
amt=Integer.parseInt(price.getText());

total_cost=quant*amt;
totalTxt.setText(Integer.toString(total_cost));

//subBtn.setEnabled("true");
}
else
{
JOptionPane.showMessageDialog(agentf, "You have to choose minimum "+min_quant+" quantity or maximum "+q+" quantity ");
quantTxt.setText("");
//subBtn.setEnabled("true");
}
}
catch (Exception ex) {
// TODO: handle exception
}
}

@Override
public void keyPressed(KeyEvent e) {
// TODO Auto-generated method stub

}
});


pdMob=new JLabel("Mob No.");
pdMob.setBounds(50, 300, 200, 30);
agentf.add(pdMob);
JTextField mobTxt=new JTextField();
mobTxt.setBounds(300,300, 100, 30);
agentf.add(mobTxt);

JLabel totalL=new JLabel("Total Cost");
totalL.setBounds(50, 350, 200, 30);
agentf.add(totalL);

subBtn.setBounds(150, 450, 100, 30);
agentf.add(subBtn);

subBtn.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent arg0) {


try
{
Connection c=conM.connection();
Statement stmnt=c.createStatement();
System.out.println("insert into agent(product_id,username,password,transaction,total_price,quantity,mob_No) values('"+idTxt.getSelectedItem()+"','"+Login.username+"','"+Login.password+"','"+trans.getSelectedItem()+"','"+total_cost+"','"+quantTxt.getText()+"','"+mobTxt.getText()+"')");
int res=stmnt.executeUpdate("insert into agent(product_id,username,password,transaction,total_price,quantity,mob_No) values('"+idTxt.getSelectedItem()+"','"+Login.username+"','"+Login.password+"','"+trans.getSelectedItem()+"','"+total_cost+"','"+quantTxt.getText()+"','"+mobTxt.getText()+"')");
if(res>0)
{
JOptionPane.showMessageDialog(agentf, "Transaction made successfully ");

int total_quant=0;
ResultSet resq=stmnt.executeQuery("select quantity from admin where product_id='"+idTxt.getSelectedItem()+"'");
while(resq.next())
{
total_quant=Integer.parseInt(resq.getString("quantity"));
//q=Integer.parseInt(res.getString("quantity"));
}

int avail_quant=total_quant-(Integer.parseInt(quantTxt.getText()));
System.out.println("update admin set quantity='"+total_quant+"' where product_id='"+idTxt.getSelectedItem()+"'");
int resup=stmnt.executeUpdate("update admin set quantity='"+avail_quant+"' where product_id='"+idTxt.getSelectedItem()+"'");

if(resup>0)
{
System.out.println("Updated successfully");
}
else
{
System.out.println("Updation failed");
}
}
else
{
JOptionPane.showMessageDialog(agentf, "Transaction failed ");
}
}
catch (Exception ex) {
// TODO: handle exception
}

}
});
}
});


agentf.setDefaultCloseOperation(agentf.EXIT_ON_CLOSE);
agentf.setLayout(null);
agentf.setSize(600, 1000);
agentf.setVisible(true);

}
}

