package dev.xkmc.l2complements.content.enchantment.digging;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.*;
import java.util.function.Predicate;

public record SmartPlaneInstance(int x0, int x1, int y0, int y1, int z0, int z1,
								 Direction dire) implements BlockBreakerInstance {

	@Override
	public List<BlockPos> find(Level level, BlockPos pos, Predicate<BlockPos> pred) {
		List<BlockPos> list = new ArrayList<>();
		Set<BlockPos> added = new HashSet<>();
		Queue<BlockPos> queue = new ArrayDeque<>();
		queue.add(pos);
		added.add(pos);
		var lo = pos.offset(x0, y0, z0);
		var hi = pos.offset(x1, y1, z1);
		var box = BoundingBox.fromCorners(lo, hi);
		while (!queue.isEmpty()) {
			var current = queue.poll();
			for (var d : Direction.values()) {
				if (d.getAxis() == dire.getAxis()) continue;
				BlockPos i = current.relative(d);
				if (!box.isInside(i)) continue;
				if (added.contains(i)) continue;
				added.add(i);
				if (!level.getBlockState(i.relative(dire)).getCollisionShape(level, i).isEmpty())
					continue;
				if (pred.test(i)) {
					list.add(i);
					queue.add(i);
				}
			}
		}
		return list;
	}

}
