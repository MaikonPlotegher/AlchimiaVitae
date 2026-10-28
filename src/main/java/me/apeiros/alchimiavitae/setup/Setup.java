package me.apeiros.alchimiavitae.setup;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.api.researches.Research;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;

import me.apeiros.alchimiavitae.AlchimiaUtils;
import me.apeiros.alchimiavitae.AlchimiaVitae;
import me.apeiros.alchimiavitae.listeners.DyeListener;
import me.apeiros.alchimiavitae.listeners.infusion.FishingRodListener;
import me.apeiros.alchimiavitae.listeners.infusion.HoeListener;
import me.apeiros.alchimiavitae.listeners.infusion.MeleeWeaponListener;
import me.apeiros.alchimiavitae.listeners.infusion.RangedWeaponListener;
import me.apeiros.alchimiavitae.listeners.infusion.TotemListener;
import me.apeiros.alchimiavitae.setup.items.crafters.AltarOfInfusion;
import me.apeiros.alchimiavitae.setup.items.crafters.CosmicCauldron;
import me.apeiros.alchimiavitae.setup.items.crafters.DivineAltar;
import me.apeiros.alchimiavitae.setup.items.electric.EXPCrystallizer;
import me.apeiros.alchimiavitae.setup.items.electric.PlantInfusionChamber;
import me.apeiros.alchimiavitae.setup.items.general.MoltenMysteryMetal;
import me.apeiros.alchimiavitae.setup.items.general.SoulCollector;
import me.apeiros.alchimiavitae.setup.items.potions.BenevolentBrew;
import me.apeiros.alchimiavitae.setup.items.potions.MalevolentConcoction;
import me.apeiros.alchimiavitae.setup.items.potions.PotionOfOsmosis;

/**
 * Sets up items, {@link Listener}s, and {@link Research}es
 */
public class Setup {

    // {{{ Main
    public static void setup(AlchimiaVitae instance) {
        // Register item groups
        AlchimiaUtils.ItemGroups.MAIN.register(instance);
        AlchimiaUtils.ItemGroups.GENERAL.register(instance);
        AlchimiaUtils.ItemGroups.ALTAR_RECIPES.register(instance);
        AlchimiaUtils.ItemGroups.INFUSIONS.register(instance);

        // Items
        setupItems(instance);

        // Listeners
        setupListeners(instance);

        // Researches
        setupResearches(instance);
    }
    // }}}

