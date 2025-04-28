
public class Fireball extends Spell{
    public Fireball(){
        manaCost=10;
        amount=10;
    }

    @Override
    public void Use(Character Target, Character Caster) {
        super.Use(Target, Caster);
    }

    @Override
    public void Damage(Character Target,  Character Caster) {
        super.Damage(Target, Caster);
    }
}


