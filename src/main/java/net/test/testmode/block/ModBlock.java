package net.test.testmode.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.test.testmode.TestMode;
import net.test.testmode.block.custom.MagicBlock;
import net.test.testmode.item.ModeItems;

import java.util.function.Supplier;

public class ModBlock {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMode.MOD_ID);


    public static final RegistryObject<Block> Alex_Block = registryBlock("alex_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));



    public static final RegistryObject<Block> Raw_Alex_Block = registryBlock("raw_alex",
            () -> new Block(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.GRAVEL)));


    public static final RegistryObject<Block> Magic_Block = registryBlock("mgaic_block",
            ()-> new MagicBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));


    private static <T extends Block> RegistryObject<T> registryBlock(String name , Supplier<T> block){
        RegistryObject<T> toreturn  = BLOCKS.register(name , block);
        registerBlockItem(name , toreturn);
        return toreturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModeItems.ITEMS.register(name , () -> new BlockItem(block.get() , new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);

    }
}
