package panels;
 
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    public String[] PanelNames = {"top","prep","plyA","plyB"};
    MainPanel top = new MainPanel(this,PanelNames[0]);
    Preparation prep = new Preparation(this,PanelNames[1]);
    PlayerA plyA = new PlayerA(this,PanelNames[2]);
    PlayerB plyB = new PlayerB(this,PanelNames[3]);
     
    public MainFrame(){
        this.add(top); top.setVisible(true);
        this.add(prep); prep.setVisible(false);
        this.add(plyA); plyA.setVisible(false);
        this.add(plyB); plyB.setVisible(false);
        this.setBounds(200, 200, 800, 400);
    }
    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        mf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
    public void PanelChange(JPanel jp, String str){
        System.out.println(jp.getName());
        String name = jp.getName();
        if(name==PanelNames[0]){
            top = (MainPanel)jp;
            top.setVisible(false);
        }else if(name==PanelNames[1]){
            prep = (Prepatation)jp;
            prep.setVisible(false);
        }else if(name==PanelNames[2]){
            plyA = (PlayerA)jp;
            plyA.setVisible(false);
        }else if(name==PanelNames[3]){
            plyB = (PlayerB)jp;
            plyB.setVisible(false);
        }
        if(str==PanelNames[0]){
            top.setVisible(true);
        }else if(str==PanelNames[1]){
            prep.setVisible(true);
        }else if(str==PanelNames[2]){
            plyA.setVisible(true);
        }else if(str==PanelNames[3]){
            plyB.setVisible(true);
        }
    }
}
