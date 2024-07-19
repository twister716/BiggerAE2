package cn.dancingsnow.bigger_ae2.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {

    public static final CreativeModeTab TAB = new CreativeModeTab("bigger_ae2") {
        @Override
        public ItemStack makeIcon() {
            return ModItems.SINGULARITY_ITEM_CELL.asStack();
        }
    };
}
