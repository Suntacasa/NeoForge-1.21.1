package net.sobo.cheesymod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sobo.cheesymod.CheesyMod;
import net.sobo.cheesymod.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CheesyMod.MOD_ID);

    public static final Supplier<CreativeModeTab> CHEESITE_ITEMS_TAB = CREATIVE_MODE_TABS.register("cheesite_items_tab",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModItems.CHEESITE.get()))
                    .title(Component.translatable("creativetab.cheesymod.cheesite_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CHEESITE);
                        output.accept(ModItems.RAW_CHEESITE);

                        output.accept(ModItems.CHEESEL);
                        output.accept(ModItems.BAKED_RAT);
                        output.accept(ModItems.RAT_POOP);
                    }).build());

    public static final Supplier<CreativeModeTab> CHEESITE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("cheesite_blocks_tab",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModBlocks.CHEESITE_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CheesyMod.MOD_ID, "cheesite_items_tab")) //so it is after the item tab
                    .title(Component.translatable("creativetab.cheesymod.cheesite_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CHEESITE_BLOCK);
                        output.accept(ModBlocks.CHEESITE_ORE);
                        output.accept(ModBlocks.MOLDY_CHEESITE_BLOCK);

                        output.accept(ModBlocks.MAGIC_BLOCK);

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
