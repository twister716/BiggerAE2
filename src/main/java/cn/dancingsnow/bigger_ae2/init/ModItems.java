package cn.dancingsnow.bigger_ae2.init;

import appeng.api.stacks.AEKeyType;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.items.materials.MaterialItem;
import appeng.items.materials.StorageComponentItem;
import appeng.items.storage.BasicStorageCell;
import cn.dancingsnow.bigger_ae2.item.cell.DigitalSingularityCellItem;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import static cn.dancingsnow.bigger_ae2.BiggerAE2Mod.REGISTRATE;

public class ModItems {

    static {
        REGISTRATE.creativeModeTab(() -> ModCreativeTab.TAB);
    }


    public static final ItemEntry<MaterialItem> ADVANCED_ITEM_CELL_HOUSING = REGISTRATE
            .item("advanced_item_cell_housing", MaterialItem::new)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(ctx.get())
                    .pattern("ABA")
                    .pattern("B B")
                    .pattern("CCC")
                    .define('A', AEBlocks.QUARTZ_BLOCK)
                    .define('B', Items.REDSTONE)
                    .define('C', Items.GOLD_INGOT)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(AEBlocks.QUARTZ_BLOCK))
                    .save(provider))
            .register();

    public static final ItemEntry<MaterialItem> ADVANCED_FLUID_CELL_HOUSING = REGISTRATE
            .item("advanced_fluid_cell_housing", MaterialItem::new)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(ctx.get())
                    .pattern("ABA")
                    .pattern("B B")
                    .pattern("CCC")
                    .define('A', AEBlocks.QUARTZ_BLOCK)
                    .define('B', Items.REDSTONE)
                    .define('C', Items.DIAMOND)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(AEBlocks.QUARTZ_BLOCK))
                    .save(provider))
            .register();

    public static final ItemEntry<StorageComponentItem> QUANTUM_CELL_COMPONENT = REGISTRATE
            .item("quantum_cell_component", p -> new StorageComponentItem(p, 1 << 28 - 1))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(ctx.get())
                    .pattern("ABA")
                    .pattern("CDC")
                    .pattern("ACA")
                    .define('A', AEItems.SKY_DUST)
                    .define('B', AEItems.ENGINEERING_PROCESSOR)
                    .define('C', AEItems.CELL_COMPONENT_256K)
                    .define('D', AEBlocks.QUARTZ_BLOCK)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(AEItems.CELL_COMPONENT_256K))
                    .save(provider))
            .register();

    public static final ItemEntry<BasicStorageCell> QUANTUM_ITEM_CELL = REGISTRATE
            .item("quantum_item_storage_cell", p -> new BasicStorageCell(
                    p.stacksTo(1),
                    QUANTUM_CELL_COMPONENT.get(),
                    ADVANCED_ITEM_CELL_HOUSING.get(),
                    20,
                    (1 << 28 - 1) / 1024,
                    65536,
                    1,
                    AEKeyType.items()
            ))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(ctx.get())
                    .requires(QUANTUM_CELL_COMPONENT.get())
                    .requires(ADVANCED_ITEM_CELL_HOUSING.get())
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(ADVANCED_ITEM_CELL_HOUSING.get()))
                    .save(provider))
            .register();

    public static final ItemEntry<BasicStorageCell> QUANTUM_FLUID_CELL = REGISTRATE
            .item("quantum_fluid_storage_cell", p -> new BasicStorageCell(
                    p.stacksTo(1),
                    QUANTUM_CELL_COMPONENT.get(),
                    ADVANCED_FLUID_CELL_HOUSING.get(),
                    20,
                    (1 << 28 - 1) / 1024,
                    65536,
                    1,
                    AEKeyType.fluids()
            ))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(ctx.get())
                    .requires(QUANTUM_CELL_COMPONENT.get())
                    .requires(ADVANCED_FLUID_CELL_HOUSING.get())
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(ADVANCED_FLUID_CELL_HOUSING.get()))
                    .save(provider))
            .register();

    public static final ItemEntry<MaterialItem> SINGULARITY_CELL_COMPONENT = REGISTRATE
            .item("digital_singularity_cell_component", MaterialItem::new)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(ctx.get())
                    .pattern("ABA")
                    .pattern("CDC")
                    .pattern("ACA")
                    .define('A', AEItems.SKY_DUST)
                    .define('B', AEItems.ENGINEERING_PROCESSOR)
                    .define('C', QUANTUM_CELL_COMPONENT.get())
                    .define('D', AEBlocks.QUARTZ_BLOCK)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(QUANTUM_CELL_COMPONENT.get()))
                    .save(provider))
            .register();

    public static final ItemEntry<DigitalSingularityCellItem> SINGULARITY_ITEM_CELL = REGISTRATE
            .item("digital_singularity_item_storage_cell", p -> new DigitalSingularityCellItem(
                    p,
                    AEKeyType.items(),
                    SINGULARITY_CELL_COMPONENT.get(),
                    ADVANCED_ITEM_CELL_HOUSING.get()
            ))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(ctx.get())
                    .requires(SINGULARITY_CELL_COMPONENT.get())
                    .requires(ADVANCED_ITEM_CELL_HOUSING.get())
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(ADVANCED_ITEM_CELL_HOUSING.get()))
                    .save(provider))
            .register();

    public static final ItemEntry<DigitalSingularityCellItem> SINGULARITY_FLUID_CELL = REGISTRATE
            .item("digital_singularity_fluid_storage_cell", p -> new DigitalSingularityCellItem(
                    p,
                    AEKeyType.fluids(),
                    SINGULARITY_CELL_COMPONENT.get(),
                    ADVANCED_FLUID_CELL_HOUSING.get()
            ))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(ctx.get())
                    .requires(SINGULARITY_CELL_COMPONENT.get())
                    .requires(ADVANCED_FLUID_CELL_HOUSING.get())
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(ADVANCED_FLUID_CELL_HOUSING.get()))
                    .save(provider))
            .register();

    public static void register() {

    }
}
