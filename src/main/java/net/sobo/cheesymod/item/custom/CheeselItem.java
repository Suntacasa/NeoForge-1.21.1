package net.sobo.cheesymod.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.sobo.cheesymod.block.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class CheeselItem extends Item {
    private static final Map<Block, Block> CHEESEL_MAP=
        Map.of(
                ModBlocks.CHEESITE_BLOCK.get(), ModBlocks.MOLDY_CHEESITE_BLOCK.get(),
                ModBlocks.MOLDY_CHEESITE_BLOCK.get(), ModBlocks.CHEESITE_BLOCK.get(),
                Blocks.COPPER_ORE, ModBlocks.CHEESITE_ORE.get()
        );

    public CheeselItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    @NotNull
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(CHEESEL_MAP.containsKey(clickedBlock)) {
            //those types of changes should only be made on the server side, while the client processes things like visuals
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(pContext.getClickedPos(), CHEESEL_MAP.get(clickedBlock).defaultBlockState());

                //check if the player isn't null
                Player player = pContext.getPlayer();
                if (player instanceof ServerPlayer serverPlayer) {
                    //add breakage
                    pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) serverPlayer),
                            item -> serverPlayer.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                }

                level.playSound(null, pContext.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.cheesymod.cheesel.shift_down"));
        }
        else{
            tooltipComponents.add(Component.translatable("tooltip.cheesymod.cheesel"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
