package pers.zhangyang.itemtrade.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class InventoryUtil {

    @Nonnull
    public static ItemStack getItemInMainHand(@Nonnull Player player){
        int bigVersion=MinecraftVersionUtil.getBigVersion();
        int middleVersion=MinecraftVersionUtil.getMiddleVersion();
        if (bigVersion==1&&middleVersion==8){
            return  player.getItemInHand();
        }else {
            return player.getInventory().getItemInMainHand();
        }
    }

  /*  //小于等于0应该不会给
    public static void addItemWithoutArmorContents(@Nonnull Player player,@Nonnull ItemStack itemStack,int amount){
        if (amount<=0||amount>checkSpace(player,itemStack)){
            throw new IllegalArgumentException();
        }
        while (amount>=itemStack.getMaxStackSize()){
            ItemStack item=itemStack.clone();
            item.setAmount(item.getMaxStackSize());
            player.getInventory().addItem(item);
            amount-=item.getMaxStackSize();
        }
        ItemStack item=itemStack.clone();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }*/

    public static void removeItem(@Nonnull Inventory inventory,@Nonnull ItemStack itemStack,int amount){


        for (int i=0;i<inventory.getStorageContents().length;i++){
            ItemStack itemStack2=inventory.getItem(i);
            if (itemStack2==null||itemStack2.getType().equals(Material.AIR)) continue;

            if (itemStack.getItemMeta().getDisplayName().equals(itemStack2.getItemMeta().getDisplayName())
            && Objects.equals(itemStack.getItemMeta().getLore(),itemStack2.getItemMeta().getLore())&&itemStack.getType().equals(itemStack2.getType()))
            {


                if (amount>0&&amount>=itemStack2.getAmount()){
                    amount-=itemStack2.getAmount();
                    inventory.clear(i);
                }else if (amount>0&&amount<itemStack2.getAmount()){
                    itemStack2.setAmount(itemStack2.getAmount()-amount);
                    amount=0;
                }
            }



        }
    }


    //由计算出玩家背包能已拥有的物品数量
    public static int computeItemHave(@Nonnull ItemStack itemStack, @Nonnull Inventory inventory){


        int number=0;

        for (int i=0;i<inventory.getStorageContents().length;i++){
            ItemStack itemStack2=inventory.getItem(i);
            if (itemStack2==null||itemStack2.getType().equals(Material.AIR)) continue;



            if (itemStack.getItemMeta().getDisplayName().equals(itemStack2.getItemMeta().getDisplayName())
                    && Objects.equals(itemStack.getItemMeta().getLore(),itemStack2.getItemMeta().getLore())&&itemStack.getType().equals(itemStack2.getType()))
            {
                number+=itemStack2.getAmount();
            }
        }
        return number;
    }

    public static boolean contains(List<ItemStack> il,Inventory iv){
        List<ItemStack> itemStackList=new ArrayList<>();
        for (ItemStack i:il){
            if (i==null){continue;}
            itemStackList.add(i.clone());
        }
        ItemStack[] its=iv.getStorageContents();
        Inventory inventory=Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setStorageContents(its);


        Iterator it=itemStackList.iterator();
        while (it.hasNext()){
            ItemStack itemStackListIt=(ItemStack) it.next();
            if (computeItemHave(itemStackListIt,inventory)>=itemStackListIt.getAmount()){
                removeItem(inventory,itemStackListIt,itemStackListIt.getAmount());
                it.remove();
            }
        }
        if (!itemStackList.isEmpty()){
            return false;
        }
        return true;
    }


  /*  //算出玩家背包内容纳某物品的数量 itemStack的数量无视
    public static int checkSpace( @Nonnull Player player,@Nonnull ItemStack itemStack){
        int maxStack=itemStack.getMaxStackSize();
        Inventory inventory=player.getInventory();
        int space=0;
        for (int i=0;i<player.getInventory().getStorageContents().length;i++){
            ItemStack stack=inventory.getItem(i);

            if (stack==null||stack.getType().equals(Material.AIR)){
                space+=maxStack;continue;
            }

            if (itemStack.isSimilar(stack)){
                space+=(maxStack>=stack.getAmount()?(maxStack-stack.getAmount()):0);
            }
        }
        return space;
    }*/}