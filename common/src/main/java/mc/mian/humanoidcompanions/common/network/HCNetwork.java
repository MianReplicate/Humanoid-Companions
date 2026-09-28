package mc.mian.humanoidcompanions.common.network;

import mc.mian.humanoidcompanions.common.network.custom.*;
import mc.mian.humanoidcompanions.platform.Services;

public class HCNetwork {
    public static void register(){
        Services.NETWORK.registerPacket(ClearTargetPayload.TYPE, new PacketData<>(ClearTargetPayload.STREAM_CODEC, ClearTargetPayload::handle));
        Services.NETWORK.registerPacket(OpenInventoryPayload.TYPE, new PacketData<>(OpenInventoryPayload.STREAM_CODEC, OpenInventoryPayload::handle));
        Services.NETWORK.registerPacket(ReleasePayload.TYPE, new PacketData<>(ReleasePayload.STREAM_CODEC, ReleasePayload::handle));
        Services.NETWORK.registerPacket(SetAlertPayload.TYPE, new PacketData<>(SetAlertPayload.STREAM_CODEC, SetAlertPayload::handle));
        Services.NETWORK.registerPacket(SetHuntingPayload.TYPE, new PacketData<>(SetHuntingPayload.STREAM_CODEC, SetHuntingPayload::handle));
        Services.NETWORK.registerPacket(SetPatrollingPayload.TYPE, new PacketData<>(SetPatrollingPayload.STREAM_CODEC, SetPatrollingPayload::handle));
        Services.NETWORK.registerPacket(SetStationaryPayload.TYPE, new PacketData<>(SetStationaryPayload.STREAM_CODEC, SetStationaryPayload::handle));
    }
}
