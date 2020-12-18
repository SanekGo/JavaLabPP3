package com.droids;

import com.logic.LogicDefines;
import java.util.Random;


public class DroidDealer extends Droid {

    private String droidClass = LogicDefines.DD_CLASS;

    public DroidDealer(String droidName, int droidHealth, int droidDamage) {
        super(droidName, (int)(droidHealth * LogicDefines.DD_HEALTH_MULT), (int)(droidDamage * LogicDefines.DD_DAMAGE_MULT));
    }

    public String getDroidClass() {return droidClass;}


    public void attack(Droid target) {
        Random chance = new Random();
        if (LogicDefines.DD_HIT_CHANCE > chance.nextInt(LogicDefines.DD_HIT_BOUND)) {
            target.setHealth(target.getHealth() - this.getDamage());
        }
    }

}
