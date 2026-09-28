package com.example.rubymod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RubyWandItem extends Item {
    public RubyWandItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            HitResult hit = user.raycast(50.0, 0.0f, false);
            if (hit.getType() != HitResult.Type.MISS) {
                Vec3d pos = hit.getPos();
                LightningEntity bolt = EntityType.LIGHTNING_BOLT.create(world);
                if (bolt != null) {
                    bolt.refreshPositionAfterTeleport(pos);
                    world.spawnEntity(bolt);
                }
            }
            user.getItemCooldownManager().set(this, 40);
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