    // {{{ Items
    private static void setupItems(AlchimiaVitae instance) {
        // Items
        // {{{ Soul Collector & Condensed Soul
        new SoulCollector(AlchimiaUtils.ItemGroups.GENERAL).register(instance);

        ItemStack condensedSoulRecipeItem = CustomItemStack.create(
                Material.DROWNED_SPAWN_EGG,
                "&bAny Mob",
                "&7Wither Skeletons and",
                "&7Withers have a chance",
                "&7to drop more souls...");

        new SlimefunItem(AlchimiaUtils.ItemGroups.GENERAL, AlchimiaItems.CONDENSED_SOUL, AlchimiaUtils.RecipeTypes.SOUL_COLLECTOR, AlchimiaUtils.recipe(
                null, null, null,
                null, condensedSoulRecipeItem, null,
                null, null, null
        )).register(instance);
        // }}}

        // {{{ Plants
        // Machine
        new PlantInfusionChamber(AlchimiaUtils.ItemGroups.GENERAL).register(instance);

        // Plants
        new SlimefunItem(AlchimiaUtils.ItemGroups.GENERAL, AlchimiaItems.LIGHT_MAGIC_PLANT, AlchimiaUtils.RecipeTypes.PLANT_INFUSION_CHAMBER, AlchimiaUtils.recipe(
                new ItemStack(Material.OAK_SAPLING), SlimefunItems.MAGIC_LUMP_3, null,
                null, null, null,
                null, null, null
        )).register(instance);

        new SlimefunItem(AlchimiaUtils.ItemGroups.GENERAL, AlchimiaItems.DARK_MAGIC_PLANT, AlchimiaUtils.RecipeTypes.PLANT_INFUSION_CHAMBER, AlchimiaUtils.recipe(
                new ItemStack(Material.OAK_SAPLING), AlchimiaItems.CONDENSED_SOUL, null,
                null, null, null,
                null, null, null
        )).register(instance);

        // Essence
        new SlimefunItem(AlchimiaUtils.ItemGroups.GENERAL, AlchimiaItems.LIGHT_ESSENCE, RecipeType.GRIND_STONE, AlchimiaUtils.recipe(
                AlchimiaItems.LIGHT_MAGIC_PLANT, null, null,
                null, null, null,
                null, null, null
        ), AlchimiaItems.LIGHT_ESSENCE.asQuantity(4)).register(instance);

        new SlimefunItem(AlchimiaUtils.ItemGroups.GENERAL, AlchimiaItems.DARK_ESSENCE, RecipeType.GRIND_STONE, AlchimiaUtils.recipe(
                AlchimiaItems.DARK_MAGIC_PLANT, null, null,
                null, null, null,
                null, null, null
        ), AlchimiaItems.DARK_ESSENCE.asQuantity(4)).register(instance);
        // }}}

        // {{{ EXP Crystallizer
        new EXPCrystallizer(AlchimiaUtils.ItemGroups.GENERAL).register(instance);

        new SlimefunItem(AlchimiaUtils.ItemGroups.GENERAL, AlchimiaItems.EXP_CRYSTAL, AlchimiaUtils.RecipeTypes.EXP_CRYSTALLIZER, AlchimiaUtils.recipe(
                SlimefunItems.FILLED_FLASK_OF_KNOWLEDGE.asQuantity(4), null, null,
                null, null, null,
                null, null, null
        )).register(instance);
        // }}}

        // {{{ Ingots
        // Illumium
        new SlimefunItem(AlchimiaUtils.ItemGroups.GENERAL, AlchimiaItems.ILLUMIUM, RecipeType.ENHANCED_CRAFTING_TABLE, AlchimiaUtils.recipe(
                SlimefunItems.MAGIC_LUMP_1, AlchimiaItems.EXP_CRYSTAL, SlimefunItems.MAGIC_LUMP_1,
                AlchimiaItems.LIGHT_ESSENCE, SlimefunItems.STEEL_INGOT, AlchimiaItems.LIGHT_ESSENCE,
                SlimefunItems.MAGIC_LUMP_1, AlchimiaItems.EXP_CRYSTAL, SlimefunItems.MAGIC_LUMP_1
        ), AlchimiaItems.ILLUMIUM.asQuantity(4)).register(instance);

        // Darksteel
        new SlimefunItem(AlchimiaUtils.ItemGroups.GENERAL, AlchimiaItems.DARKSTEEL, RecipeType.ENHANCED_CRAFTING_TABLE, AlchimiaUtils.recipe(
                SlimefunItems.ENDER_LUMP_1, AlchimiaItems.EXP_CRYSTAL, SlimefunItems.ENDER_LUMP_1,
                AlchimiaItems.DARK_ESSENCE, SlimefunItems.STEEL_INGOT, AlchimiaItems.DARK_ESSENCE,
                SlimefunItems.ENDER_LUMP_1, AlchimiaItems.EXP_CRYSTAL, SlimefunItems.ENDER_LUMP_1
        ), AlchimiaItems.DARKSTEEL.asQuantity(4)).register(instance);
        // }}}

        // {{{ Divine Altar
        DivineAltar divineAltar = new DivineAltar(AlchimiaUtils.ItemGroups.GENERAL);
        divineAltar.register(instance);

        new MoltenMysteryMetal(AlchimiaUtils.ItemGroups.GENERAL, divineAltar).register(instance);

        new SlimefunItem(AlchimiaUtils.ItemGroups.GENERAL, AlchimiaItems.MYSTERY_METAL, RecipeType.ENHANCED_CRAFTING_TABLE, AlchimiaUtils.recipe(
                AlchimiaItems.MOLTEN_MYSTERY_METAL, null, null,
                null, null, null,
                null, null, null
        ), AlchimiaItems.MYSTERY_METAL.asQuantity(16)).register(instance);
        // }}}

        // {{{ Cosmic Cauldron
        CosmicCauldron cauldron = new CosmicCauldron(AlchimiaUtils.ItemGroups.GENERAL, divineAltar);
        cauldron.register(instance);

        new PotionOfOsmosis(AlchimiaUtils.ItemGroups.GENERAL, cauldron).register(instance);
        new BenevolentBrew(AlchimiaUtils.ItemGroups.GENERAL, cauldron).register(instance);
        new MalevolentConcoction(AlchimiaUtils.ItemGroups.GENERAL, cauldron).register(instance);
        // }}}

        // {{{ Altar of Infusion
        new AltarOfInfusion(AlchimiaUtils.ItemGroups.INFUSIONS, divineAltar).register(instance);
        // }}}
    }
    // }}}

