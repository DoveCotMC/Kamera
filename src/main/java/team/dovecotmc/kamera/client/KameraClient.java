package team.dovecotmc.kamera.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.impl.client.rendering.BlockEntityRendererRegistryImpl;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import team.dovecotmc.kamera.Kamera;
import team.dovecotmc.kamera.client.block.entity.CameraBlockEntityRenderer;

public class KameraClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistryImpl.register(Kamera.BLOCK_ENTITY_CAMERA, context -> new CameraBlockEntityRenderer());
    }
}
