package com.aqupd.aqcaracal.events.client;

import com.aqupd.aqcaracal.Caracal;
import com.aqupd.aqcaracal.client.renderer.CaracalEntityRenderer;
import com.aqupd.aqcaracal.client.renderer.model.CaracalEntityModel;
import com.aqupd.aqcaracal.setup.Registration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Caracal.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    private ClientModEvents(){
    }

    @SubscribeEvent
    public static void RegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CaracalEntityModel.LAYER_LOCATION, CaracalEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void RegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Registration.CARACAL.get(), CaracalEntityRenderer::new);
    }
}
