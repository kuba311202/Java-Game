public class Lesser_heal extends Spell {
    public Lesser_heal(){
        manaCost=15;
        amount=15;
    }
    @Override
    public void Damage(Character Target,  Character Caster) {
        super.Damage(Target, Caster);
    }
}
