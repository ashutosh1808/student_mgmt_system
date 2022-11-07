import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class AddFrame extends JFrame{
Container c;
JLabel labRno,labName;
JTextField txtRno,txtName;
JButton btnSubmit, btnBack;

AddFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());
labRno=new JLabel("enter rno");
txtRno=new JTextField(30);
labName=new JLabel("enter name");
txtName=new JTextField(30);
btnSubmit=new JButton("Submit");
btnBack=new JButton("Back");

ActionListener a1=(ae)->{
	int rno=Integer.parseInt(txtRno.getText());
	String name=txtName.getText();
	try{
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/kc9july22";
		String un="root";
		String pw="abc456";
		Connection con=DriverManager.getConnection(url,un,pw);
		String sql="insert into student values(?,?)";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1,rno);
		pst.setString(2,name);
		pst.executeUpdate();
		JOptionPane.showMessageDialog(c,"student added");
		con.close();
	}catch(SQLException e){
		JOptionPane.showMessageDialog(c,"issue: "+e);
	}
};
btnSubmit.addActionListener(a1);

ActionListener a2=(ae)->{
MainFrame m=new MainFrame();
dispose();
};
btnBack.addActionListener(a2);
c.add(labRno);
c.add(txtRno);
c.add(labName);
c.add(txtName);
c.add(btnSubmit);
c.add(btnBack);
setTitle("Add Student");
setSize(400,400);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setVisible(true);
}
}