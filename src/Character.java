import java.util.Arrays;

public class Character {
    protected String name;
    protected double maxHp;
    protected double hp;
    protected double mana;
    protected double attack;
    protected double armor;
    protected boolean defend;
    protected Spell[] spellsLibrary;

    public double GetMaxHP(){
        return this.maxHp;
    }
    public double GetHP(){
        return this.hp;
    }
    public double GetMana(){
        return this.mana;
    }
    public double GetAttack(){
        return this.attack;
    }
    public double GetArmor(){
        return this.armor;
    }
    public String GetName(){return this.name;}


    public void SetMaxHp(double newMaxHp){
        this.maxHp = this.maxHp + Math.ceil(newMaxHp);
    }
    public void SetHp(double newHp){
        if(this.hp + newHp>=maxHp){
        this.hp=this.maxHp;
        }else {
            this.hp = this.hp + Math.ceil(newHp);
        }
    }
    public void SetMana(double newMana){
        this.mana = this.mana + Math.ceil(newMana);
    }
    public void SetAttack(double newAttack){
        this.attack = this.attack + Math.ceil(newAttack);
    }
    public void SetArmor(double newArmor){
        this.armor = this.armor + Math.ceil(newArmor);
    }
    public void SetDefend(boolean newDefend){
        this.defend = newDefend;

    }

    public void AddSpell(Spell spellToAdd){
        int i = 0;
        while(i<this.spellsLibrary.length) {
            if (this.spellsLibrary[i] == null) {
                this.spellsLibrary[i]=spellToAdd;
                break;
            }else{
                i++;
            }
        }
    }

    public int CountSpells(){
        int i = 0;
        int count=0;
        while(i<this.spellsLibrary.length) {
            if (this.spellsLibrary[i] == null) {
                i++;
            }else{
                count++;
            }
        }
        return count;
    }

    public Character(String name){
        if (name=="Warrior"){
            this.name = name;
            this.maxHp = 50;
            this.hp=50;
            this.mana=20;
            this.attack=15;
            this.armor=10;
            this.spellsLibrary=new Spell[10];
        }
    }
}


