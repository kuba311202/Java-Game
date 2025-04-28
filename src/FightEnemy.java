import javax.swing.*;
import java.util.Objects;

public class FightEnemy {
    protected Enemy slot1;
    protected String slot1Graphic;

    public JLabel getGraphic(JLabel Graphic) {
        return Graphic;
    }

    public FightEnemy(String fightNumber){
       if(Objects.equals(fightNumber, "1")){
           Enemy Boar = new Enemy("Boar");
           this.slot1 = Boar;
           this.slot1Graphic="../Gra/images/Boar.png";

       }
    }

}
