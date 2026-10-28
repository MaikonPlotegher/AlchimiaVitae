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

public class EXPCrystallizer extends AContainer implements RecipeDisplayItem {

    public EXPCrystallizer(ItemGroup ig) {
        super(ig, AlchimiaItems.EXP_CRYSTALLIZER, RecipeType.ANCIENT_ALTAR, AlchimiaUtils.recipe(
                SlimefunItems.TALISMAN_WISE, SlimefunItems.MAGICAL_GLASS, SlimefunItems.TALISMAN_WISE,
                SlimefunItems.HARDENED_GLASS, SlimefunItems.EXP_COLLECTOR, SlimefunItems.HARDENED_GLASS,
                SlimefunItems.HEATING_COIL, SlimefunItems.ANCIENT_ALTAR, SlimefunItems.HEATING_COIL
        ));

        this.setProcessingSpeed(1).setCapacity(64).setEnergyConsumption(16);
    }

    // {{{ Register recipes
    public void registerDefaultRecipes() {
        this.registerRecipe(10,
            AlchimiaUtils.recipe(
                SlimefunItems.FILLED_FLASK_OF_KNOWLEDGE.asQuantity(2)
            ),

            AlchimiaUtils.recipe(
                AlchimiaItems.EXP_CRYSTAL.asQuantity(1),
                SlimefunItems.FLASK_OF_KNOWLEDGE.asQuantity(2)
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
        return new ItemStack(Material.EMERALD);
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "AV_EXP_CRYSTALLIZER";
    }
    // }}}

}
