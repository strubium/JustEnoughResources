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
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;
import com.paneedah.mwc.init.MWCBlocks;
import com.paneedah.mwc.init.MWCItems;

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
        registerWorldGen(new WorldGenEntry(new ItemStack(MWCBlocks.copperOre), new DistributionSquare(15, 15, 64)));
        registerWorldGen(new WorldGenEntry(new ItemStack(MWCBlocks.tinOre), new DistributionSquare(20, 8, 1, 64)));
        registerWorldGen(new WorldGenEntry(new ItemStack(MWCBlocks.leadOre), new DistributionSquare(8, 7, 1, 16)));
        registerWorldGen(new WorldGenEntry(new ItemStack(MWCBlocks.sulfurOre), new DistributionSquare(1, 7, 1, 16), true, new LootDrop(new ItemStack((MWCItems.sulfurDust, 2, 5))));
        registerWorldGen(new WorldGenEntry(new ItemStack(MWCBlocks.graphiteOre), new DistributionSquare(6, 1, 4, 32), true, new LootDrop(new ItemStack(MWCItems.graphiteChunk, 1, 3))));
    }
}
