package dev.xkmc.l2complements.content.item.wand;

import dev.xkmc.l2complements.init.data.LCConfig;
import dev.xkmc.l2complements.init.data.LCLang;
import dev.xkmc.l2library.content.raytrace.RayTraceUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class HeliosScepter extends WandItem {

	public static final int RANGE = 64, CHARGE = 60, SIZE = 10;

	public HeliosScepter(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		player.startUsingItem(hand);
		return InteractionResultHolder.consume(itemstack);
	}

	@Override
	public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remain) {
		if (!(user instanceof Player player)) return;
		var result = RayTraceUtil.rayTraceBlock(level, player, RANGE);
		var center = result.getLocation();
		int time = getUseDuration(stack, user) - remain;
		if (level.isClientSide()) return;
		renderRegionServer(user, center, time);
	}

	public static void renderRegionServer(LivingEntity user, Vec3 center, int time) {
		WandEffectToClient.Type.HELIOS_TICK.send(user, center, time);
	}

	public static void renderRegionClient(Level level, Vec3 center, int time) {
		double radius = Math.min(CHARGE, time) * 1.0 * SIZE / CHARGE;
		int n = Mth.clamp(time, 5, 20);
		for (int i = 0; i < n; i++) {
			float tpi = (float) (Math.PI * 2);
			Vec3 v0 = new Vec3(0, radius, 0);
			v0 = v0.xRot(tpi / 4).yRot(level.getRandom().nextFloat() * tpi);
			level.addAlwaysVisibleParticle(time > 20 && i <= n / 4 ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME,
					center.x + v0.x,
					center.y + v0.y + 0.5f,
					center.z + v0.z,
					0, 0, 0);
		}
	}

	public static void trigger(LivingEntity user, ServerLevel sl, Vec3 center, int time) {
		WandEffectToClient.Type.HELIOS_TRIGGER.send(user, center, time);
		float damage = LCConfig.SERVER.scepterOfHeliosDamage.get() * Math.min(1, time / 20f);
		double radius = Math.min(CHARGE, time) * 1.0 * SIZE / CHARGE;
		for (var e : sl.getEntities(user, AABB.ofSize(center.add(0, radius, 0),
				radius * 2, radius * 2, radius * 2))) {
			if (e instanceof LivingEntity x) {
				if (e == user || e.isAlliedTo(user) || user.isAlliedTo(e)) continue;
				x.hurt(sl.damageSources().indirectMagic(null, user), damage);
			}
		}
	}

	public static void renderHexagonClient(Level level, Vec3 center, int time) {
		level.playSound(null, center.x, center.y, center.z, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 3.0F, 1.0F);
		double radius = Math.min(CHARGE, time) * 1.0 * SIZE / CHARGE;
		double side = 1.732;
		double perimeter = Math.PI * 2;
		double total = side * 3 + perimeter;
		int size = time * 7;
		for (int i = 0; i < size; i++) {
			double perc = i * 1.0 / size * total;
			Vec3 v0;
			if (perc < side * 3) {
				int start = (int) Math.floor(perc / side);
				Vec3 tip = new Vec3(0, radius, 0);
				tip = tip.xRot((float) (Math.PI / 2)).yRot((float) (Math.PI * 2 / 3 * start));
				Vec3 next = tip.yRot((float) (Math.PI * 2 / 3));
				v0 = tip.add(next.subtract(tip).scale(perc / side - start));
				level.addAlwaysVisibleParticle(ParticleTypes.SOUL_FIRE_FLAME,
						center.x + v0.x,
						center.y + v0.y + 0.5,
						center.z + v0.z, 0, 1, 0);
				level.addAlwaysVisibleParticle(ParticleTypes.FLAME,
						center.x - v0.x,
						center.y + v0.y + 0.5,
						center.z - v0.z, 0, 1, 0);
			} else {
				v0 = new Vec3(0, radius, 0);
				v0 = v0.xRot((float) (Math.PI / 2)).yRot((float) (perc - side * 3));
				level.addAlwaysVisibleParticle(ParticleTypes.FLAME,
						center.x + v0.x,
						center.y + v0.y + 0.5,
						center.z + v0.z, 0, 1, 0);
			}
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int remain) {
		if (!(user instanceof Player player)) return;
		stack.hurtAndBreak(1, user, LivingEntity.getSlotForHand(user.getUsedItemHand()));
		var result = RayTraceUtil.rayTraceBlock(level, player, RANGE);
		var center = result.getLocation();
		int time = getUseDuration(stack, user) - remain;
		if (level instanceof ServerLevel sl) {
			trigger(user, sl, center, time);
		}
		player.getCooldowns().addCooldown(this, 10);
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.NONE;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> list, TooltipFlag flag) {
		list.add(LCLang.IDS.HELLFIRE_WAND.get().withStyle(ChatFormatting.GRAY));
	}

}
