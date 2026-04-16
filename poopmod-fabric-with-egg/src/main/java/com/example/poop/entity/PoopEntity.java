
package com.example.poop.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class PoopEntity extends PathAwareEntity {
    private boolean hasEye = false;
    private int thinkTime = 0;

    private static final ItemStack[] LOOT = new ItemStack[]{
        new ItemStack(Items.ROTTEN_FLESH),
        new ItemStack(Items.SLIME_BALL),
        new ItemStack(Items.BONE),
        new ItemStack(Items.GOLD_NUGGET, 3),
        new ItemStack(Items.EMERALD),
        new ItemStack(Items.DIAMOND)
    };

    public PoopEntity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return PathAwareEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new WanderAroundGoal(this, 0.6));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        if (!getWorld().isClient) {
            if (hasEye) {
                thinkTime--;
                if (thinkTime % 10 == 0) {
                    ((ServerWorld) getWorld()).spawnParticles(ParticleTypes.HAPPY_VILLAGER, getX(), getY()+0.7, getZ(), 2, 0.2,0.2,0.2,0);
                }
                if (thinkTime <= 0) {
                    dropRandom();
                    fart();
                    hasEye = false;
                }
            } else {
                Box box = getBoundingBox().expand(3);
                List<ItemEntity> eyes = getWorld().getEntitiesByClass(ItemEntity.class, box, e -> e.isAlive() && e.getStack().isOf(Items.SPIDER_EYE));
                if (!eyes.isEmpty()) {
                    ItemEntity eye = eyes.get(0);
                    getNavigation().startMovingTo(eye, 1.0);
                    if (squaredDistanceTo(eye) < 2.0) {
                        eye.discard();
                        hasEye = true;
                        thinkTime = 60;
                        getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_VILLAGER_AMBIENT, SoundCategory.NEUTRAL, 0.7f, 1.2f);
                    }
                }
            }
        }
    }

    private void dropRandom() {
        ItemStack pick = LOOT[random.nextInt(LOOT.length)].copy();
        dropStack(pick);
    }

    private void fart() {
        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_SLIME_SQUISH, SoundCategory.NEUTRAL, 1f, 0.4f);
        if (getWorld() instanceof ServerWorld sw) {
            sw.spawnParticles(ParticleTypes.POOF, getX(), getY()+0.2, getZ(), 20, 0.2,0.1,0.2,0.01);
            sw.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, getX(), getY()+0.2, getZ(), 10, 0.1,0.1,0.1,0.005);
        }
    }

    @Override
    protected boolean canPickUpLoot() { return true; }
}
