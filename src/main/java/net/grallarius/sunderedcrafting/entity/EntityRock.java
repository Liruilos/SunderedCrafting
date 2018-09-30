package net.grallarius.sunderedcrafting.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRock extends EntityThrowable {

    private int knockback = 3;

    public EntityRock(World worldIn)
    {
        super(worldIn);
    }

    public EntityRock(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityRock(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesRock(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "Rock");
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            int i = 1;
// re-add if specific enemies should get extra damage
/*            if (result.entityHit instanceof EntityBlaze)
            {
                i = 3;
            }*/
            Entity entity = result.entityHit;
            if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {

                float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

                if (f1 > 0.0F) {
                    entity.addVelocity(this.motionX * knockback * 0.6000000238418579D / (double) f1, 0.1D, this.motionZ * knockback * 0.6000000238418579D / (double) f1);
                }

                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) i);
            }
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
