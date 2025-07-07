package io.github.mxylery.bobuxplugin.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;

public class BobuxPlayerStats implements Serializable {

    private ArrayList<TempAttribute> tempAttributeList;
    private int bobuxBank;
    private UUID uuid;

    public BobuxPlayerStats(Player player) {
        this.uuid = player.getUniqueId();
        this.tempAttributeList = new ArrayList<TempAttribute>();
    }

    public BobuxPlayerStats(Player player, ArrayList<TempAttribute> tempAttributeList) {
        this.uuid = player.getUniqueId();
        this.tempAttributeList = tempAttributeList;
    }

    private void removeAttribute(TempAttribute attributeToRemove) {
        Attribute attribute = attributeToRemove.getAttribute();
        AttributeModifier modifier = attributeToRemove.getModifier();
        Player player = Bukkit.getPlayer(uuid);
        player.getAttribute(attribute).removeModifier(modifier);
        tempAttributeList.remove(attributeToRemove);
    }

    public void addAttribute(TempAttribute tempAttribute) {
        Attribute attribute = tempAttribute.getAttribute();
        AttributeModifier modifier = tempAttribute.getModifier();
        Player player = Bukkit.getPlayer(uuid);
        player.getAttribute(attribute).addModifier(modifier);
        tempAttributeList.add(tempAttribute);
    }

    public ArrayList<TempAttribute> getAttributeList() {
        return tempAttributeList;
    }

    public void tick() {
        Player player = Bukkit.getPlayer(uuid);
        ArrayList<TempAttribute> listToRemove = new ArrayList<TempAttribute>();
        for (TempAttribute tempAttribute : tempAttributeList) {
            tempAttribute.tick();
            //If no more time left on temp attribute
            if (tempAttribute.getTimeLeft() <= 0) {
                Attribute attribute = tempAttribute.getAttribute();
                AttributeModifier modifier = tempAttribute.getModifier();

                //Removes attribute from player 
                player.getAttribute(attribute).removeModifier(modifier);
                listToRemove.add(tempAttribute);
            }
        }
        tempAttributeList.removeAll(listToRemove);
    }

    @Override
    public String toString() {
        Player player = Bukkit.getPlayer(uuid);
        String finalString = player.getName();
        finalString = finalString + "'s Stats: ";
        finalString = finalString + "Bank = " + bobuxBank + ", Temp Attributes: ";
        for (TempAttribute tempAttribute : tempAttributeList) {
            finalString = finalString + tempAttribute.getAttribute().toString() + " ";
        }
        return finalString;
    }

}