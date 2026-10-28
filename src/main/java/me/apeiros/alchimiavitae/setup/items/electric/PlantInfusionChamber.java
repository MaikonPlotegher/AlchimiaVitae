package me.apeiros.alchimiavitae.setup.items.electric;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;

import me.apeiros.alchimiavitae.AlchimiaUtils;
import me.apeiros.alchimiavitae.setup.AlchimiaItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public class PlantInfusionChamber extends AContainer implements RecipeDisplayItem {

    public PlantInfusionChamber(ItemGroup ig) {
        super(ig, AlchimiaItems.PLANT_INFUSION_CHAMBER, RecipeType.ANCIENT_ALTAR, AlchimiaUtils.recipe(
                SlimefunItems.ELECTRIC_PRESS, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_PRESS,
                SlimefunItems.HARDENED_GLASS, SlimefunItems.HEATED_PRESSURE_CHAMBER_2, SlimefunItems.HARDENED_GLASS,
                SlimefunItems.HEATING_COIL, SlimefunItems.ANCIENT_ALTAR, SlimefunItems.HEATING_COIL
        ));

        this.setProcessingSpeed(1).setCapacity(128).setEnergyConsumption(32);
    }

    // {{{ Register recipes
    public void registerDefaultRecipes() {
        this.registerRecipe(60,
            AlchimiaUtils.recipe(
                new ItemStack(Material.OAK_SAPLING),
                SlimefunItems.MAGIC_LUMP_3
            ),

            AlchimiaUtils.recipe(
                AlchimiaItems.LIGHT_MAGIC_PLANT
            )
        );

        this.registerRecipe(60,
            AlchimiaUtils.recipe(
                new ItemStack(Material.OAK_SAPLING),
                AlchimiaItems.CONDENSED_SOUL
            ),

            AlchimiaUtils.recipe(
                AlchimiaItems.DARK_MAGIC_PLANT
            )
        );
    }
    // }}}

    @Override
    protected void constructMenu(BlockMenuPreset preset) {
        preset.optOutOfHeaderItem();
        super.constructMenu(preset);
    }

    // {{{ Other
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.SHORT_GRASS);
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "AV_PLANT_INFUSION_CHAMBER";
    }
    // }}}

}
