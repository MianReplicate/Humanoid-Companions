package mc.mian.humanoidcompanions.platform.services;

import mc.mian.humanoidcompanions.common.network.Context;
import mc.mian.humanoidcompanions.common.network.PacketData;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;
import java.util.function.Consumer;

public interface INetworkRegistry {
    <T extends CustomPacketPayload> void registerPacket(CustomPacketPayload.Type<T> type, PacketData<T> data);
    void sendToPlayer(ServerPlayer player, CustomPacketPayload payload);
    void sendToServer(CustomPacketPayload payload);

    default void sendToPlayers(List<ServerPlayer> players, CustomPacketPayload payload){
        for(ServerPlayer player: players){
            sendToPlayer(player, payload);
        }
    }
}