    // {{{ Listeners
    private static void setupListeners(AlchimiaVitae instance) {
        new DyeListener(instance);

        // Infusion listeners
        new MeleeWeaponListener(instance);
        new RangedWeaponListener(instance);
        new HoeListener(instance);
        new TotemListener(instance);
        new FishingRodListener(instance);
    }
    // }}}

    // {{{ Researches
    private static void setupResearches(AlchimiaVitae instance) {
        new Research(new NamespacedKey(instance, "soul"), 131072,
                "Manipulation of life force", 15)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.CONDENSED_SOUL, AlchimiaItems.SOUL_COLLECTOR))
                .register();

        new Research(new NamespacedKey(instance, "magic_plants"), 131073,
                "Powerful plants", 20)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.PLANT_INFUSION_CHAMBER, AlchimiaItems.LIGHT_MAGIC_PLANT, AlchimiaItems.DARK_MAGIC_PLANT))
                .register();

        new Research(new NamespacedKey(instance, "magic_essence"), 131074,
                "Powerful powder", 10)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.LIGHT_ESSENCE, AlchimiaItems.DARK_ESSENCE))
                .register();

        new Research(new NamespacedKey(instance, "exp_crystals"), 131075,
                "Pure crystalline energy", 12)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.EXP_CRYSTALLIZER, AlchimiaItems.EXP_CRYSTAL))
                .register();

        new Research(new NamespacedKey(instance, "magic_steel"), 131076,
                "Mystical metals", 16)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.DARKSTEEL, AlchimiaItems.ILLUMIUM))
                .register();

        new Research(new NamespacedKey(instance, "divine_altar"), 131077,
                "The Ancient Altar's lost cousin", 36)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.DIVINE_ALTAR))
                .register();

        new Research(new NamespacedKey(instance, "metal_amalgamation"), 131078,
                "Amalgam", 19)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.MOLTEN_MYSTERY_METAL, AlchimiaItems.MYSTERY_METAL))
                .register();

        new Research(new NamespacedKey(instance, "cosmic_cauldron"), 131079,
                "Advanced brewery", 36)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.COSMIC_CAULDRON))
                .register();

        new Research(new NamespacedKey(instance, "potion_of_osmosis"), 131080,
                "Absorbing and reflecting", 30)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.POTION_OF_OSMOSIS))
                .register();

        new Research(new NamespacedKey(instance, "benevolent_brew"), 131081,
                "A blessing from Gaia herself", 20)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.BENEVOLENT_BREW))
                .register();

        new Research(new NamespacedKey(instance, "malevolent_concoction"), 131082,
                "A demonic liquid", 20)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.MALEVOLENT_CONCOCTION))
                .register();

        new Research(new NamespacedKey(instance, "altar_of_infusion"), 131083,
                "Infusion", 36)
                .addItems(AlchimiaUtils.recipe(AlchimiaItems.ALTAR_OF_INFUSION))
                .register();
    }
    // }}}

}
