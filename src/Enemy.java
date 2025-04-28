import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Enemy extends Character {
    protected JLabel Graphic;
    public Enemy(String name) {
        super(name);
        if(Objects.equals(name, "Boar")){
            this.name=name;
            this.maxHp=40;
            this.hp=40;
            this.mana=0;
            this.attack=10;
            this.armor=5;
            this.Graphic = new JLabel();
            this.Graphic.setIcon(new ImageIcon("../Gra/images/Boar.png"));
        }
    }
}
