import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Rivals extends JPanel implements ActionListener{
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JLabel l,la,lb;
    JLabel[] l1 = new JLabel[10];
    JLabel[] l2 = new JLabel[10];
    JButton b1,b2,b3;

    public Rivals(){
      int[] a = new int[10];
      int[] b = new int[10];
      ImageIcon icon1 = new ImageIcon("rock.jpg");
      ImageIcon icon2 = new ImageIcon("Scissors.jpg");
      ImageIcon icon3 = new ImageIcon("Paper.jpg");

      for(int j = 0; j < 2; j++){
        if(j == 0){
          for(int i = 0; i < 10; i++){
            Random rnd = new Random();
            int ran = rnd.nextInt(3) + 1;
          if(ran == 1)
            a[i] = 1;
          else if(ran == 2)
            a[i] = 2;
          else
            a[i] = 3;
          }
        }
        else{
          for(int i = 0; i < 10; i++){
            Random rnd = new Random();
            int ran = rnd.nextInt(3) + 1;
          if(ran == 1)
            b[i] = 1;
          else if(ran == 2)
            b[i] = 2;
          else
            b[i] = 3;
          }
        }
      }
      la = new JLabel("先攻のカード");
      lb = new JLabel("後攻のカード");
      p1.add(la);
      p2.add(lb);
	for(int i = 0; i < 10; i++){
    if(a[i] == 1){
	    l1[i] = new JLabel(icon1);
    }else if(a[i] == 2){
      l1[i] = new JLabel(icon2);
    }else if(a[i] == 3){
      l1[i] = new JLabel(icon3);
    }

    if(b[i] == 1){
      l2[i] = new JLabel(icon1);
    }else if(b[i] == 2){
      l2[i] = new JLabel(icon2);
    }else if(b[i] == 3){
      l2[i] = new JLabel(icon3);
    }
	    p1.add(l1[i]);
	    p2.add(l2[i]);
	}
	l = new JLabel("じゃんけんライバルズ");
	b1 = new JButton("ゲームスタート");
	b2 = new JButton("Next");
	b3 = new JButton("Start");

  b1.addActionListener(this);
  b2.addActionListener(this);
  b3.addActionListener(this);

	p1.add(b2);
	p2.add(b3);

	setLayout(new BorderLayout());
	add(l,BorderLayout.NORTH);
	add(b1,BorderLayout.SOUTH);
	add(p1,BorderLayout.WEST);
  add(p2,BorderLayout.EAST);
	p1.setVisible(false);
  p2.setVisible(false);
	b1.setVisible(true);
}

public void actionPerformed(ActionEvent e){
  Object obj = e.getSource();
  if(obj == b1){
    b1.setVisible(false);
    p1.setVisible(true);
  }
  else if(obj == b2){
    p1.setVisible(false);
    p2.setVisible(true);
  }
  else if(obj == b3){
    p2.setVisible(false);
  }
}

public static void main(String[] args){
    JFrame frame = new JFrame("Rivals");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Rivals r = new Rivals();
    frame.add(r,BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
}
}
