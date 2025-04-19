package net.test.testmode.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModeFoodProp {
    public static final FoodProperties Alex_Food = new FoodProperties.Builder().nutrition(10).saturationMod(10f).effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1f).build();
}
