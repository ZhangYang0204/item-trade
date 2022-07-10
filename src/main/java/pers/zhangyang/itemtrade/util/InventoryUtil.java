package pers.zhangyang.itemtrade.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import javax.annotation.Nonnull;
import java.util.*;

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

    public static boolean contains(List<ItemStack> il,Inventory iv,Player player){
        List<ItemStack> itemStackListB=new ArrayList<>();
        for (ItemStack itemStack:il){
            if (itemStack==null){itemStackListB.add(null);continue;}
            itemStackListB.add(itemStack.clone());
        }
        List<ItemStack> itemStackList=new ArrayList<>();
        for (ItemStack i:il){
            if (i==null){continue;}
            itemStackList.add(i.clone());
        }
        ItemStack[] its=iv.getStorageContents();
        Inventory inventory=Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setStorageContents(its);
        Iterator<ItemStack> it=itemStackList.iterator();
        while (it.hasNext()){
            ItemStack itemStackListIt=it.next();
            if (computeItemHave(itemStackListIt,inventory)>=itemStackListIt.getAmount()){
                removeItem(inventory,itemStackListIt,itemStackListIt.getAmount());
                it.remove();
            }
        }
        if (!itemStackList.isEmpty()){
            List<String> list = MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_FAILURE_TRADE_BECAUSE_NOT_MATERIAL();
            if (list!=null) {
                for (int s=0;s<itemStackListB.size();s++){
                    if (itemStackList.get(0).isSimilar(itemStackListB.get(s))){
                        ReplaceUtil.replace(list, Collections.singletonMap("{item}",String.valueOf(s+1)));
                    }
                }
            }
            MessageUtil.sendMessageTo(player, list);
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