package net.test.testmode.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.test.testmode.TestMode;
import net.test.testmode.item.custom.ChiselItem;
import net.test.testmode.item.custom.FuelItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ModeItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMode.MOD_ID);
    public static final RegistryObject<Item> Test = ITEMS.register("alex" ,
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> Raw = ITEMS.register("raw_ore" ,
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Chisle = ITEMS.register("chisle" ,
            () -> new ChiselItem(new Item.Properties().stacksTo(1).durability(32)));
    public static final RegistryObject<Item> Foody = ITEMS.register("foody" ,
            () -> new Item(new Item.Properties().food(ModeFoodProp.Alex_Food)) {
                @Override
                public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("item.testmode.foody.tooltip"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });


    public static final RegistryObject<Item> Fuely= ITEMS.register("fuely" ,
            () -> new FuelItem(new Item.Properties().stacksTo(16) , 1200));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
