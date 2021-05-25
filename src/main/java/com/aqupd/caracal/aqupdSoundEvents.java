package com.aqupd.caracal;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class aqupdSoundEvents {
    public static final SoundEvent CARACAL_AMBIENT = register("aqupd:caracal_scream");
    public static final SoundEvent CARACAL_HISS = register("aqupd:caracal_hiss");

    private static SoundEvent register(String id) {
        return (SoundEvent) Registry.register(Registry.SOUND_EVENT, (String)id, new SoundEvent(new Identifier(id)));
    }
}
