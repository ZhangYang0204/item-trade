package pers.zhangyang.itemtrade.util;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemStackUtil {



    public static ItemStack getItemStack(String materialName, String displayName, List<String> lore){

        Material material= Material.matchMaterial(materialName);
        if (material.equals(Material.AIR)){throw new IllegalArgumentException();}
        ItemStack itemStack=new ItemStack(material);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(displayName);
        if(!itemStack.setItemMeta(itemMeta)){throw new IllegalArgumentException();}
        return itemStack;

    }

    public static String itemStackSerialize(@Nonnull ItemStack itemStack) {
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
