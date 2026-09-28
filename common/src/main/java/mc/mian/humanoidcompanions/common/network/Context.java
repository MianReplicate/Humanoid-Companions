package mc.mian.humanoidcompanions.common.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public record Context<T extends CustomPacketPayload>(Side side, T message, ServerPlayer sender, MinecraftServer server) {
}