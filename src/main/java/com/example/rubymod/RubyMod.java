package com.example.rubymod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RubyMod implements ModInitializer {
    public static final String MOD_ID = "rubymod";

    public static final Item RUBY = new Item(new FabricItemSettings());
    public static final Block RUBY_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK));
    public static final Item RUBY_BLOCK_ITEM = new BlockItem(RUBY_BLOCK, new FabricItemSettings());
    public static final Item RUBY_WAND = new RubyWandItem(new FabricItemSettings().maxCount(1));

    public static final ItemGroup RUBY_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RUBY))
            .displayName(Text.translatable("itemGroup.rubymod"))
            .entries((context, entries) -> {
                entries.add(RUBY);
                entries.add(RUBY_BLOCK_ITEM);
                entries.add(RUBY_WAND);
            })
            .build();

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "ruby"), RUBY);
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "ruby_block"), RUBY_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "ruby_block"), RUBY_BLOCK_ITEM);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "ruby_wand"), RUBY_WAND);
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "main"), RUBY_GROUP);
    }
}
