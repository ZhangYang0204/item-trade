package pers.zhangyang.itemtrade.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

public class ItemStackUtil {



    public static ItemStack getItemStack(String materialName, String displayName, List<String> lore){

        Material material= Material.matchMaterial(materialName);
        if (material.equals(Material.AIR)){throw new IllegalArgumentException();}
        ItemStack itemStack=new ItemStack(material);
        ItemMeta itemMeta=itemStack.getItemMeta();

        if (lore!=null){
            for(int i=0;i< lore.size();i++){
                lore.set(i, ChatColor.translateAlternateColorCodes('&',lore.get(i)));
            }

        }
        itemMeta.setLore(lore);
        if (displayName!=null){
            displayName=ChatColor.translateAlternateColorCodes('&',displayName);
        }

        itemMeta.setDisplayName(displayName);

        if(!itemStack.setItemMeta(itemMeta)){throw new IllegalArgumentException();}
        return itemStack;

    }

    public static String itemStackSerialize(@Nonnull ItemStack itemStack) {
        if (itemStack==null){throw new  NullPointerException();}
        YamlConfiguration yml = new YamlConfiguration();
        yml.set("item", itemStack);
        return yml.saveToString();

    }

    @Nonnull
    public static ItemStack itemStackDeserialize(@Nonnull String str)  {
        YamlConfiguration yml = new YamlConfiguration();
        ItemStack item;
        try {
            yml.loadFromString(str);
        } catch (InvalidConfigurationException e) {
            throw new IllegalArgumentException();
        }
        item = yml.getItemStack("item");
        if (item==null) throw new IllegalArgumentException();
        return item;
    }
}
