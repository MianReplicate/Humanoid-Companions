package mc.mian.humanoidcompanions.platform.services;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.Collection;

public interface IEventHelper {
    void postOpenContainer(ServerPlayer player, AbstractContainerMenu menu);
}