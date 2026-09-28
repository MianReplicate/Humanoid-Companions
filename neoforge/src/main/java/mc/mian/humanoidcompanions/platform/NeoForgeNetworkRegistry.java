package mc.mian.humanoidcompanions.platform;


import mc.mian.humanoidcompanions.common.network.Context;
import mc.mian.humanoidcompanions.common.network.NetworkRegistry;
import mc.mian.humanoidcompanions.common.network.Side;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.ClientPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.function.Consumer;

public class NeoForgeNetworkRegistry extends NetworkRegistry {
    @Override
    public void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {
        PacketDistributor.sendToServer(payload);
    }

    public static void registerPayload(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(HCConstants.MOD_ID);
        ((NeoForgeNetworkRegistry)Services.NETWORK).typesToData.forEach((type, data) ->
                registrar.optional().playBidirectional((CustomPacketPayload.Type)type, (StreamCodec<? super RegistryFriendlyByteBuf, ? extends CustomPacketPayload>) data.streamCodec(),
                        (payload, context) ->
                                data.handler().accept(new Context(context instanceof ClientPayloadContext ? Side.CLIENT : Side.SERVER, payload, context instanceof ClientPayloadContext ? null : (ServerPlayer) context.player(), context instanceof ServerPlayer ? Services.PLATFORM.getServer() : null))));
    }
}