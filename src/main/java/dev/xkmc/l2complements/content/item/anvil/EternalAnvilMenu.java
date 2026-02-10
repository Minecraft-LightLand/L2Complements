package dev.xkmc.l2complements.content.item.anvil;

import dev.xkmc.l2complements.init.registrate.LCBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.CommonHooks;

public class EternalAnvilMenu extends AnvilMenu {

	public static EternalAnvilMenu createFloating(int id, Inventory inv, ContainerLevelAccess access) {
		var ans = new EternalAnvilMenu(id, inv, access);
		ans.floating = true;
		return ans;
	}

	private boolean floating = false;

	public EternalAnvilMenu(MenuType<AnvilMenu> type, int id, Inventory inv) {
		super(id, inv);
	}

	public EternalAnvilMenu(int id, Inventory inv, ContainerLevelAccess access) {
		super(id, inv, access);
	}

	@Override
	public MenuType<?> getType() {
		return LCBlocks.ETERNAL_ANVIL_MENU.get();
	}

	protected void onTake(Player pl, ItemStack stack) {
		inputSlots.setItem(0, ItemStack.EMPTY);

		CommonHooks.onAnvilRepair(pl, stack, inputSlots.getItem(0), inputSlots.getItem(1));

		inputSlots.setItem(0, ItemStack.EMPTY);
		if (repairItemCountCost > 0) {
			ItemStack itemstack = inputSlots.getItem(1);
			if (!itemstack.isEmpty() && itemstack.getCount() > repairItemCountCost) {
				itemstack.shrink(repairItemCountCost);
				inputSlots.setItem(1, itemstack);
			} else {
				inputSlots.setItem(1, ItemStack.EMPTY);
			}
		} else {
			inputSlots.setItem(1, ItemStack.EMPTY);
		}

		setMaximumCost(0);
		access.execute((level, pos) -> level.levelEvent(1030, pos, 0));
	}

	@Override
	public boolean stillValid(Player player) {
		return floating || super.stillValid(player);
	}

	@Override
	public void createResult() {
		super.createResult();
		var stack = resultSlots.getItem(0);
		if (stack.getOrDefault(DataComponents.REPAIR_COST, 0) > 0) {
			stack.set(DataComponents.REPAIR_COST, 0);
			resultSlots.setItem(0, stack);
			broadcastChanges();
		}
	}

}
