package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityPassive;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class BardSetAbility extends AbilityPassive {

    public BardSetAbility() {
        super("Bard Set Ability", true, 20, 0);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        ArrayList<Player> playerList = new ArrayList<Player>();

        playerList.add((Player) user);
        BobuxRegisterer<Player> registerer = 
        new BobuxRegisterer<Player>(new RegistererOption(RegistererType.SPHERE, 5, 8, 5, new Vector(0,1,0)), user, Player.class);

        Entity[] registererList = registerer.getEntityList();
        Entity[] entityList;

        if (registererList != null) {
            for (Entity entity : registererList) {
                if (entity instanceof Player) {
                    Player player = (Player) entity;
                    playerList.add(player);
                }   
            }
            entityList = new Entity[1 + registerer.getEntityList().length];
            entityList[0] = user;
            for (int i = 1; i < entityList.length; i++) {
                entityList[i] = registererList[i-1];
            }
        } else {
            entityList = new Entity[1];
            entityList[0] = user;
        }

        ParticleSequence particleSequence1 = 
        new ParticleSequence(ParticleSequenceOptions.RING, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.fromRGB(200,200,255), 2));
        particleSequence1.setRingOptions(3, 1, 7, 8);

        componentHead = new AbilityComponent(new PlayParticle(particleSequence1), new Vector(0,1,0), user.getLocation());
        componentHead.addComponent(new AbilityComponent(new EffectGive(PotionEffectType.REGENERATION, 100, 0), entityList));
        componentHead.addComponent(new AbilityComponent(new EffectGive(PotionEffectType.RESISTANCE, 100, 0), entityList));

        return true;
    }

}
