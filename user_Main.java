import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class user_Main extends JFrame implements ActionListener{

  JPanel panel;
  CardLayout layout;

  public static int a_hands[] = {1, 2, 3, 1, 2, 3, 1, 2, 3, 1};
  public static int b_hands[] = {1, 2, 3, 1, 2, 3, 1, 2, 3, 1};
  public static int a_field[][] = {{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
  public static int b_field[][] = {{1, 2, 3, 1, 2, 3}, {0, 0, 0, 0, 0, 0}};

  public static int a_hp = 6;
  public static int b_hp = 6;

  public static void main(String args[]){
    user_Main frame = new user_Main();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(200, 200, 600, 400);
    frame.setTitle("タイトル");
    frame.setVisible(true);
    while(true){
      printField();
      doneCommand();
    }
  }

  user_Main(){
    
    /* 対戦ページ */
    JPanel pA = new JPanel();
    pA.setLayout(new GridLayout(3, 2));
    pA.add(new Button("one"));    
    pA.add(new Button("two"));    
    pA.add(new Button("three"));    
    pA.add(new Button("four"));    
    pA.add(new Button("five"));    
    pA.add(new Button("six"));    

    JPanel pB = new JPanel();
    pB.setLayout(new GridLayout(3, 2));
    pB.add(new Button("one"));    
    pB.add(new Button("two"));    
    pB.add(new Button("three"));    
    pB.add(new Button("four"));    
    pB.add(new Button("five"));    
    pB.add(new Button("six"));    

    JPanel hands = new JPanel();
    hands.setLayout(new GridLayout(0, 10));
    hands.add(new Button("a"));
    hands.add(new Button("b"));
    hands.add(new Button("c"));
    hands.add(new Button("d"));
    hands.add(new Button("e"));
    hands.add(new Button("f"));
    hands.add(new Button("g"));
    hands.add(new Button("h"));
    hands.add(new Button("i"));
    hands.add(new Button("j"));

    JPanel card1 = new JPanel();
    card1.setLayout(new GridLayout(0, 4));
    card1.add(new Button("PlayerA"), 0);
    card1.add(pA, 1);
    card1.add(pB, 2);
    card1.add(new Button("PlayerB"), 3);

    JPanel all = new JPanel();
    all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
    all.add(new JLabel("天の声"));
    all.add(card1);
    all.add(hands);

    /* View2 */
    JPanel card2 = new JPanel();
    card2.add(new JLabel("View2"));
 
    /* CardLayout準備 */
    this.panel = new JPanel();
    this.layout = new CardLayout();//CardLayoutの作成
    this.panel.setLayout(this.layout);
    /* panelにViewを追加 */
    this.panel.add(all, "View1");
    this.panel.add(card2, "View2");
    
    /* カード移動用ボタン */
    JButton button1 = new JButton("View1");
    button1.addActionListener(this);
    button1.setActionCommand("View1");

    JButton button2 = new JButton("View2");
    button2.addActionListener(this);
    button2.setActionCommand("View2");

    JPanel btnPanel = new JPanel();
    btnPanel.add(button1);
    btnPanel.add(button2);

    getContentPane().add(this.panel, BorderLayout.CENTER);
    getContentPane().add(btnPanel, BorderLayout.PAGE_END);
  }
  public void actionPerformed(ActionEvent e) {
    // TODO 自動生成されたメソッド・スタブ
    String cmd = e.getActionCommand();
    layout.show(this.panel,cmd);
  }

  public static void doneCommand(){
    System.out.println("----------------------------");
    System.out.println("  味方の手を配置 a");
    System.out.println("  相手の手を攻撃 b");
    System.out.println("  相手プレイヤーを攻撃 c");
    System.out.println("  ターンを終える d");
    System.out.println("----------------------------");
    System.out.print(" > コマンドを入力してください: ");
    Scanner scan = new Scanner(System.in);
    String str = scan.next();

    if(str.equals("a")) arrangement();
    if(str.equals("b")) attackJ();
    if(str.equals("c")) attackP();
    if(str.equals("d")) turnEnd(); 
  }

  public static void printField(){
    System.out.println("   ========  ========");
    System.out.println("   = " + a_field[0][0] + "  " + a_field[0][3] + " =  = " + b_field[0][3] + "  " + b_field[0][0] + " =");
    System.out.println("   =      =  =      =");
    System.out.println(" " + a_hp + " = " + a_field[0][1] + "  " + a_field[0][4] + " =  = " + b_field[0][4] + "  " + b_field[0][1] + " = " + b_hp);
    System.out.println("   =      =  =      =");
    System.out.println("   = " + a_field[0][2] + "  " + a_field[0][5] + " =  = " + b_field[0][5] + "  " + b_field[0][2] + " =");
    System.out.println("   ========  ========");
    for(int i = 0; i < 10; i++){
      System.out.print(a_hands[i] + " ");
    }
    System.out.println();
  }

  public static void arrangement(){
    //System.out.println("味方の手を配置します.");
    System.out.print(" > 何番目の手を場に配置しますか?: ");
    Scanner hand = new Scanner(System.in);
    String Thand = hand.next();

    System.out.print(" > 場のどこに配置しますか?: ");
    Scanner field = new Scanner(System.in);
    String Tfield = field.next();
    
    a_field[0][Integer.parseInt(Tfield)] = a_hands[Integer.parseInt(Thand)];
    a_hands[Integer.parseInt(Thand)] = 0;
  }

  public static void attackJ(){
    //System.out.println("相手の手を攻撃します.");
    System.out.print(" > どのカードで攻撃しますか?: ");
    Scanner attacker = new Scanner(System.in);
    String attack = attacker.next();

    System.out.print(" > どのカードを攻撃しますか?: ");
    Scanner receiver = new Scanner(System.in);
    String receive = receiver.next();
    
    int att = Integer.parseInt(attack);
    int rec = Integer.parseInt(receive);

    if((a_field[0][att] == 1 && b_field[0][rec] == 2) || (a_field[0][att] == 2 && b_field[0][rec] == 3) || (a_field[0][att] == 3 && b_field[0][rec] == 1)){
      System.out.println(" > 攻撃成功");
      b_field[0][rec] = 0;
    }else{
      System.out.println(" > 攻撃失敗");
    }
  }

  public static void attackP(){
    //System.out.println("相手のプレイヤーを攻撃します.");
    System.out.println(" > どのカードで攻撃しますか?: ");
    Scanner challenger = new Scanner(System.in);
    String challenge = challenger.next();
    b_hp = b_hp - 1;
  }

  public static void turnEnd(){
    System.out.println("ターンを終える.");
  }
}
