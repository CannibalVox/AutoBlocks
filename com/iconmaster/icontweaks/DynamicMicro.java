package com.iconmaster.icontweaks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import codechicken.lib.lighting.LC;
import codechicken.lib.lighting.LightMatrix;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.IUVTransformation;
import codechicken.lib.render.UV;
import codechicken.lib.render.Vertex5;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry.IMicroMaterial;
import codechicken.microblock.Microblock;

//public class DynamicMicro implements IMicroMaterial {
public class DynamicMicro extends BlockMicroMaterial {
	
	//public Block target;
	
	public DynamicMicro(Block target, int meta) {
		super(target, meta);
		//this.target = target;
		this.loadIcons();
	}
	
	@Override
	public void renderMicroFace(Vertex5[] verts,int side,Vector3 pos,LightMatrix lightMatrix,int color, IUVTransformation uvt) {
		if (uvt == null) { return; }
		super.renderMicroFace(verts,side,pos,lightMatrix,color,uvt);
	}
}
