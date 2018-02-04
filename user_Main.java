import java.util.*;
import java.util.ArrayList;
import java.util.List; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

class user_Main extends JFrame implements ActionListener{

  JPanel panel;
  CardLayout layout;

  public static int a_hands[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public static int b_hands[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public static int a_field[] = {0, 0, 0, 0, 0, 0};
  public static int b_field[] = {0, 0, 0, 0, 0, 0};
  
  public static int a_stateH[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public static int b_stateH[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public static int a_stateF[] = {0, 0, 0, 0, 0, 0};
  public static int b_stateF[] = {0, 0, 0, 0, 0, 0};
  
  public static int a = 0;
  public static int b = 0;

  public static int a_hp = 6;
  public static int b_hp = 6;
  
  public static JLabel tennokoe = new JLabel("ooo");

  public static ImageIcon[] type = new ImageIcon[4];

  public static ImageIcon[] stateW = new ImageIcon[7];
  public static ImageIcon[] stateM = new ImageIcon[7];

  public static JButton[] aField = new JButton[6];
  public static JButton[] bField = new JButton[6];
  public static JButton[] aHands = new JButton[10];
  public static JButton[] bHands = new JButton[10];
    
  public static JButton playerA = new JButton();
  public static JButton playerB = new JButton();
  
  public static JButton putally = new JButton("味方を配置");
  public static JButton attenem = new JButton("敵を攻撃");
  public static JButton turnend = new JButton("ターンを終了");
  public static JButton reset = new JButton("取り消し");

  public static void main(String args[]){
    user_Main frame = new user_Main();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(200, 200, 800, 600);
    frame.setTitle("タイトル");
    frame.setVisible(true);
    tennokoe.setText("こんにちわ");
    /* 手札の設定 */
    for(int i = 0; i < 10; i++){
      int a = (int)(Math.random() * 3 + 1);
      int b = (int)(Math.random() * 3 + 1);
      aHands[i].setIcon(type[a]);
      a_stateH[i] = a;
      bHands[i].setIcon(type[b]);
      b_stateH[i] = b;
    }
    boolean hand = false;
    int id = 0;
    while(a_hp != 0 && b_hp != 0){
      for(int i = 0; i < 10; i++){
        if(a_hands[i] == 1){
          aHands[i].setIcon(type[0]);
          hand = true;
          id = i;
          break;
        }if(b_hands[i] == 1){
          bHands[i].setIcon(type[0]);
          hand = true;
          id = i;
          break;
        }
      }
      if(hand == true){
        for(int i = 0; i < 6; i++){
          if(a_field[i] == 1){
            aField[i].setIcon(type[a_stateH[id]]);
            break;
          }
          if(a_field[i] == 1){
            aField[i].setIcon(type[a_stateH[id]]);
            break;
          }
        }
      }
    }
  }

  user_Main(){

    tennokoe.setFont(new Font("MS ゴシック", Font.BOLD, 20));
    
    type[0] = new ImageIcon("./none.png");
    type[1] = new ImageIcon("./rock.png");
    type[2] = new ImageIcon("./paper.png");
    type[3] = new ImageIcon("./scissors.png");
    
    stateW[0] = new ImageIcon("./w0.png");
    stateW[1] = new ImageIcon("./w1.png");
    stateW[2] = new ImageIcon("./w2.png");
    stateW[3] = new ImageIcon("./w3.png");
    stateW[4] = new ImageIcon("./w4.png");
    stateW[5] = new ImageIcon("./w5.png");
    stateW[6] = new ImageIcon("./w6.png");
    stateM[0] = new ImageIcon("./m0.png");
    stateM[1] = new ImageIcon("./m1.png");
    stateM[2] = new ImageIcon("./m2.png");
    stateM[3] = new ImageIcon("./m3.png");
    stateM[4] = new ImageIcon("./m4.png");
    stateM[5] = new ImageIcon("./m5.png");
    stateM[6] = new ImageIcon("./m6.png");
    
    for(int i = 0; i < 10; i++){
      aHands[i] = new JButton(type[0]);
      aHands[i].addActionListener(this);
      aHands[i].setBackground(Color.RED);
      bHands[i] = new JButton(type[0]);
      bHands[i].addActionListener(this);
      bHands[i].setBackground(Color.BLUE);
    }
    
    for(int i = 0; i < 6; i++){
      aField[i] = new JButton(type[0]);
      aField[i].addActionListener(this);
      bField[i] = new JButton(type[0]);
      bField[i].addActionListener(this);
    }
   
    playerA.setIcon(stateM[6]);
    playerA.addActionListener(this);
    playerB.setIcon(stateW[6]);
    playerB.addActionListener(this);
    
    reset.addActionListener(this);
    turnend.addActionListener(this);
  
    /* 対戦ページ */
    JPanel pA = new JPanel();
    pA.setLayout(new GridLayout(3, 2));
    for(int i = 0; i < 6; i++)
      pA.add(aField[i]);

    JPanel pB = new JPanel();
    pB.setLayout(new GridLayout(3, 2));
    for(int i = 0; i < 6; i++)
      pB.add(bField[i]);

    JPanel hands = new JPanel();
    hands.setLayout(new GridLayout(2, 10));
    for(int i = 0; i < 5; i++)
      hands.add(aHands[i]);
    for(int i = 0; i < 5; i++)
      hands.add(bHands[i]);
    for(int i = 5; i < 10; i++)
      hands.add(aHands[i]);
    for(int i = 5; i < 10; i++)
      hands.add(bHands[i]);

    JPanel operate = new JPanel();
    operate.setLayout(new FlowLayout());
    operate.add(reset);
    operate.add(turnend);

    JPanel card1 = new JPanel();
    card1.setLayout(new GridLayout(0, 4));
    card1.add(playerA, 0);
    card1.add(pA, 1);
    card1.add(pB, 2);
    card1.add(playerB, 3);

    JPanel all = new JPanel();
    all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
    all.add(tennokoe);
    all.add(card1);
    all.add(hands);
    all.add(operate);

    /* CardLayout準備 */
    this.panel = new JPanel();
    this.layout = new CardLayout();//CardLayoutの作成
    this.panel.setLayout(this.layout);
    this.panel.add(all, "View1");
    
    getContentPane().add(this.panel, BorderLayout.CENTER);
  }
  public void actionPerformed(ActionEvent e) {
    // TODO 自動生成されたメソッド・スタブ
    String cmd = e.getActionCommand();
    layout.show(this.panel,cmd);
    JButton btn = null;
    if(e.getSource() instanceof JButton){
      btn = (JButton)e.getSource();
      for(int i = 0; i < 6; i++){
        a_field[i] = 0;
        b_field[i] = 0;
      }
      for(int i = 0; i < 10; i++){
        a_hands[i] = 0;
        b_hands[i] = 0;
      }
      a = 0;
      b = 0;
    }
    for(int i = 0; i < 6; i++){
      if(btn == aField[i]){
        a_field[i] = 1;
        break;
      }
      if(btn == bField[i]){
        b_field[i] = 1;
        break;
      }
    }
    for(int i = 0; i < 10; i++){
      if(btn == aHands[i]){
        a_hands[i] = 1;
        break;
      }
      if(btn == bHands[i]){
        b_hands[i] = 1;
        break;
      }
    }
    if(btn == playerA) a = 1;
    else if(btn == playerB) b = 1;
  }
}
