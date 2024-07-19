package cn.dancingsnow.bigger_ae2.client;

import appeng.client.render.SimpleModelLoader;
import appeng.client.render.crafting.CraftingCubeModel;
import cn.dancingsnow.bigger_ae2.BiggerAE2Base;
import cn.dancingsnow.bigger_ae2.block.ModCraftingUnitType;
import cn.dancingsnow.bigger_ae2.init.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.CLIENT)
public class BiggerAE2Client extends BiggerAE2Base {
    public BiggerAE2Client() {
        super();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(BiggerAE2Client::setRenderLayer);
        modEventBus.addListener(BiggerAE2Client::initCraftingUnitModels);
    }

    private static void initCraftingUnitModels(ModelEvent.RegisterGeometryLoaders event) {
        for (ModCraftingUnitType type : ModCraftingUnitType.values()) {
            System.out.println("block/crafting/" + type.getAffix() + "_formed");
            event.register(
                    "block/crafting/" + type.getAffix() + "_formed",
                    new SimpleModelLoader<>(() -> new CraftingCubeModel(new ModCraftingUnitModelProvider(type)))
            );
        }
    }

    private static void setRenderLayer(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ACCELERATOR_4.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ACCELERATOR_16.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ACCELERATOR_64.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ACCELERATOR_256.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ACCELERATOR_1024.get(), RenderType.cutout());
    }
}
