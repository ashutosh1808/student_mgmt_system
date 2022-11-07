import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class ViewFrame extends JFrame{
Container c;
TextArea taData;
JButton btnBack;

ViewFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());
taData=new TextArea(10,30);
btnBack=new JButton("Back");
taData.setEditable(false);
StringBuffer data=new StringBuffer();
try{
	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kc9july22","root","abc456");
	String sql="select * from student";	
	Statement stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery(sql);	
	while(rs.next()){
		int rno=rs.getInt(1);
		String name=rs.getString(2);
		data.append("rno= "+rno+" name= "+name+"\n");
	}
	taData.setText(data.toString());
	con.close();
}catch(SQLException e){
	JOptionPane.showMessageDialog(c,"Issue: "+e);
}
c.add(taData);
c.add(btnBack);

ActionListener a1=(ae)->{
MainFrame m=new MainFrame();
dispose();
};
btnBack.addActionListener(a1);


setTitle("View Students");
setSize(400,400);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setVisible(true);
}
}