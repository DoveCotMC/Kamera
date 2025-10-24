package team.dovecotmc.kamera.client.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import team.dovecotmc.kamera.block.CameraBlock;
import team.dovecotmc.kamera.block.entity.CameraBlockEntity;

public class CameraBlockEntityRenderer implements BlockEntityRenderer<CameraBlockEntity> {
    @Override
    public void render(CameraBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        Minecraft mc = Minecraft.getInstance();
        BlockState state = entity.getBlockState();
        BakedModel model =  mc.getModelManager().getBlockModelShaper().getBlockModel(state);

        matrices.pushPose();
        matrices.translate(0.5f, 0.5f, 0.5f);
        matrices.mulPoseMatrix(new Matrix4f().rotate((float) (state.getValue(CameraBlock.ROTATION) * -22.5) * Mth.DEG_TO_RAD, 0, 1, 0));
        matrices.translate(-0.5f, -0.5f, -0.5f);

        boolean bl = Minecraft.useAmbientOcclusion() && state.getLightEmission() == 0 && model.useAmbientOcclusion();
        Vec3 vec3d = state.getOffset(entity.getLevel(), entity.getBlockPos());
        matrices.translate(vec3d.x, vec3d.y, vec3d.z);

        if (entity.getLevel() != null) {
            mc.getBlockRenderer().getModelRenderer().tesselateBlock(entity.getLevel(), model, state, entity.getBlockPos(), matrices, vertexConsumers.getBuffer(ItemBlockRenderTypes.getChunkRenderType(state)), false, entity.getLevel().getRandom(), 0, overlay);
        }
        matrices.popPose();

//        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
//        camera.getPosition()
    }
}
