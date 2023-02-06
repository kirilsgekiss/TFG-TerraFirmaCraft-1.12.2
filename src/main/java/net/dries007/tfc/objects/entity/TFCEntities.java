/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.objects.entity.animal.*;
import net.dries007.tfc.objects.entity.projectile.TFCEntityThrownJavelin;
import net.dries007.tfc.util.Helpers;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DataSerializerEntry;

import javax.annotation.Nonnull;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class TFCEntities {
    @GameRegistry.ObjectHolder(MOD_ID + ":long")
    public static final DataSerializerEntry LONG_DATA_SERIALIZER_ENTRY = Helpers.getNull();

    private static final DataSerializer<Long> LONG_DATA_SERIALIZER = new DataSerializer<Long>() {
        public void write(PacketBuffer buf, @Nonnull Long value) {
            buf.writeLong(value);
        }

        public Long read(PacketBuffer buf) {
            return buf.readLong();
        }

        public DataParameter<Long> createKey(int id) {
            return new DataParameter<>(id, this);
        }

        @Nonnull
        public Long copyValue(@Nonnull Long value) {
            return value;
        }
    };

    private static int id = 1; // don't use id 0, it's easier to debug if something goes wrong

    @SuppressWarnings("unchecked")
    public static DataSerializer<Long> getLongDataSerializer() {
        return (DataSerializer<Long>) LONG_DATA_SERIALIZER_ENTRY.getSerializer();
    }

    @SubscribeEvent
    public static void registerDataSerializers(RegistryEvent.Register<DataSerializerEntry> event) {
        event.getRegistry().register(new DataSerializerEntry(LONG_DATA_SERIALIZER).setRegistryName("long"));
    }

    public static void preInit() {
        register("sitblock", TFCEntitySeatOn.class);
        register("falling_block", TFCEntityFallingBlock.class);
        register("thrown_javelin", TFCEntityThrownJavelin.class);
        register("boat", TFCEntityBoat.class);
        registerLiving("sheeptfc", TFCEntitySheep.class, 0xFFFFFF, 0xFF6347);
        registerLiving("cowtfc", TFCEntityCow.class, 0xA52A2A, 0xFFFFFF);
        registerLiving("grizzlybeartfc", TFCEntityGrizzlyBear.class, 0xB22222, 0xDEB887);
        registerLiving("chickentfc", TFCEntityChicken.class, 0x557755, 0xFFF91F);
        registerLiving("pheasanttfc", TFCEntityPheasant.class, 0x5577FF, 0xFFFA90);
        registerLiving("deertfc", TFCEntityDeer.class, 0x55FF55, 0x5FFAAF);
        registerLiving("pigtfc", TFCEntityPig.class, 0xAA7722, 0xFFEBCD);
        registerLiving("wolftfc", TFCEntityWolf.class, 0xB0ACAC, 0x796555);
        registerLiving("rabbittfc", TFCEntityRabbit.class, 0x885040, 0x462612);
        registerLiving("horsetfc", TFCEntityHorse.class, 0xA5886B, 0xABA400);
        registerLiving("donkeytfc", TFCEntityDonkey.class, 0x493C32, 0x756659);
        registerLiving("muletfc", TFCEntityMule.class, 0x180200, 0x482D1A);
        registerLiving("polarbeartfc", TFCEntityPolarBear.class, 0xF1FFF1, 0xA0A0A0);
        registerLiving("parrottfc", TFCEntityParrot.class, 0x885040, 0xB0ACAC);
        registerLiving("llamatfc", TFCEntityLlama.class, 0xA52A2A, 0xAA7722);
        registerLiving("ocelottfc", TFCEntityOcelot.class, 0x3527FA, 0x7F23A0);
        registerLiving("panthertfc", TFCEntityPanther.class, 0x000066, 0x000000);
        registerLiving("ducktfc", TFCEntityDuck.class, 0xFFF91F, 0x462612);
        registerLiving("alpacatfc", TFCEntityAlpaca.class, 0x00CC66, 0x006633);
        registerLiving("goattfc", TFCEntityGoat.class, 0xA0A0A0, 0x404040);
        registerLiving("sabertoothtfc", TFCEntitySaberTooth.class, 0xFF8000, 0xFFD700);
        registerLiving("cameltfc", TFCEntityCamel.class, 0xA5886B, 0x006633);
        registerLiving("liontfc", TFCEntityLion.class, 0xDAA520, 0xA0522D);
        registerLiving("hyenatfc", TFCEntityHyena.class, 0x666600, 0x331900);
        registerLiving("direwolftfc", TFCEntityDireWolf.class, 0x343434, 0x978f7e);
        registerLiving("haretfc", TFCEntityHare.class, 0x866724, 0xDADADA);
        registerLiving("boartfc", TFCEntityBoar.class, 0x463c09, 0xe39ad8);
        registerLiving("zebutfc", TFCEntityZebu.class, 0x2c2507, 0xbcb38e);
        registerLiving("gazelletfc", TFCEntityGazelle.class, 0xa9a76f, 0xc0ab55);
        registerLiving("wildebeesttfc", TFCEntityWildebeest.class, 0x696142, 0x9c8115);
        registerLiving("quailtfc", TFCEntityQuail.class, 0x237ddc, 0xe3e36d);
        registerLiving("grousetfc", TFCEntityGrouse.class, 0xf7a100, 0x71ffd0);
        registerLiving("mongoosetfc", TFCEntityMongoose.class, 0xf9f50f, 0x90ec7f);
        registerLiving("turkeytfc", TFCEntityTurkey.class, 0xad1d1d, 0xeaa659);
        registerLiving("jackaltfc", TFCEntityJackal.class, 0xb8762b, 0xffffff);
        registerLiving("muskoxtfc", TFCEntityMuskOx.class, 0x620d55, 0xcdaf4f);
        registerLiving("yaktfc", TFCEntityYak.class, 0x837669, 0x3e3d7cc);
        registerLiving("blackbeartfc", TFCEntityBlackBear.class, 0x000000, 0xa18f6c);
        registerLiving("cougartfc", TFCEntityCougar.class, 0x817a00, 0xdcd889);
        registerLiving("coyotetfc", TFCEntityCoyote.class, 0xb7bc88, 0xdac213);
        registerLiving("silkmothtfcf", TFCEntitySilkMoth.class, 0xDBDBD8, 0xF8F8F3);
    }

    private static void register(String name, Class<? extends Entity> cls) {
        EntityRegistry.registerModEntity(new ResourceLocation(MOD_ID, name), cls, name, id++, TerraFirmaCraft.getInstance(), 160, 20, true);
    }

    private static void registerLiving(String name, Class<? extends Entity> cls, int eggPrimaryColor, int eggSecondaryColor) {
        //Register entity and create a spawn egg for creative
        EntityRegistry.registerModEntity(new ResourceLocation(MOD_ID, name), cls, name, id++, TerraFirmaCraft.getInstance(), 80, 3, true, eggPrimaryColor, eggSecondaryColor);
    }
}