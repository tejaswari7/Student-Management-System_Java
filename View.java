import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class View extends JFrame{

Container v;
JTextField view;
JButton btnBack;
public JTextArea textarea = new JTextArea(10,20);
JScrollPane sp = new JScrollPane(textarea);

View()
{
v = getContentPane();
v.setLayout(new FlowLayout());
btnBack = new JButton("Back");

v.add(sp);
v.add(btnBack);


Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");
SessionFactory factory = cfg.buildSessionFactory();
Session session = null;
Transaction t = null;

try {	
session = factory.openSession();
t = session.beginTransaction();		
textarea.setEditable(false);	
java.util.List<student> s = new ArrayList<>();
s = session.createQuery("from student ORDER BY rno ASC").list();

textarea.append("Rno" + "\t" + "Name" + "\t" +"Marks" +"\n");
for (student m : s)
textarea.append(m.getRno() + "\t" + m.getName() + "\t" +m.getMarks() + "\n");

}
catch(Exception e){
t.rollback();
JOptionPane.showMessageDialog(new JDialog(),e);	
}
finally
{
session.close();
}

btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
Main m = new Main();
dispose();
}
});


setTitle("View Student");
setSize(250,250);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setVisible(true);
}

}