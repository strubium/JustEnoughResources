package jeresources.compatibility.mwc;

import jeresources.api.distributions.DistributionSquare;
import jeresources.api.distributions.DistributionTriangular;
import jeresources.api.distributions.DistributionUnderWater;
import jeresources.api.drop.LootDrop;
import jeresources.api.drop.PlantDrop;
import jeresources.api.restrictions.BiomeRestriction;
import jeresources.api.restrictions.DimensionRestriction;
import jeresources.api.restrictions.Restriction;
import jeresources.compatibility.CompatBase;
import jeresources.entry.DungeonEntry;
import jeresources.entry.MobEntry;
import jeresources.entry.PlantEntry;
import jeresources.entry.WorldGenEntry;
import jeresources.util.LootTableHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;

public class ModernWarfareCompat extends CompatBase {
    @Override
    public void init(boolean worldGen) {
        //registerMWCMobs();
        if (worldGen)
            registerOres();
    }

    private void registerMWCMobs() {
        World world = getWorld();
        LootTableManager manager = LootTableHelper.getManager(world);
        LootTableHelper.getAllMobLootTables(world).entrySet().stream()
            .map(entry -> new MobEntry(entry.getValue(), manager.getLootTableFromLocation(entry.getKey())))
            .forEach(this::registerMob);
    }

    private void registerOres() {
        registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.LAPIS_ORE), new DistributionTriangular(15, 15, 0.001F), true, new LootDrop(new ItemStack(Items.DYE, 4, 4))));
        registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.IRON_ORE), new DistributionSquare(20, 8, 1, 64)));
        registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.REDSTONE_ORE), new DistributionSquare(8, 7, 1, 16), true, new LootDrop(new ItemStack(Items.REDSTONE, 4))));
        registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.DIAMOND_ORE), new DistributionSquare(1, 7, 1, 16), true, new LootDrop(new ItemStack(Items.DIAMOND))));
        registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.EMERALD_ORE), new DistributionSquare(6, 1, 4, 32), new Restriction(BiomeRestriction.EXTREME_HILLS), true, new LootDrop(new ItemStack(Items.EMERALD))));
        registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.GOLD_ORE), new DistributionSquare(2, 8, 1, 32)));
        registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.COAL_ORE), new DistributionSquare(20, 16, 1, 128), true, new LootDrop(new ItemStack(Items.COAL))));
        registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.QUARTZ_ORE), new DistributionSquare(20, 14, 1, 126), new Restriction(DimensionRestriction.NETHER), true, new LootDrop(new ItemStack(Items.QUARTZ, 4))));
        registerWorldGen(new WorldGenEntry(new ItemStack(Blocks.CLAY), new DistributionUnderWater(0.0035F), new LootDrop(new ItemStack(Items.CLAY_BALL, 4))));
    }
}
