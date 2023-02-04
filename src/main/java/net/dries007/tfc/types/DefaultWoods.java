/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.types;

import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Wood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = MOD_ID)
public final class DefaultWoods
{

	//Default Tree ResourceLocations
	public static final ResourceLocation ACACIA = new ResourceLocation(MOD_ID, "acacia");
	public static final ResourceLocation ASH = new ResourceLocation(MOD_ID, "ash");
	public static final ResourceLocation ASPEN = new ResourceLocation(MOD_ID, "aspen");

	//Birch Trees
	public static final ResourceLocation BIRCH = new ResourceLocation(MOD_ID, "birch");
	public static final ResourceLocation SILVER_BIRCH = new ResourceLocation(MOD_ID, "silver_birch");

	public static final ResourceLocation BLACKWOOD = new ResourceLocation(MOD_ID, "blackwood");
	public static final ResourceLocation CHESTNUT = new ResourceLocation(MOD_ID, "chestnut");
	public static final ResourceLocation DOUGLAS_FIR = new ResourceLocation(MOD_ID, "douglas_fir");
	public static final ResourceLocation HICKORY = new ResourceLocation(MOD_ID, "hickory");
	public static final ResourceLocation MAPLE = new ResourceLocation(MOD_ID, "maple");

	//Oak Trees
	public static final ResourceLocation OAK = new ResourceLocation(MOD_ID, "oak");
	public static final ResourceLocation DARK_OAK = new ResourceLocation(MOD_ID, "dark_oak");
	
	public static final ResourceLocation PALM = new ResourceLocation(MOD_ID, "palm");
	public static final ResourceLocation PINE = new ResourceLocation(MOD_ID, "pine");
	public static final ResourceLocation ROSEWOOD = new ResourceLocation(MOD_ID, "rosewood");
	public static final ResourceLocation SEQUOIA = new ResourceLocation(MOD_ID, "sequoia");

	//Spruce Trees
	public static final ResourceLocation SPRUCE = new ResourceLocation(MOD_ID, "spruce");
	public static final ResourceLocation JAPANESE_SPRUCE = new ResourceLocation(MOD_ID, "japanese_spruce");
	public static final ResourceLocation NORWAY_SPRUCE = new ResourceLocation(MOD_ID, "norway_spruce");

	public static final ResourceLocation SYCAMORE = new ResourceLocation(MOD_ID, "sycamore");
	public static final ResourceLocation WHITE_CEDAR = new ResourceLocation(MOD_ID, "white_cedar");
	public static final ResourceLocation WHITE_ELM = new ResourceLocation(MOD_ID, "white_elm");
	public static final ResourceLocation WILLOW = new ResourceLocation(MOD_ID, "willow");
	public static final ResourceLocation KAPOK = new ResourceLocation(MOD_ID, "kapok");

	//Hevea Trees
	public static final ResourceLocation HEVEA = new ResourceLocation(MOD_ID, "hevea");
	public static final ResourceLocation RUBBERWOOD = new ResourceLocation(MOD_ID, "rubberwood");

	//Latex Trees
	public static final ResourceLocation SAPODILLA = new ResourceLocation(MOD_ID, "sapodilla");
	public static final ResourceLocation CASTILLA = new ResourceLocation(MOD_ID, "castilla");

	//Manufactured Woods
	public static final ResourceLocation TREATED_WOOD = new ResourceLocation(MOD_ID, "treated_wood");
	public static final ResourceLocation LACQUERED_WOOD = new ResourceLocation(MOD_ID, "lacquered_wood");

	//Misc Wood
	public static final ResourceLocation WOODEN = new ResourceLocation(MOD_ID, "wooden");


	//Simple ITreeGenerator instances.

	@SubscribeEvent
	public static void onPreRegisterWood(TFCRegistryEvent.RegisterPreBlock<Wood> event)
	{
		event.getRegistry().registerAll(

			new Wood.Builder(ACACIA, 650f, 1000).setColor(0x8B3929).build(),
			new Wood.Builder(ASH, 696f, 1250).setColor(0xAE604E).build(),
			new Wood.Builder(ASPEN, 611f, 1000).setColor(0x373727).build(),
			new Wood.Builder(BLACKWOOD, 720f, 1750).setColor(0x1A1A1A).build(),
			new Wood.Builder(CHESTNUT, 651f, 1500).setColor(0x642C1E).setTannin().build(),
			new Wood.Builder(DOUGLAS_FIR, 707f, 1500).setColor(0xD7BC8D).setTannin().build(),
			new Wood.Builder(HICKORY, 762f, 2000).setColor(0x4E3418).setTannin().build(),
			new Wood.Builder(KAPOK, 645f, 1000).build(),
			new Wood.Builder(MAPLE, 745f, 2000).setTannin().build(),

			//Oak Trees
			new Wood.Builder(OAK, 728f, 2250).setTannin().build(),
			new Wood.Builder(DARK_OAK, 728f, 2250).setTannin().build(),

			new Wood.Builder(PALM, 730f, 1250).build(),
			new Wood.Builder(PINE, 627f, 1250).build(),
			new Wood.Builder(ROSEWOOD, 640f, 1500).build(),
			new Wood.Builder(SEQUOIA, 612f, 1750).setTannin().build(),

			//Spruce Trees
			new Wood.Builder(SPRUCE, 608f, 1500).build(),
			new Wood.Builder(NORWAY_SPRUCE, 608f, 1500).build(),
			new Wood.Builder(JAPANESE_SPRUCE, 608f, 1500).build(),
			new Wood.Builder(SYCAMORE, 653f, 1750).build(),

			//Birch Trees
			new Wood.Builder(BIRCH, 652f, 1750).setTannin().build(),
			new Wood.Builder(SILVER_BIRCH, 652f, 1750).setTannin().build(),
			new Wood.Builder(WHITE_CEDAR, 625f, 1500).build(),
			new Wood.Builder(WHITE_ELM, 625f, 1500).build(),
			new Wood.Builder(WILLOW, 603f, 1000).build(),


			//Hevea Trees
			new Wood.Builder(HEVEA, 762f, 2000).build(),
			new Wood.Builder(RUBBERWOOD, 762f, 2000).build(),	//Industrial Byproduct


			//Latex Trees
			new Wood.Builder(SAPODILLA, 762f, 2000).build(),
			new Wood.Builder(CASTILLA, 762f, 2000).build(),

			//Manufactured Woods
			new Wood.Builder(TREATED_WOOD, 762f, 2000).build(),
			new Wood.Builder(LACQUERED_WOOD, 762f, 2000).build(),

			//Misc Wood
			new Wood.Builder(WOODEN, 762f, 2000).build()
		);
	}
}
