package mc.mian.humanoidcompanions.common.network;

import commonnetwork.api.Network;
import mc.mian.humanoidcompanions.common.network.custom.*;

public class HCNetwork {
    public static void register(){
        Network.registerPacket(
                ClearTargetPacket.type(),
                ClearTargetPacket.class,
                ClearTargetPacket.STREAM_CODEC,
                ClearTargetPacket::handle
        );
        Network.registerPacket(
                OpenInventoryPacket.type(),
                OpenInventoryPacket.class,
                OpenInventoryPacket.STREAM_CODEC,
                OpenInventoryPacket::handle
        );
        Network.registerPacket(
                ReleasePacket.type(),
                ReleasePacket.class,
                ReleasePacket.STREAM_CODEC,
                ReleasePacket::handle
        );
        Network.registerPacket(
                SetAlertPacket.type(),
                SetAlertPacket.class,
                SetAlertPacket.STREAM_CODEC,
                SetAlertPacket::handle
        );
        Network.registerPacket(
                SetHuntingPacket.type(),
                SetHuntingPacket.class,
                SetHuntingPacket.STREAM_CODEC,
                SetHuntingPacket::handle
        );
        Network.registerPacket(
                SetPatrollingPacket.type(),
                SetPatrollingPacket.class,
                SetPatrollingPacket.STREAM_CODEC,
                SetPatrollingPacket::handle
        );
        Network.registerPacket(
                SetStationaryPacket.type(),
                SetStationaryPacket.class,
                SetStationaryPacket.STREAM_CODEC,
                SetStationaryPacket::handle
        );
    }
}
