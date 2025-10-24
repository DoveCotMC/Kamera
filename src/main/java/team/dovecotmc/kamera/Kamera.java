package team.dovecotmc.kamera;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.dovecotmc.kamera.block.CameraBlock;
import team.dovecotmc.kamera.block.MonitorBlock;
import team.dovecotmc.kamera.block.entity.CameraBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class Kamera implements ModInitializer {
    public static final String MOD_ID = "kamera";

    private static final List<Item> ITEMS = new ArrayList<>();

    public static final Block BLOCK_CAMERA = registerBlock("camera", new CameraBlock());
    public static final BlockEntityType<CameraBlockEntity> BLOCK_ENTITY_CAMERA = registerBlockEntity("camera", FabricBlockEntityTypeBuilder.create(CameraBlockEntity::new, BLOCK_CAMERA).build());
    public static final Item ITEM_CAMERA = registerItem("camera", new BlockItem(BLOCK_CAMERA, new Item.Properties()));
    public static final Block BLOCK_MONITOR = registerBlock("monitor", new MonitorBlock());
    public static final Item ITEM_MONITOR = registerItem("monitor", new BlockItem(BLOCK_MONITOR, new Item.Properties()));

    protected static Item registerItem(String id, Item item) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, id), item);
        ITEMS.add(item);
        return item;
    }

    protected static Block registerBlock(String id, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, id), block);
        return block;
    }

    protected static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String id, BlockEntityType<T> blockEntity) {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(MOD_ID, id), blockEntity);
        return blockEntity;
    }

    @Override
    public void onInitialize() {
        for (Item item : ITEMS) {
            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
                content.addAfter(item.getDefaultInstance(), item);
            });
        }
    }
}
