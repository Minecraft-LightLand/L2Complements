package dev.xkmc.l2complements.content.client;

import dev.xkmc.l2complements.init.L2Complements;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.boss.wither.WitherBoss;

public class BackupWitherArmorLayer extends EnergySwirlLayer<WitherBoss, BackupWitherBossModel<WitherBoss>> {
	public static final ModelLayerLocation WITHER_ARMOR = new ModelLayerLocation(L2Complements.loc("wither"), "armor");

	private static final ResourceLocation WITHER_ARMOR_LOCATION = L2Complements.loc("textures/entity/wither_armor.png");

	private final BackupWitherBossModel<WitherBoss> model;

	public BackupWitherArmorLayer(RenderLayerParent<WitherBoss, BackupWitherBossModel<WitherBoss>> p_174554_, EntityModelSet p_174555_) {
		super(p_174554_);
		this.model = new BackupWitherBossModel<>(p_174555_.bakeLayer(WITHER_ARMOR));
	}

	@Override
	protected float xOffset(float p_117702_) {
		return Mth.cos(p_117702_ * 0.02F) * 3.0F;
	}

	@Override
	protected ResourceLocation getTextureLocation() {
		return WITHER_ARMOR_LOCATION;
	}

	@Override
	protected EntityModel<WitherBoss> model() {
		return this.model;
	}
}
