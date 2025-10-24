package team.dovecotmc.kamera.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import team.dovecotmc.kamera.Kamera;

public class CameraBlockEntity extends BlockEntity {
    public CameraBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(Kamera.BLOCK_ENTITY_CAMERA, blockPos, blockState);
    }
}
