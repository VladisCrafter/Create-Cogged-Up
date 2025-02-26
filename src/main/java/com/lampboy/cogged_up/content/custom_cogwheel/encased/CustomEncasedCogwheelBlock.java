package com.lampboy.cogged_up.content.custom_cogwheel.encased;

import com.lampboy.cogged_up.CoggedUpBETypes;
import com.lampboy.cogged_up.CoggedUpBlocks;
import com.lampboy.cogged_up.content.custom_cogwheel.CogwheelVariant;
import com.lampboy.cogged_up.content.custom_cogwheel.IHasMaterial;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Supplier;

public class CustomEncasedCogwheelBlock extends EncasedCogwheelBlock implements IHasMaterial {
    public CustomEncasedCogwheelBlock(Properties properties, boolean large, Supplier<Block> casing, CogwheelVariant material) {
        super(properties, large, casing);
        this.material = material;
    }

    public final CogwheelVariant material;

    @Override
    public CogwheelVariant getMaterial() {
        return material;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        if (target instanceof BlockHitResult)
            switch (material) {
                case ANDESITE -> {return ((BlockHitResult) target).getDirection()
                        .getAxis() != getRotationAxis(state)
                        ? isLarge ? CoggedUpBlocks.LARGE_ANDESITE_COGWHEEL.asStack() : CoggedUpBlocks.ANDESITE_COGWHEEL.asStack()
                        : getCasing().asItem().getDefaultInstance();}
                case BRASS -> {return ((BlockHitResult) target).getDirection()
                        .getAxis() != getRotationAxis(state)
                        ? isLarge ? CoggedUpBlocks.LARGE_BRASS_COGWHEEL.asStack() : CoggedUpBlocks.BRASS_COGWHEEL.asStack()
                        : getCasing().asItem().getDefaultInstance();}
                case COPPER -> {return ((BlockHitResult) target).getDirection()
                        .getAxis() != getRotationAxis(state)
                        ? isLarge ? CoggedUpBlocks.LARGE_COPPER_COGWHEEL.asStack() : CoggedUpBlocks.COPPER_COGWHEEL.asStack()
                        : getCasing().asItem().getDefaultInstance();}
            }
        return super.getCloneItemStack(state, target, world, pos, player);
    }

    @Override
    public BlockEntityType<? extends SimpleKineticBlockEntity> getBlockEntityType() {
        return CoggedUpBETypes.CUSTOM_ENCASED_COGWHEEL_BE.get();
    }
}
