package com.aqupd.aqcaracal;

import com.aqupd.aqcaracal.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Caracal.MODID)
public class Caracal {
    public static final String MODID = "aqcaracal";

    public Caracal() {
        Registration.init();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
