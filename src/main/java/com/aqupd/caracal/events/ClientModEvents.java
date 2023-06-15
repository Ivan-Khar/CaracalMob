package com.aqupd.caracal.events;

import com.aqupd.caracal.CaracalMain;
import com.aqupd.caracal.entity.CaracalEntityRenderer;
import com.aqupd.caracal.setup.CaracalEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CaracalMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

  private ClientModEvents(){
  }

  @SubscribeEvent
  public static void RegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
  }

  @SubscribeEvent
  public static void RegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(CaracalEntities.CARACAL.get(), CaracalEntityRenderer::new);
  }
}