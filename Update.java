import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Update extends JFrame{

Container u;
JLabel lblRno;
JLabel lblName;
JLabel lblMarks;
JTextField txtRno;
JTextField txtName;
JTextField txtMarks;
JButton btnSave;
JButton btnBack;

Update()
{
u = getContentPane();
u.setLayout(new FlowLayout());
lblRno = new JLabel("Enter Rno");
txtRno = new JTextField(15);
lblName = new JLabel("Enter Name");
txtName = new JTextField(15);
lblMarks = new JLabel("Enter Marks");
txtMarks = new JTextField(15);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

u.add(lblRno);
u.add(txtRno);
u.add(lblName);
u.add(txtName);
u.add(lblMarks);
u.add(txtMarks);
u.add(btnSave);
u.add(btnBack);

btnSave.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory factory = cfg.buildSessionFactory();
Session session = null;
Transaction t = null;	


try {
session = factory.openSession();
t = session.beginTransaction();
int rno = Integer.parseInt(txtRno.getText());
String name = txtName.getText();
int marks = Integer.parseInt(txtMarks.getText());

student s = (student)session.get(student.class, rno);

if(s != null) {


if(name.length() < 2)  {
	txtName.setText("");
	txtName.grabFocus();
	throw new NonsenseException("Name should not consits < 2 letters ");
	}

if(name.chars().allMatch(Character::isLetter) == false ) {
		txtName.setText("");
		txtName.grabFocus();
		throw new NonsenseException("Name should not be consiting digit or special character");
	}
	

if(marks<0 || marks>100 ) {
	txtMarks.setText("");
	txtMarks.grabFocus();
	throw new NonsenseException("Marks should be in 0-100");

	
}
s.setName(name);
s.setMarks(marks);
session.save(s);
t.commit();
JOptionPane.showMessageDialog(new JDialog(),"Record Updated");
txtRno.setText("");
txtName.setText("");
txtMarks.setText("");
txtRno.grabFocus();
}

else {
JOptionPane.showMessageDialog(new JDialog(),"Rno doesn't exists!!");
txtRno.setText("");
txtName.setText("");
txtMarks.setText("");
txtRno.grabFocus();
}


}

catch(NonsenseException ne) {
JOptionPane.showMessageDialog(new JDialog(),ne.msg);	
}

catch(NumberFormatException nfe) {
if(txtRno.getText().chars().allMatch(Character::isLetter) == true || txtRno.getText().isEmpty()) {
txtRno.setText("");
txtRno.grabFocus();
JOptionPane.showMessageDialog(new JDialog(),"Roll Number can only be INTEGER and can't be empty");
}
if(txtMarks.getText().chars().allMatch(Character::isLetter) == true || txtMarks.getText().isEmpty()) {
txtMarks.setText("");
txtMarks.grabFocus();
JOptionPane.showMessageDialog(new JDialog(),"Marks can only be INTEGER and can't be empty");
}
}

catch(Exception e){
t.rollback();
JOptionPane.showMessageDialog(new JDialog(),e);	
}

finally
{
session.close();
}//end of finally

}
});

btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
Main m = new Main();
dispose();
}
});


setTitle("Update Student");
setSize(200,250);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setVisible(true);
}

}