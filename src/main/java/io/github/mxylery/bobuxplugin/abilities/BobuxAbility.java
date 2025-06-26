package io.github.mxylery.bobuxplugin.abilities;

import org.bukkit.entity.Entity;

/* This will be used for the ability manager and to 
* give each bobux item a given ability (defining them 
* in their class def, not a random other managing class)
*/

//NAMING CONVENTION: CAPITALIZE THE NAME OF THE ABILITY THE EXACT SAME WAY AS THE NAME OF THE ITEM THAT IT IS THE ABILITY FOR.
//IF THERE ARE SEVERAL, NUMBER THEM.

//Will requires up to 5 things: User, Entity List, Vector List, Location List, Inventory List
public abstract class BobuxAbility {
    
    protected int actionListLength;
    protected AbilityComponent componentHead;
    protected Entity user;
    protected long cooldown;
    protected String name;
    protected boolean muteCD;
    protected Entity singleTarget;
    protected boolean triggeredNormally = true;

    public BobuxAbility(String name, boolean muteCD, long cooldown) {
        this.name = name;
        this.muteCD = muteCD;
        this.cooldown = cooldown;
    }

    public abstract boolean assignVariables();

    public void use() {
        AbilityComponent tempHead = componentHead;
        while (tempHead != null) {
            tempHead.useAction();
            tempHead = tempHead.getNextComponent();
        }
    }

    public long getCooldown() {
        return cooldown;
    }

    public String getName() {
        return name;
    }

    public boolean isMuted() {
        return muteCD;
    }

    public void setUser(Entity user) {
        this.user = user;
    }

    public void setTarget(Entity entity) {
        this.singleTarget = entity;
    }

    public void setTriggeredNormally(boolean triggeredNormally) {
        this.triggeredNormally = triggeredNormally;
    }

}
