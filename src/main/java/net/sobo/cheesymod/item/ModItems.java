package net.sobo.cheesymod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sobo.cheesymod.CheesyMod;
import net.sobo.cheesymod.item.custom.CheeselItem;
import net.sobo.cheesymod.item.custom.FuelItem;

import java.util.List;

public class ModItems {
    //basically when it needs to register the item, it does XD
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(CheesyMod.MOD_ID);

    //create the item cheesite with default proprieties
    public static final DeferredItem<Item> CHEESITE = ITEMS.register("cheesite", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_CHEESITE = ITEMS.register("raw_cheesite", () -> new Item(new Item.Properties())
    {
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
            tooltipComponents.add(Component.translatable("tooltip.cheesymod.raw_cheesite.tooltip"));
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }
    });

    public static final DeferredItem<Item> CHEESEL = ITEMS.register("cheesel",
            () -> new CheeselItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> BAKED_RAT = ITEMS.register("baked_rat",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BAKED_RAT)));

    public static final DeferredItem<Item> RAT_POOP = ITEMS.register("rat_poop",
            () -> new FuelItem(new Item.Properties(), 1200));

    //“Hey Forge, when it’s time to register items... call this stuff over here.”
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
