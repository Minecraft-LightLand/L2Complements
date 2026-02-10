package dev.xkmc.l2complements.content.enchantment.digging;

public record SmartPlaneBlockBreaker(int radius) implements SimpleNumberDesc {

	@Override
	public BlockBreakerInstance getInstance(DiggerContext ctx) {
		int r = radius + ctx.level() - 1;
		int x = r * (1 - Math.abs(ctx.dire().getStepX()));
		int y = r * (1 - Math.abs(ctx.dire().getStepY()));
		int z = r * (1 - Math.abs(ctx.dire().getStepZ()));
		return new SmartPlaneInstance(-x, x, -y, y, -z, z, ctx.dire());
	}

	@Override
	public int range(int lv) {
		return (radius + lv - 1) * 2 + 1;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
}
