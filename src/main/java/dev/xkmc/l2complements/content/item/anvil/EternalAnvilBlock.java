package dev.xkmc.l2complements.content.item.anvil;

import dev.xkmc.l2complements.init.data.LCLang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class EternalAnvilBlock extends AnvilBlock {

	public EternalAnvilBlock(Properties prop) {
		super(prop);
	}

	@Nullable
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider((id, inv, pl) ->
				new EternalAnvilMenu(id, inv, ContainerLevelAccess.create(level, pos)),
				Component.translatable("container.repair"));
	}

	protected void falling(FallingBlockEntity p_48779_) {
		p_48779_.setHurtsEntities(4, 1000);
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext level, List<Component> list, TooltipFlag flag) {
		list.add(LCLang.IDS.ETERNAL_ANVIL.get().withStyle(ChatFormatting.GRAY));
	}

}
