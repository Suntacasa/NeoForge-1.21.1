package net.sobo.cheesymod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sobo.cheesymod.CheesyMod;
import net.sobo.cheesymod.block.custom.MagicBlock;
import net.sobo.cheesymod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CheesyMod.MOD_ID);

    public static final DeferredBlock<Block> CHEESITE_BLOCK = registerBlock("cheesite_block",
            ()-> new Block(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.SPONGE)));

    public static final DeferredBlock<Block> MOLDY_CHEESITE_BLOCK = registerBlock("moldy_cheesite_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.CORAL_BLOCK)));


    public static final DeferredBlock<Block> CHEESITE_ORE = registerBlock("cheesite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.FUNGUS)));

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of().strength(2f)));

    //register
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //register the block as an item also
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
