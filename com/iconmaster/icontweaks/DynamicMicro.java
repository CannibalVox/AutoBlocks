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
	
//	public Block target;
	
	public DynamicMicro(Block target, int meta) {
		super(target, meta);
//		this.target = target;
	}
	
	@Override
	public void renderMicroFace(Vertex5[] verts,int side,Vector3 pos,LightMatrix lightMatrix,int color, IUVTransformation uvt) {
		if (uvt == null) { return; }
        UV uv = new UV();
        Tessellator t = Tessellator.instance;
        int i = 0;
        while(i < 4)
        {
            if(CCRenderState.useNormals())
            {
               Vector3 n = Rotation.axes[side%6];
                t.setNormal((float)n.x, (float)n.y, (float)n.z);
            }
            Vertex5 vert = verts[i];
            if(lightMatrix != null)
            {
                LC lc = LC.computeO(vert.vec, side);
                if(CCRenderState.useModelColours());
                    lightMatrix.setColour(t, lc, color);
                lightMatrix.setBrightness(t, lc);
            }
            else 
            {
                if(CCRenderState.useModelColours());
                    CCRenderState.vertexColour(color);
            }
            uvt.transform(uv.set(vert.uv));
            t.addVertexWithUV(vert.vec.x+pos.x, vert.vec.y+pos.y, vert.vec.z+pos.z, uv.u, uv.v);
            i++;
        }
	}
}
