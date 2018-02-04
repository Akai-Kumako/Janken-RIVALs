import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

public class Screen extends JFrame implements ActionListener{
	JPanel p1,p2;
	JButton b1,b2,b3;
	public static void main(String[] args){
		Screen h = new Screen();
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		h.setBounds(200, 200,800,400);
		h.setVisible(true);
  }
  Screen(){
    SpringLayout layout = new SpringLayout();
		setLayout(layout);
		p1 = new JPanel();
		p2 = new JPanel();
    p1.setPreferredSize(new Dimension(800, 400));
    p2.setPreferredSize(new Dimension(800, 400));
    p1.setLayout(layout);
    p2.setLayout(layout);
    b1 = new JButton("START");
    b1.setActionCommand("b1");
  	layout.putConstraint(SpringLayout.NORTH, b1, 300, SpringLayout.NORTH,p1);
	  layout.putConstraint(SpringLayout.WEST, b1, 400, SpringLayout.WEST,p1);
    p1.add(b1);
  }
}
