package mc.mian.humanoidcompanions.common.network;

import com.ibm.icu.impl.Pair;
import mc.mian.humanoidcompanions.platform.services.INetworkRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.HashMap;
import java.util.function.Consumer;

public abstract class NetworkRegistry implements INetworkRegistry {
    public HashMap<CustomPacketPayload.Type<?>, PacketData<?>> typesToData = new HashMap<>();

    @Override
    public <T extends CustomPacketPayload> void registerPacket(CustomPacketPayload.Type<T> type, PacketData<T> data) {
        typesToData.put(type, data);
    }
}