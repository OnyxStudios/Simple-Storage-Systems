package nerdhub.simplestoragesystems.utils;

import nerdhub.simplestoragesystems.SimpleStorageSystems;
import nerdhub.simplestoragesystems.blocks.BlockBase;
import nerdhub.simplestoragesystems.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.function.Supplier;

public class RegistryHelper {

    /**
     * Blocks and Item registry Helpers
     */
    public static void registerBlock(BlockBase block) {
        Registry.register(Registry.BLOCK, block.getNameIdentifier(SimpleStorageSystems.MODID), block);
        Registry.register(Registry.ITEM, block.getNameIdentifier(SimpleStorageSystems.MODID), new BlockItem(block, new Item.Settings().itemGroup(block.getTab())));
    }

    public static void registerBlock(BlockBase block, BlockItem blockItem) {
        Registry.register(Registry.BLOCK, block.getNameIdentifier(SimpleStorageSystems.MODID), block);
        Registry.register(Registry.ITEM, block.getNameIdentifier(SimpleStorageSystems.MODID), blockItem);
    }

    public static void registerBlock(Identifier identifier, ItemGroup itemGroup, Block block) {
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().itemGroup(itemGroup)));
    }

    public static void registerBlock(Identifier identifier, Block block, BlockItem blockItem) {
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, blockItem);
    }

    public static void registerItem(ItemBase item) {
        Registry.register(Registry.ITEM, item.getNameIdentifier(), item);
    }

    public static void registerItem(Identifier identifier, Item item) {
        Registry.register(Registry.ITEM, identifier, item);
    }

    /**
     * Tile entity registry
     * EX: BlockEntityType<BlockEntityTest> BlockEntityTest = registerTile(new Identifier(MODID, NAME), BlockEntityTest.class);
     */
    public static BlockEntityType registerTile(Identifier identifier, Class<? extends BlockEntity> blockEntity) {
        return Registry.register(Registry.BLOCK_ENTITY, identifier, BlockEntityType.Builder.create((Supplier<BlockEntity>) () -> {
            try {
                return blockEntity.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            return null;
        }).build(null));
    }

    /**
     * World Gen Ore Registry
     */
    public static void generateOreInStone(Block block, int veinSize, int spawnRate, int maxHeight) {
        for (Biome biome : Biome.BIOMES) {
            biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, block.getDefaultState(), veinSize), Decorator.COUNT_RANGE, new RangeDecoratorConfig(spawnRate, 0, 0, maxHeight)));
        }
    }

    public static void generateOre(Block block, OreFeatureConfig.Target target, int veinSize, int spawnRate, int maxHeight) {
        for (Biome biome : Biome.BIOMES) {
            biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.ORE, new OreFeatureConfig(target, block.getDefaultState(), veinSize), Decorator.COUNT_RANGE, new RangeDecoratorConfig(spawnRate, 0, 0, maxHeight)));
        }
    }
}