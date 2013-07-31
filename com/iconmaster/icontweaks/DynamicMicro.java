package com.iconmaster.icontweaks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import codechicken.lib.lighting.LightMatrix;
import codechicken.lib.render.Vertex5;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry.IMicroMaterial;
import codechicken.microblock.Microblock;

//public class DynamicMicro implements IMicroMaterial {
public class DynamicMicro extends BlockMicroMaterial {
	
	public Block target;
	
	public DynamicMicro(Block target) {
		super(target, 0);
		this.target = target;
	}
}
