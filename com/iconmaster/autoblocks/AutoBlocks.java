package com.iconmaster.autoblocks;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import scala.collection.Seq;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "AutoBlocks", name = "AutoBlocks", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class AutoBlocks {
    @Instance("IconTweaks")
    public static AutoBlocks instance;
    
    @SidedProxy(modId="AutoBlocks",clientSide="com.iconmaster.autoblocks.client.ClientProxy", serverSide="com.iconmaster.autoblocks.CommonProxy")
    public static CommonProxy proxy;

	private boolean addClear;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
            // Stub Method
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	this.addClear = config.get("General","Add Clear Blocks",false).getBoolean(false);
    	config.save();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
            proxy.registerRenderers();
            for (int id=159;id<4096;id++) {
            	if (Block.blocksList[id] != null && (addClear || Block.blocksList[id].isOpaqueCube()) && MicroMaterialRegistry.materialID(Block.blocksList[id].getUnlocalizedName())==0) {
            		ArrayList metas = new ArrayList();
            		Block.blocksList[id].getSubBlocks(id,null,metas);
            		for (int meta = 0;meta<=metas.size()-1;meta++) {
	            		try {
	            			System.out.println("[AUTOBLOCKS] Registering ID " + id + ":" + meta);
	            			MicroMaterialRegistry.registerMaterial(new DynamicMicro(Block.blocksList[id],meta), Block.blocksList[id].getUnlocalizedName()+"_"+meta);
	            		} catch (Exception ex) {
	            			
	            		}
            		}
            	}
            }
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    }
}
