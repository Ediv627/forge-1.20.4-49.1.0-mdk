package net.test.testmode.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.test.testmode.item.ModeItems;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f, 1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getItem().is(ModeItems.Test.get())) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));
            }

            if (itemEntity.getItem().is(Items.RABBIT_FOOT)) {
                itemEntity.setItem(new ItemStack(Items.EMERALD, itemEntity.getItem().getCount()));
            }

            if (itemEntity.getItem().is(Items.DIRT)) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND_BLOCK, itemEntity.getItem().getCount()));

            }

            if (itemEntity.getItem().is(Items.IRON_PICKAXE)) {
                ItemStack enchantedPickaxe = new ItemStack(Items.DIAMOND_PICKAXE, itemEntity.getItem().getCount());

                // Create enchantment NBT
                ListTag enchantments = new ListTag();

                // Efficiency 255
                CompoundTag enchantment = new CompoundTag();
                enchantment.putString("id", "minecraft:efficiency");
                enchantment.putShort("lvl", (short) 255); // Set to level 255
                CompoundTag fortune = new CompoundTag();
                fortune.putString("id", "minecraft:fortune");
                fortune.putShort("lvl", (short) 3);
                enchantments.add(fortune);
                enchantments.add(enchantment);

                // Apply the enchantments to the item
                enchantedPickaxe.getOrCreateTag().put("Enchantments", enchantments);

                itemEntity.setItem(enchantedPickaxe);
            }
        }

        super.stepOn(level, pos, state, entity);
    }
}
