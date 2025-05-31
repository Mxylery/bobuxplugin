package io.github.mxylery.bobuxplugin.entities.entities;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;
import io.github.mxylery.bobuxplugin.listeners.MobAbilityManager;

//schedule a remove itself task basically which will be its lifetime.
public class BobuxInvisArmorStand extends BobuxEntity {
    
    private BobuxAbility onSpawnAbil;
    private BobuxAbility onDeathAbil;

    public BobuxInvisArmorStand(Location location, long lifetime, BobuxAbility onSpawnAbil, BobuxAbility onDeathAbil) {
        super(location, lifetime);
        this.onSpawnAbil = onSpawnAbil;
        this.onDeathAbil = onDeathAbil;
    }

    public void setUpEntity() {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGravity(false);
        super.entity = armorStand;
        deleteSelf(this);
    }

    private void deleteSelf(BobuxEntity thisEntity) {
        Runnable runnable = new Runnable() {
            public void run() {
                if (onDeathAbil != null) {
                    MobAbilityManager.verifyAbilityCD(thisEntity, 0);
                }
                thisEntity.getEntity().remove();
                BobuxEntityListener.getBobuxEntityList().remove(thisEntity);
            }
        };
        BukkitScheduler scheduler = BobuxTimer.getScheduler();
        scheduler.runTaskLater(BobuxTimer.getPlugin(), runnable, super.lifetime);
    }
}
