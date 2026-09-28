package mc.mian.humanoidcompanions.platform;

import mc.mian.humanoidcompanions.common.network.Context;
import mc.mian.humanoidcompanions.common.network.NetworkRegistry;
import mc.mian.humanoidcompanions.common.network.PacketData;
import mc.mian.humanoidcompanions.common.network.Side;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Consumer;

public class FabricNetworkRegistry extends NetworkRegistry {
    @Override
    public <T extends CustomPacketPayload> void registerPacket(CustomPacketPayload.Type<T> type, PacketData<T> data) {
        super.registerPacket(type, data);

        PayloadTypeRegistry.playS2C().register(type, data.streamCodec());
        PayloadTypeRegistry.playC2S().register(type, data.streamCodec());
    }

    public void registerServer(){
        typesToData.forEach((type, data) -> ServerPlayNetworking.registerGlobalReceiver(type, (payload, context) ->
                data.handler().accept(new Context(Side.SERVER, payload, context.player(), context.server()))));
    }

    public void registerClient(){
        typesToData.forEach((type, data) -> ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) ->
                data.handler().accept(new Context(Side.CLIENT, payload, null, null))));
    }

    @Override
    public void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {
        ClientPlayNetworking.send(payload);
    }
}