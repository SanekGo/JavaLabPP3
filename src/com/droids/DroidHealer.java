package com.droids;
import com.logic.LogicDefines;


public class DroidHealer extends Droid {

    private String droidClass = LogicDefines.HEALER_CLASS;

    public DroidHealer(String droidName, int droidHealth, int droidDamage) {
        super(droidName, droidHealth, droidDamage);
    }

    public String getDroidClass() {return droidClass;}



    public void attack(Droid target) {
        target.setHealth(target.getHealth() + this.getDamage());
    }

}
