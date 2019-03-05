package com.tumods.adventurecraft.objects.blocks.trees;

import java.util.Random;

import com.tumods.adventurecraft.Main;
import com.tumods.adventurecraft.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLeavesCherry extends BlockLeavesFruiting implements IGrowable {

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
	
	public BlockLeavesCherry(String name) {
		super(name);
		setDefaultState(this.blockState.getBaseState()
				.withProperty(AGE, Integer.valueOf(0))
				.withProperty(CHECK_DECAY, Boolean.valueOf(true))
				.withProperty(DECAYABLE, Boolean.valueOf(true)));
		
		disableStats();
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        
        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (!canFruit(worldIn, pos)) return;
        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = this.getAge(state);

            if (i < this.getMaxAge())
            {
                float f = getGrowthChance(this, worldIn, pos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        }
    }
	
	public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();

        if (i > j)
        {
            i = j;
        }

        worldIn.setBlockState(pos, this.withAge(i), 2);
    }

    protected int getBonemealAgeIncrease(World worldIn)
    {
        return MathHelper.getInt(worldIn.rand, 2, 5);
    }
	
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
    		EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    	super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    	
    	playerIn.swingArm(EnumHand.MAIN_HAND);
    	if (!worldIn.isRemote) {
    		if (this.isMaxAge(state)) {
    			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), 
						new ItemStack(ItemInit.PRODUCE_CHERRY, (new Random().nextInt(3)+1)) ));
				worldIn.setBlockState(pos, this.withAge(0));
				return true;
    		}
    	}
    	return false;
    }
    
//	@Override
//	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
//		super.getDrops(drops, world, pos, state, fortune);
//		int age = getAge(state);
//		Random rand = world instanceof World ? ((World)world).rand : new Random();
//
//        if (age >= getMaxAge())
//        {
//            int k = 3 + fortune;
//
//            for (int i = 0; i < 3 + fortune; ++i)
//            {
//                if (rand.nextInt(2 * getMaxAge()) <= age)
//                {
//                    drops.add(new ItemStack(ItemInit.PRODUCE_CHERRY, MathHelper.getInt(rand, 1, 3), 0));
//                }
//            }
//        }
//	}
	
	protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos) {
		float f = 1.0f;
		
		BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();

        if (flag && flag1)
        {
            f /= 2.0F;
        }
        else
        {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();

            if (flag2)
            {
                f /= 2.0F;
            }
        }

        return f;
    }
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.setFruitByChance(worldIn, pos, MathHelper.getInt(new Random(), 0, 2) >= 1);
	}
	
	/**
     * Whether this IGrowable can grow
     */
	@Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return (!this.isMaxAge(state) && this.canFruit(worldIn, pos));
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return false;
    }
    
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(worldIn, pos, state);
    }
    
    @Override
	protected int getSaplingDropChance(IBlockState state) {return 30;}
	
	public int getMaxAge() {
		return 2;
	}
	
	protected int getAge(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    public IBlockState withAge(int age)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(age));
    }

    public boolean isMaxAge(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue() >= this.getMaxAge();
    }
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {AGE, CAN_FRUIT, CHECK_DECAY, DECAYABLE});
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
