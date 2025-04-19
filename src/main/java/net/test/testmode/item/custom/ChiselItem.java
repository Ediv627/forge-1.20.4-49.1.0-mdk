package net.test.testmode.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.test.testmode.block.ModBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {

    private static final Map<Block, Block> chiselMap = Map.of(
            Blocks.SLIME_BLOCK, Blocks.HONEY_BLOCK,
            Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK,
            Blocks.GRASS_BLOCK, Blocks.DIAMOND_BLOCK,
            Blocks.DIRT, ModBlock.Alex_Block.get()
    );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Block clickedBlock = level.getBlockState(pos).getBlock();

        if (!chiselMap.containsKey(clickedBlock)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            Block newBlock = chiselMap.get(clickedBlock);
            level.setBlockAndUpdate(pos, newBlock.defaultBlockState());

            if (context.getPlayer() instanceof ServerPlayer serverPlayer) {
                context.getItemInHand().hurtAndBreak(1, serverPlayer,
                        (player) -> player.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }

            level.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("item.testmode.chisel.tooltip.shift"));
        }
        else{
            pTooltipComponents.add(Component.translatable("item.testmode.chisel.tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}