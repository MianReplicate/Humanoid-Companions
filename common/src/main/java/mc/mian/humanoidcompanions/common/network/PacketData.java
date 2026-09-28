package mc.mian.humanoidcompanions.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.function.Consumer;

public record PacketData<T extends CustomPacketPayload>(
        StreamCodec<FriendlyByteBuf, T> streamCodec,
        Consumer<Context<T>> handler
) {}