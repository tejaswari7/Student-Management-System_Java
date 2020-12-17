import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Delete extends JFrame{

Container d;
JLabel lblRno;
JTextField txtRno;
JButton btnSave;
JButton btnBack;



Delete()
{
d = getContentPane();
d.setLayout(new FlowLayout());
lblRno = new JLabel("Enter Rno");
txtRno = new JTextField(15);
btnSave = new JButton("Save");
btnBack = new JButton("Back");

d.add(lblRno);
d.add(txtRno);
d.add(btnSave);
d.add(btnBack);

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
student s = (student)session.get(student.class,rno);
if(s!=null) {
session.delete(s);
t.commit();
JOptionPane.showMessageDialog(new JDialog(),"Record Deleted");
txtRno.setText("");
txtRno.grabFocus();
}
else {
JOptionPane.showMessageDialog(new JDialog(),"Roll No doesn't exists");
txtRno.setText("");
txtRno.grabFocus();
}
}

catch(NumberFormatException nfe) {
if(txtRno.getText().chars().allMatch(Character::isLetter) == true || txtRno.getText().isEmpty()) {
txtRno.setText("");
//txtRno.requestFocus();
txtRno.grabFocus();
JOptionPane.showMessageDialog(new JDialog(),"Roll Number can only be INTEGER and can't be empty");
}
}

catch(Exception e){
JOptionPane.showMessageDialog(new JDialog(),e);	
t.rollback();
}

finally
{
session.close();
}

}
});

btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
Main m = new Main();
dispose();
}
});


setTitle("Delete Student");
setSize(200,200);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setVisible(true);
}

}