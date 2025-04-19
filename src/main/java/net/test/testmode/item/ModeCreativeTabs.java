package net.test.testmode.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.test.testmode.TestMode;
import net.test.testmode.block.ModBlock;

public class ModeCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CreativeTab =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMode.MOD_ID);


    public static final RegistryObject<CreativeModeTab> Alex_Creative_Tab = CreativeTab.register("alex" ,
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModeItems.Test.get())).title(Component.translatable("creativetap.testmode.alexandre_items")).displayItems((itemDisplayParameters,output) ->{
                output.accept(new ItemStack(ModeItems.Test.get()));
                output.accept(new ItemStack(ModeItems.Raw.get()));
                output.accept(new ItemStack(ModeItems.Chisle.get()));
                output.accept(new ItemStack(ModeItems.Foody.get()));
                output.accept(new ItemStack(ModeItems.Fuely.get()));
            }).build());


public static final RegistryObject<CreativeModeTab> Alex_Creative_Tab_block = CreativeTab.register("blocks" ,
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModBlock.Alex_Block.get())).withTabsBefore(Alex_Creative_Tab.getId()).title(Component.translatable("creativetap.toutorialmode.alexandre_blocks")).displayItems((itemDisplayParameters,output) ->{
                output.accept(ModBlock.Alex_Block.get() );
                output.accept(ModBlock.Raw_Alex_Block.get());
                output.accept(ModBlock.Magic_Block.get());
            }).build());



    public static void register(IEventBus eventBus){
        CreativeTab.register(eventBus);
    }
}
