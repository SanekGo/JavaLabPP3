package com.droids;
import com.logic.LogicDefines;


public class DroidTank extends Droid {

    private String droidClass = LogicDefines.TANK_CLASS;

    public DroidTank(String droidName, int droidHealth, int droidDamage) {
        super(droidName, (int)(droidHealth * LogicDefines.TANK_HEALTH_MULT), (int)(droidDamage * LogicDefines.TANK_DAMAGE_MULT));
    }

    public String getDroidClass() {return droidClass;}

    public void attack(Droid target) {
        target.setHealth(target.getHealth() - this.getDamage());
    }

}
