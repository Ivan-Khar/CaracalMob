package com.aqupd.caracal;

import com.aqupd.caracal.setup.CaracalEntities;
import com.aqupd.caracal.setup.CaracalItems;
import com.aqupd.caracal.setup.CaracalSounds;
import com.aqupd.caracal.setup.CaracalSpawnRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

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
