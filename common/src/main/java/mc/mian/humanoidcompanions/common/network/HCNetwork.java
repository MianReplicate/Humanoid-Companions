package mc.mian.humanoidcompanions.common.network;

import commonnetwork.api.Network;
import mc.mian.humanoidcompanions.common.network.custom.TemplatePacket;

public class HCNetwork {
    public static void register(){
        Network.registerPacket(
                TemplatePacket.type(),
                TemplatePacket.class,
                TemplatePacket.STREAM_CODEC,
                TemplatePacket::handle
        );
    }
}
