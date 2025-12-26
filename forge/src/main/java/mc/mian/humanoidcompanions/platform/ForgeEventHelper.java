package mc.mian.humanoidcompanions.platform;

import mc.mian.humanoidcompanions.platform.services.IEventHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;

public class ForgeEventHelper implements IEventHelper {

    @Override
    public void postOpenContainer(ServerPlayer player, AbstractContainerMenu menu) {
        MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(player, player.containerMenu));
    }
}
