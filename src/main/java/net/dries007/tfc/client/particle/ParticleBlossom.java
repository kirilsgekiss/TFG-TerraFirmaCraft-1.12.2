package net.dries007.tfc.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleBlossom extends Particle
{
    private final int xSignModifier = rand.nextInt(2) - 1;
    private final int zSignModifier = rand.nextInt(2) - 1;
    private final double xMod = (rand.nextFloat() - 0.5) / 7;
    private final double zMod = (rand.nextFloat() - 0.5) / 7;

    public ParticleBlossom(World worldIn, double x, double y, double z, double speedX, double speedY, double speedZ, int duration)
    {
        super(worldIn, x, y, z, speedX, speedY, speedZ);
        this.particleMaxAge = duration + (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.motionX *= 0.1D;
        this.motionY *= 0.01D;
        this.motionZ *= 0.1D;
        this.motionX += speedX;
        this.motionY += speedY;
        this.motionZ += speedZ;
        this.particleScale *= 2.5F;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) { this.setExpired(); }

        double swayWave = 0.03 * MathHelper.sin((float) (this.particleAge / 12.5));
        double angleWave = 0.1 * MathHelper.sin((float) (this.particleAge / 12.5));

        this.particleAngle = (float) (angleWave * xSignModifier);

        this.motionY -= 0.001D;
        this.move(this.motionX, this.motionY, this.motionZ);

        if (this.posY == this.prevPosY)
        {
            this.motionX = 0;
            this.motionZ = 0;
        }

        this.motionX = swayWave * xSignModifier + xMod;
        this.motionY *= 0.96D;
        this.motionZ = swayWave * zSignModifier + zMod;

        if (this.onGround)
        {
            this.particleAlpha += 0.1;
            this.motionY = 0;
            if (this.particleAge >= this.particleMaxAge - rand.nextInt(6))
                this.setExpired();
        }
    }

    @Override
    public int getFXLayer()
    {
        return 1;
    }
}
