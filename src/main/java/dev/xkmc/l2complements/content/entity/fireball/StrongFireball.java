package dev.xkmc.l2complements.content.entity.fireball;

import dev.xkmc.l2complements.init.registrate.LCEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class StrongFireball extends BaseFireball<StrongFireball> {

	public StrongFireball(EntityType<StrongFireball> type, Level level) {
		super(type, level);
	}

	public StrongFireball(double x, double y, double z, double vx, double vy, double vz, Level level) {
		super(LCEntities.ETFB_STRONG.get(), x, y, z, vx, vy, vz, level);
	}

	public StrongFireball(LivingEntity owner, double vx, double vy, double vz, Level level) {
		super(LCEntities.ETFB_STRONG.get(), owner, vx, vy, vz, level);
	}

	protected void onHitAction(Vec3 pos) {
		this.level.explode(this, pos.x, pos.y, pos.z, 2, false, Explosion.BlockInteraction.BREAK);
	}

}
