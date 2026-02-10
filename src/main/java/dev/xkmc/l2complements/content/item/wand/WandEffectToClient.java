package dev.xkmc.l2complements.content.item.wand;

import dev.xkmc.l2complements.init.L2Complements;
import dev.xkmc.l2serial.network.SerialPacketBase;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public record WandEffectToClient(
		Type type, Vec3 vec, int time
) implements SerialPacketBase<WandEffectToClient> {

	@Override
	public void handle(Player player) {
		type.handler.handle(player.level(), vec, time);
	}

	public enum Type {
		HELLFIRE_TICK(HellfireWand::renderRegionClient),
		HELLFIRE_TRIGGER(HellfireWand::renderPentagonClient),
		WINTERSTORM(WinterStormWand::tickClient),
		HELIOS_TICK(HeliosScepter::renderRegionClient),
		HELIOS_TRIGGER(HeliosScepter::renderHexagonClient),
		BOREAS(BoreasScepter::tickClient),
		;


		private final Handler handler;

		Type(Handler handler) {
			this.handler = handler;
		}

		public void send(LivingEntity user, Vec3 center, int time) {
			L2Complements.HANDLER.toTrackingPlayers(new WandEffectToClient(this, center, time), user);
		}

	}

	public interface Handler {

		void handle(Level level, Vec3 pos, int time);

	}

}