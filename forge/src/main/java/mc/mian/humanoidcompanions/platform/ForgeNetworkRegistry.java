package mc.mian.humanoidcompanions.platform;

import mc.mian.humanoidcompanions.common.network.Context;
import mc.mian.humanoidcompanions.common.network.NetworkRegistry;
import mc.mian.humanoidcompanions.common.network.PacketData;
import mc.mian.humanoidcompanions.common.network.Side;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.payload.PayloadFlow;
import org.checkerframework.checker.signature.qual.Identifier;

import java.util.function.Consumer;

public class ForgeNetworkRegistry extends NetworkRegistry {
    private static final int PROTOCOL_VERSION = 0;
    public static Channel<CustomPacketPayload> CHANNEL;
    public static PayloadFlow<FriendlyByteBuf, CustomPacketPayload> CHANNEL_BUILDER = ChannelBuilder
            .named(ResourceLocation.fromNamespaceAndPath(HCConstants.MOD_ID, "main"))
            .networkProtocolVersion(PROTOCOL_VERSION)
            .optional()
            .payloadChannel()
            .play()
            .login()
            .bidirectional();
    public void build() {
        CHANNEL = CHANNEL_BUILDER.build();
    }
    @Override
    public <T extends CustomPacketPayload> void registerPacket(CustomPacketPayload.Type<T> type, PacketData<T> data) {
        super.registerPacket(type, data);

        CHANNEL_BUILDER = CHANNEL_BUILDER.add(type, data.streamCodec(), (payload, context) ->
                data.handler().accept(new Context(context.isClientSide() ? Side.CLIENT : Side.SERVER, payload, context.getSender(), context.isServerSide() ? Services.PLATFORM.getServer() : null)));
    }
    @Override
    public void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {
        CHANNEL.send(payload, PacketDistributor.PLAYER.with(player));
    }

    @Override
    public void sendToServer(CustomPacketPayload payload) {
        CHANNEL.send(payload, PacketDistributor.SERVER.noArg());
    }
}