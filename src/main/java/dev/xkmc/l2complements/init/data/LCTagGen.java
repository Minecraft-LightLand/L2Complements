package dev.xkmc.l2complements.init.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import dev.xkmc.l2complements.compat.TFCompat;
import dev.xkmc.l2complements.init.L2Complements;
import dev.xkmc.l2complements.init.materials.LCMats;
import dev.xkmc.l2complements.init.registrate.LCBlocks;
import dev.xkmc.l2complements.init.registrate.LCEffects;
import dev.xkmc.l2complements.init.registrate.LCEnchantments;
import dev.xkmc.l2complements.init.registrate.LCEntities;
import dev.xkmc.l2core.init.L2TagGen;
import dev.xkmc.l2menustacker.init.L2MSTagGen;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;

public class LCTagGen {

	public static final TagKey<Block> AS_LEAF = BlockTags.create(L2Complements.loc("as_leaf"));
	public static final TagKey<Item> DAMPENS_VIBRATION = ItemTags.create(L2Complements.loc("dampens_vibration"));
	public static final TagKey<Item> HIDE_WITH_INVISIBILITY = ItemTags.create(L2Complements.loc("hide_with_invisibility"));

	public static final TagKey<Item> SCULK_MATS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("modulargolems", "sculk_materials"));
	public static final TagKey<Item> SPECIAL_ITEM = ItemTags.create(L2Complements.loc("l2c_legendary"));
	public static final TagKey<Item> DELICATE_BONE = ItemTags.create(L2Complements.loc("delicate_bone"));
	public static final TagKey<MobEffect> SKILL_EFFECT = TagKey.create(Registries.MOB_EFFECT, L2Complements.loc("skill_effect"));
	public static final TagKey<Enchantment> WAND_ENCH = enchTag("applicable_on_wand");


	public static final TagKey<Enchantment> IMMUNITY = enchTag("immunity");
	public static final TagKey<Enchantment> THORN = enchTag("thorn");
	public static final TagKey<Item> WEAPON_MINING_ENCHANTABLE = ItemTags.create(L2Complements.loc("enchantable/weapon_mining"));

	public static void onBlockTagGen(RegistrateTagsProvider.IntrinsicImpl<Block> pvd) {
		pvd.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(Blocks.REINFORCED_DEEPSLATE);
		pvd.addTag(AS_LEAF).addTag(BlockTags.LEAVES).addTag(BlockTags.WART_BLOCKS).add(Blocks.SHROOMLIGHT);
	}

	public static void onEffectTagGen(RegistrateTagsProvider.IntrinsicImpl<MobEffect> pvd) {
		pvd.addTag(SKILL_EFFECT).add(
				MobEffects.NIGHT_VISION.value(),
				MobEffects.BAD_OMEN.value(),
				MobEffects.TRIAL_OMEN.value(),
				MobEffects.RAID_OMEN.value(),
				MobEffects.HERO_OF_THE_VILLAGE.value(),
				MobEffects.DOLPHINS_GRACE.value(),
				MobEffects.CONDUIT_POWER.value(),
				MobEffects.WATER_BREATHING.value(),
				LCEffects.CLEANSE.get(),
				LCEffects.EMERALD.get()
		);
		pvd.addTag(SKILL_EFFECT)
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "angel_wings"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "evasion"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "heartstop"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "abyssal_shroud"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "ascension"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "charged"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "true_invisibility"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "fortify"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "spider_aspect"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "guided"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "airborne"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "vigor"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "oakskin"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "planar_sight"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "antigravity"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "hastened"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "burning_dash"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "gluttony"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "echoing_strikes"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "thunderstorm"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "volt_strike"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "frostbite"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "immolate"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "fall_damage_immunity"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "frozen_sight"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "vigor_siphon"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "abyssal_strike"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "assassin"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "lingering_strain"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "aerial_collapse_helper"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "spectral_blink"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "meteor_storm"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "reversal"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "consume"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "vortex_punch"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "casting"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "phantom_rage"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "spirit_damage_helper"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "replenish"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "jet_steam"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "tidal_grasp"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "floodgate"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "astral_path_reveal"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "astral_sense"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "astral_sense_treasure"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "crimson_descend"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "aptrgangr_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "cursed_revenants_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "desert_dweller_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "ender_golem_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "forsaken_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "gum_worm_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "kobolediator_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "magnetron_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "mechanized_predator_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "primal_pack_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("traveloptics", "vesper_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("gtbcs_geomancy_plus", "solar_storm"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("gtbcs_geomancy_plus", "tremor_step_effect"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("gtbcs_geomancy_plus", "casting"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("gtbcs_geomancy_plus", "aegis"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("gtbcs_geomancy_plus", "seismic_ride_timer"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("alshanex_familiars", "bird_buff"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("cataclysm", "monstrous"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("cataclysm", "ghost_form"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("cataclysm", "ghost_sickness"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "summoning_sickness"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "scrying"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "glide"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "flight"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "immolate"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "looting"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "bounce"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("ars_nouveau", "magic_find"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("mowziesmobs", "suns_blessing"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("mowziesmobs", "geomancy"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("mowziesmobs", "sunblock"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("farmersdelight", "nourishment"))
				.addOptional(ResourceLocation.fromNamespaceAndPath("farmersdelight", "comfort"));
		pvd.addTag(L2TagGen.TRACKED_EFFECTS).add(LCEffects.FLAME.get(), LCEffects.EMERALD.get(), LCEffects.ICE.get(),
				LCEffects.INCARCERATE.get(), LCEffects.BLEED.get(), LCEffects.CLEANSE.get(), LCEffects.CURSE.get());
	}

	public static void onEnchTagGen(RegistrateTagsProvider.Impl<Enchantment> pvd) {
		pvd.addTag(THORN).add(Enchantments.THORNS);
		pvd.addTag(WAND_ENCH).add(
				Enchantments.UNBREAKING,
				LCEnchantments.LIFE_SYNC.id(),
				LCEnchantments.ETERNAL.id()
		);
	}

	public static void onItemTagGen(RegistrateItemTagsProvider pvd) {
		pvd.addTag(WEAPON_MINING_ENCHANTABLE)
				.addTag(ItemTags.WEAPON_ENCHANTABLE)
				.addTag(ItemTags.MINING_ENCHANTABLE);


		pvd.addTag(SCULK_MATS).add(LCMats.SCULKIUM.getIngot());
		pvd.addTag(DELICATE_BONE).add(Items.SCULK_CATALYST, Items.SCULK_SHRIEKER);
		TFCompat.onItemTagGen(pvd);
		pvd.addTag(L2MSTagGen.QUICK_ACCESS_VANILLA).add(LCBlocks.ETERNAL_ANVIL.asItem());
		pvd.addTag(ItemTags.TRIM_MATERIALS).add(Arrays.stream(LCMats.values()).map(LCMats::getIngot).toArray(Item[]::new));
	}

	public static void onEntityTagGen(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> pvd) {
		pvd.addTag(EntityTypeTags.IMPACT_PROJECTILES).add(
				LCEntities.ETFB_BLACK.get(),
				LCEntities.ETFB_SOUL.get(),
				LCEntities.ETFB_STRONG.get());
	}

	private static TagKey<Enchantment> enchTag(String id) {
		return TagKey.create(Registries.ENCHANTMENT, L2Complements.loc(id));
	}

}
