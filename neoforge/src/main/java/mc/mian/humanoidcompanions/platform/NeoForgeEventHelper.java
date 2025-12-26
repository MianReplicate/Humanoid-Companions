package mc.mian.humanoidcompanions.platform;

import mc.mian.humanoidcompanions.platform.services.IEventHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;

public class NeoForgeEventHelper implements IEventHelper {

    @Override
    public void postOpenContainer(ServerPlayer player, AbstractContainerMenu menu) {
        NeoForge.EVENT_BUS.post(new PlayerContainerEvent.Open(player, player.containerMenu));
    }
}
