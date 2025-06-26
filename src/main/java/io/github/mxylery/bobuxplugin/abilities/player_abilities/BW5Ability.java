package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.item.DeleteItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class BW5Ability extends AbilityOneTime {

    public BW5Ability() {
        super("BW5 Ability", false, 100);
    }

    //Assuming the player is a player
    public boolean assignVariables() {

        Player player = (Player) user;
        
        Location elevatedPlayerLoc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
        Vector playerDirection = player.getLocation().getDirection();
        RegistererOption registererOption1 = new RegistererOption(RegistererType.LINE, 30, 1, 1, player.getEyeLocation().getDirection());
        BobuxRegisterer<Mob> registerer1 = new BobuxRegisterer<Mob>(registererOption1, player, Mob.class);
        if (registerer1.getEntityList() == null) {
            return false;
        }
        Location enemyLocation = new Location(player.getWorld(), registerer1.getEntityList()[0].getLocation().getX(), registerer1.getEntityList()[0].getLocation().getY() + 1, registerer1.getEntityList()[0].getLocation().getZ());

        ParticleSequence BW5ParticleSequence1 = new ParticleSequence
        (ParticleSequenceOptions.LINE, ParticleSequenceOrientations.NORMAL, Particle.WHITE_SMOKE, null);
        BW5ParticleSequence1.setLineOptions(30, 1, 0);
        ParticleSequence BW5ParticleSequence2 = new ParticleSequence
        (ParticleSequenceOptions.RING, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.RED, 2));
        BW5ParticleSequence2.setRingOptions(5, 3, 2, 2);
        ParticleSequence BW5ParticleSequence3 = new ParticleSequence
        (ParticleSequenceOptions.LINE, ParticleSequenceOrientations.UP, Particle.DUST, new DustOptions(Color.RED, 2));
        BW5ParticleSequence3.setLineOptions(2,3,1);
        ParticleSequence BW5ParticleSequence4 = new ParticleSequence
        (ParticleSequenceOptions.LINE, ParticleSequenceOrientations.RIGHT, Particle.DUST, new DustOptions(Color.RED, 2));
        BW5ParticleSequence4.setLineOptions(2,3,1);
        ParticleSequence BW5ParticleSequence5 = new ParticleSequence
        (ParticleSequenceOptions.LINE, ParticleSequenceOrientations.DOWN, Particle.DUST, new DustOptions(Color.RED, 2));
        BW5ParticleSequence5.setLineOptions(2,3,1);
        ParticleSequence BW5ParticleSequence6 = new ParticleSequence
        (ParticleSequenceOptions.LINE, ParticleSequenceOrientations.LEFT, Particle.DUST, new DustOptions(Color.RED, 2));
        BW5ParticleSequence6.setLineOptions(2,3,1);

        componentHead = new AbilityComponent
        (new DamageEntity(30), registerer1.getEntityList());
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(BW5ParticleSequence1), playerDirection, elevatedPlayerLoc));
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(BW5ParticleSequence2), playerDirection, enemyLocation));
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(BW5ParticleSequence3), playerDirection, enemyLocation));
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(BW5ParticleSequence4), playerDirection, enemyLocation));
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(BW5ParticleSequence5), playerDirection, enemyLocation));
        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.ENTITY_GENERIC_EXPLODE, 0.4f, 0.5f), enemyLocation));
        componentHead.addComponent(new AbilityComponent
        (new DeleteItem(BobuxItemInterface.BW5Ammo.getStack(), 1), player.getInventory()));

        return true;
    }

}
