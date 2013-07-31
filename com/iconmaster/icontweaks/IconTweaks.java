package com.iconmaster.icontweaks;


import java.util.ArrayList;
import java.util.List;

import scala.collection.Seq;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
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

@Mod(modid = "IconTweaks", name = "IconTweaks", version = "dev1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class IconTweaks {
    @Instance("IconTweaks")
    public static IconTweaks instance;
    
    @SidedProxy(modId="IconTweaks",clientSide="com.iconmaster.icontweaks.client.ClientProxy", serverSide="com.iconmaster.icontweaks.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
            // Stub Method
    	
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
            proxy.registerRenderers();
            for (int id=159;id<4096;id++) {
            	if (Block.blocksList[id] != null && Block.blocksList[id].isOpaqueCube() && MicroMaterialRegistry.materialID(Block.blocksList[id].getUnlocalizedName())==0) {
            		ArrayList metas = new ArrayList();
            		Block.blocksList[id].getSubBlocks(id,null,metas);
            		for (int meta = 0;meta<=metas.size()-1;meta++) {
	            		try {
	            			System.out.println("[ICONTWEAKS] Registering ID " + id + ":" + meta);
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
