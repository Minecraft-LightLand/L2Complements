package dev.xkmc.l2complements.content.recipe;

import dev.xkmc.l2complements.init.registrate.LCRecipes;
import dev.xkmc.l2library.base.recipe.BaseRecipe;
import dev.xkmc.l2library.serial.SerialClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

@SerialClass
public class BurntRecipe extends BaseRecipe<BurntRecipe, BurntRecipe, BurntRecipe.Inv> {

	@SerialClass.SerialField
	public Ingredient ingredient;

	@SerialClass.SerialField
	public ItemStack result;

	@SerialClass.SerialField
	public int chance;

	public BurntRecipe(ResourceLocation id) {
		super(id, LCRecipes.RS_BURNT.get());
	}

	@Override
	public boolean matches(Inv inv, Level level) {
		return ingredient.test(inv.getItem(0));
	}

	@Override
	public ItemStack assemble(Inv inv) {
		return result.copy();
	}

	@Override
	public boolean canCraftInDimensions(int i, int i1) {
		return false;
	}

	@Override
	public ItemStack getResultItem() {
		return result;
	}

	public static class Inv extends SimpleContainer implements RecInv<BurntRecipe> {

		public Inv() {
			super(1);
		}

	}

}
