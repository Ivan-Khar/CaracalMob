package com.aqupd.caracal;

import com.aqupd.caracal.setup.CaracalEntities;
import com.aqupd.caracal.setup.CaracalItems;
import com.aqupd.caracal.setup.CaracalSounds;
import com.aqupd.caracal.setup.CaracalSpawnRules;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CaracalMain.MODID)
public class CaracalMain {

  public static final String MODID = "aqupdcaracal";

  public CaracalMain() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    CaracalSounds.init(bus);
    CaracalEntities.init(bus);
    CaracalSpawnRules.init(bus);
    CaracalItems.init(bus);
  }

}
