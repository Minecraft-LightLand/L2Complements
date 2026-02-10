package dev.xkmc.l2complements.init.registrate;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.MenuEntry;
import dev.xkmc.l2complements.content.item.anvil.EternalAnvilBlock;
import dev.xkmc.l2complements.content.item.anvil.EternalAnvilMenu;
import dev.xkmc.l2complements.init.L2Complements;
import dev.xkmc.l2complements.init.materials.LCMats;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * handles blocks and block entities
 */
public class LCBlocks {

	public static final BlockEntry<EternalAnvilBlock> ETERNAL_ANVIL = L2Complements.REGISTRATE
			.block("eternal_anvil", p -> new EternalAnvilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL)))
			.blockstate((ctx, pvd) -> pvd.horizontalBlock(ctx.getEntry(), pvd.models().getExistingFile(
					L2Complements.loc("eternal_anvil")), 90))
			.tag(BlockTags.ANVIL, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL)
			.item().tag(ItemTags.ANVIL).build().register();

	public static final MenuEntry<?> ETERNAL_ANVIL_MENU = L2Complements.REGISTRATE
			.menu("eternal_anvil", EternalAnvilMenu::new, () -> AnvilScreen::new).register();

	public static final BlockEntry<Block>[] GEN_BLOCK = L2Complements.MATS.genBlockMats(LCMats.values());

	public static void register() {
	}

}
