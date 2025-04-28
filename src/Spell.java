
public class Spell {
    protected String name;
    protected int manaCost;
    protected Character target;
    protected int amount;
    protected String type;
    protected Character caster;

    public void Use(Character Target, Character Caster){

    }

    public void Damage(Character Target, Character Caster) {
        Caster.SetMana(-this.manaCost);
        Target.SetHp(-this.amount);
    }

    public void Heal(int ManaCost, Character Target, int Amount, Character Caster) {
        Caster.SetMana(-ManaCost);
        Target.SetHp(Amount);
    }
}