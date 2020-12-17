import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Main extends JFrame {
Container c;
JButton btnAdd;
JButton btnView;
JButton btnUpdate;
JButton btnDelete;


Main()
{
c = getContentPane();
c.setLayout(new FlowLayout());
btnAdd = new JButton("Add");
btnView = new JButton("View");
btnUpdate = new JButton("Update");
btnDelete = new JButton("Delete");

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);

/*btnAdd.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
Add add = new Add();
dispose();
}
});

btnView.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
View view = new View();
dispose();
}
});*/

ActionListener a = (ae) -> {
Add add = new Add();
dispose();
};
btnAdd.addActionListener(a);

ActionListener a1 = (ae) -> {
View view = new View();
dispose();
};
btnView.addActionListener(a1);

ActionListener a2 = (ae) -> {
Update up = new Update();
dispose();
};
btnUpdate.addActionListener(a2);

ActionListener a3 = (ae) -> {
Delete delete = new Delete();
dispose();
};
btnDelete.addActionListener(a3);

/*btnUpdate.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
Update up = new Update();
dispose();
}
});

btnDelete.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
Delete delete = new Delete();
dispose();
}
});*/

setTitle("SMS");
setSize(100,200);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
public static void main(String args[])  {
Main m = new Main();
}
}
