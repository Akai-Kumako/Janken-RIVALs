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

  /* どのボタンが押されているか */
  public static int a_hands[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public static int b_hands[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public static int a_field[] = {0, 0, 0, 0, 0, 0};
  public static int b_field[] = {0, 0, 0, 0, 0, 0};
  
  /* 現在どういうステータスか(0: none, 1: rock, 2: paper, 3:scissors) */
  public static int a_stateH[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public static int b_stateH[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public static int a_stateF[] = {0, 0, 0, 0, 0, 0};
  public static int b_stateF[] = {0, 0, 0, 0, 0, 0};
 
  /* プレイヤーボタンが押されているか */
  public static int a = 0;
  public static int b = 0;

  /* プレイヤーのHP */
  public static int a_hp = 6;
  public static int b_hp = 6;
  
  /* 操作ボタンが押されているか */
  public static int ope[] = {0, 0, 0};

  /* 天の声とターン数の表示ラベル */
  public static JLabel tennokoe = new JLabel();
  public static JLabel turn = new JLabel();

  /* じゃんけんの手のイメージアイコン */
  public static ImageIcon[] type = new ImageIcon[4];

  /* プレイヤーのステータスイメージアイコン(W0-W6, M0-M6) */
  public static ImageIcon[] stateW = new ImageIcon[7];
  public static ImageIcon[] stateM = new ImageIcon[7];

  /* 画面にボタンを設置する */
  public static JButton[] aField = new JButton[6];
  public static JButton[] bField = new JButton[6];
  public static JButton[] aHands = new JButton[10];
  public static JButton[] bHands = new JButton[10];
    
  public static JButton playerA = new JButton();
  public static JButton playerB = new JButton();
  
  public static JButton putally = new JButton("味方を配置");
  public static JButton attenem = new JButton("敵を攻撃");
  public static JButton reset = new JButton("取り消し");

  public static void main(String args[]){
    user_Main frame = new user_Main();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(200, 200, 800, 600);
    frame.setTitle("JankenRIVALs");
    frame.setVisible(true);
    /* 手札の設定のフェーズ */
    for(int i = 0; i < 10; i++){
      int a = (int)(Math.random() * 3 + 1);
      int b = (int)(Math.random() * 3 + 1);
      aHands[i].setIcon(type[a]);
      a_stateH[i] = a;
      bHands[i].setIcon(type[b]);
      b_stateH[i] = b;
    }
    /* 実際に戦うフェーズ */
    /* noa は 攻撃可能回数 */
    int noa = 1;
    while(a_hp != 0 && b_hp != 0){
      turn.setText("先攻のターン (行動回数: " + noa + ")");
      tennokoe.setText("次の行動を選択してください");
      p1Turn(noa);
      turn.setText("後攻のターン (行動回数: " + noa + ")");
      tennokoe.setText("次の行動を選択してください");
      p2Turn(noa);
      noa++;
    }
    /* 成績発表のフェーズ */
  }

  public static void p1Turn(int noa){
    int c = noa;
    while(c != 0){
      int com = -1;
      while(com == -1){
        System.out.print("");
        for(int i = 0; i < 3; i++){
          if(ope[i] == 1){
            com = i;
            ope[i] = 0;
            break;
          }
        }
      }
      if(com == 0){
        int ally = -1;
        int ally_p = -1;
        int field = -1;
        while(ally == -1){
          for(int i = 0; i < 10; i++){
            if(a_hands[i] == 1){
              ally = a_stateH[i];
              ally_p = i;
              aHands[i].setIcon(type[0]);
              a_stateH[i] = 0;
              a_hands[i] = 0;
              break;
            }
          }
        }
        while(field == -1){
          for(int i = 0; i < 6; i++){
            if(a_field[i] == 1){
              field = a_stateF[i];
              if(field == 0){
                a_field[i] = 0;
                aField[i].setIcon(type[ally]);
                a_stateF[i] = ally;
                break;
              }else{
                tennokoe.setText("配置不可能です");
                aHands[ally_p].setIcon(type[ally]);
                a_stateH[i] = ally;
              }
            }
          }
        }
      }
      else if(com == 1){
        int field = -1;
        int field_p = -1;
        int enem = -1;
        int enem_p = -1;
        while(field == -1){
          for(int i = 0; i < 6; i++){
            if(a_field[i] == 1){
              field = a_stateF[i];
              if(field != 0){
                a_field[i] = 0;
                field_p = i;
                break;
              }else{
                tennokoe.setText("味方が配置されていません");
              }
            }
          }
        }
        while(enem == -1){
          for(int i = 0; i < 6; i++){
            if(b_field[i] == 1){
              enem = b_stateF[i];
              if(enem != 0){
                b_field[i] = 0;
                enem_p = i;
                break;
              }else{
                tennokoe.setText("敵が配置されていません");
              }
            }
          }
          if(b == 1){
            b = 0;
            b_hp--;
            playerB.setIcon(stateW[b_hp]);
            break;
          }
        }
        if(field - enem == 1 || field - enem == -2){
          bField[enem_p].setIcon(type[0]);
        }
      }
      c = c - 1;
    }
  }

  public static void p2Turn(int noa){
    int c = noa;
    while(c != 0){
      int com = -1;
      while(com == -1){
        System.out.print("");
        for(int i = 0; i < 3; i++){
          if(ope[i] == 1){
            com = i;
            ope[i] = 0;
            break;
          }
        }
      }
      if(com == 0){
        int ally = -1;
        int ally_p = -1;
        int field = -1;
        while(ally == -1){
          for(int i = 0; i < 10; i++){
            if(b_hands[i] == 1){
              ally = b_stateH[i];
              ally_p = i;
              bHands[i].setIcon(type[0]);
              b_stateH[i] = 0;
              b_hands[i] = 0;
              break;
            }
          }
        }
        while(field == -1){
          for(int i = 0; i < 6; i++){
            if(b_field[i] == 1){
              field = b_stateF[i];
              if(field == 0){
                b_field[i] = 0;
                bField[i].setIcon(type[ally]);
                b_stateF[i] = ally;
                break;
              }else{
                tennokoe.setText("配置不可能です");
                bHands[ally_p].setIcon(type[ally]);
                b_stateH[i] = ally;
              }
            }
          }
        }
      }
      else if(com == 1){
        int field = -1;
        int field_p = -1;
        int enem = -1;
        int enem_p = -1;
        while(field == -1){
          for(int i = 0; i < 6; i++){
            if(b_field[i] == 1){
              field = b_stateF[i];
              if(field != 0){
                b_field[i] = 0;
                field_p = i;
                break;
              }else{
                tennokoe.setText("味方が配置されていません");
              }
            }
          }
        }
        while(enem == -1){
          for(int i = 0; i < 6; i++){
            if(a_field[i] == 1){
              enem = a_stateF[i];
              if(enem != 0){
                a_field[i] = 0;
                enem_p = i;
                break;
              }else{
                tennokoe.setText("敵が配置されていません");
              }
            }
          }
          if(a == 1){
            a = 0;
            a_hp--;
            playerA.setIcon(stateM[a_hp]);
            break;
          }
        }
        if(field - enem == 1 || field - enem == -2){
          aField[enem_p].setIcon(type[0]);
        }
      }
      c = c - 1;
    }
  }

  user_Main(){

    tennokoe.setFont(new Font("MS ゴシック", Font.BOLD, 20));
    tennokoe.setAlignmentX(Component.CENTER_ALIGNMENT);    
    turn.setFont(new Font("MS ゴシック", Font.BOLD, 20));
    turn.setAlignmentX(Component.CENTER_ALIGNMENT);    
    
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
    
    putally.addActionListener(this);
    attenem.addActionListener(this);
    reset.addActionListener(this);
  
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
    operate.add(putally);
    operate.add(attenem);
    operate.add(reset);

    JPanel card1 = new JPanel();
    card1.setLayout(new GridLayout(0, 4));
    card1.add(playerA, 0);
    card1.add(pA, 1);
    card1.add(pB, 2);
    card1.add(playerB, 3);

    JPanel all = new JPanel();
    all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
    all.add(turn);
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
      for(int i = 0; i < 3; i++)
        ope[i] = 0;
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
    else if(btn == putally) ope[0] = 1;
    else if(btn == attenem) ope[1] = 1;
    else if(btn == reset) ope [2] = 1;
  }
}
