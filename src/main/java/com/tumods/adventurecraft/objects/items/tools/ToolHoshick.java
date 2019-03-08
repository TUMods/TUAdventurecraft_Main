package com.tumods.adventurecraft.objects.items.tools;

import java.util.List;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Sets;
import com.tumods.adventurecraft.Main;
import com.tumods.adventurecraft.init.ItemInit;
import com.tumods.adventurecraft.util.IHasModel;
import com.tumods.adventurecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import scala.swing.event.KeyPressed;

public class ToolHoshick extends ItemTool implements IHasModel {
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, 
			Blocks.DETECTOR_RAIL, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, 
			Blocks.ICE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, 
			Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE,
			Blocks.DIRT, Blocks.SAND, Blocks.CLAY, Blocks.BONE_BLOCK, Blocks.FARMLAND, Blocks.GRAVEL, Blocks.GRASS, Blocks.GRASS_PATH,
			Blocks.HARDENED_CLAY, Blocks.HAY_BLOCK, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND);
	
	public ToolHoshick(String name, ToolMaterial material) {
		super(1.0f, -1.2f, material, EFFECTIVE_ON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.itemTab);
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		Material material = blockIn.getMaterial();
		Block block = blockIn.getBlock();
		if (material == Material.ROCK || material == Material.SNOW || block == Blocks.SNOW_LAYER)
			return true;
		
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			int maxHealth = this.getMaxDamage();
			int currentHealth = maxHealth - (int)(this.getDurabilityForDisplay(stack) * maxHealth);
			double healthPercent = (currentHealth / (double)maxHealth);
			
			String colourFormat = (healthPercent > 0.5) ? Reference.DARK_GREEN : ((healthPercent > 0.25) ? Reference.YELLOW : Reference.DARK_RED);	
			tooltip.add(" Durability: " + colourFormat + String.valueOf(currentHealth) + Reference.GRAY + " / " + String.valueOf(maxHealth));
			
			tooltip.add(Reference.GOLD + "Left Click " + Reference.GRAY + "to dig or mine");
			tooltip.add(Reference.GOLD + "Right Click " + Reference.GRAY + "to till the soil");
			tooltip.add(Reference.GOLD + "Shift + Right Click " + Reference.GRAY + "to dig a path");
		} else {
			tooltip.add("Hold \u00A73Shift\u00A77 for more information");
		}
	}
	
	/**
     * Called when a Block is right-clicked with this Item
     */
    @SuppressWarnings("incomplete-switch")
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	boolean doPathDig = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
            {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
                {
                	if (!doPathDig)
                		this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                	else
                		this.setBlock(itemstack, player, worldIn, pos, Blocks.GRASS_PATH.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                        	if (!doPathDig)
                        		this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                        	else
                        		this.setBlock(itemstack, player, worldIn, pos, Blocks.GRASS_PATH.getDefaultState());
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                    }
                }
            }

            return EnumActionResult.PASS;
        }
    }
    
    protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }
    
    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
    	return super.getDestroySpeed(stack, state);
    }

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
