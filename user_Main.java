import java.util.*;

class user_Main{

  public static int a_hands[] = {1, 2, 3, 1, 2, 3, 1, 2, 3, 1};
  public static int b_hands[] = {1, 2, 3, 1, 2, 3, 1, 2, 3, 1};
  public static int a_field[] = {0, 0, 0, 0, 0, 0};
  public static int b_field[] = {1, 2, 3, 1, 2, 3};

  public static int a_hp = 6;
  public static int b_hp = 6;

  public static void main(String args[]){
    while(true){
      printField();
      doneCommand();
    }
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
    System.out.println("   = " + a_field[0] + "  " + a_field[3] + " =  = " + b_field[3] + "  " + b_field[0] + " =");
    System.out.println("   =      =  =      =");
    System.out.println(" " + a_hp + " = " + a_field[1] + "  " + a_field[4] + " =  = " + b_field[4] + "  " + b_field[1] + " = " + b_hp);
    System.out.println("   =      =  =      =");
    System.out.println("   = " + a_field[2] + "  " + a_field[5] + " =  = " + b_field[5] + "  " + b_field[2] + " =");
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
    
    a_field[Integer.parseInt(Tfield)] = a_hands[Integer.parseInt(Thand)];
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

    if((a_field[att] == 1 && b_field[rec] == 2) || (a_field[att] == 2 && b_field[rec] == 3) || (a_field[att] == 3 && b_field[rec] == 1)){
      System.out.println(" > 攻撃成功");
      b_field[rec] = 0;
    }else{
      System.out.println(" > 攻撃失敗");
    }
  }

  public static void attackP(){
    //System.out.println("相手のプレイヤーを攻撃します.");
    System.out.print;lk(" > どのカードで攻撃しますか?: ");
    Scanner challenger = new Scanner(System.in);
    String challenge = challenger.next();
    b_hp = b_hp - 1;
  }

  public static void turnEnd(){
    System.out.println("ターンを終える.");
  }
}
