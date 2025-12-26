package mc.mian.humanoidcompanions.common.network.custom;

import commonnetwork.networking.data.PacketContext;
import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import mc.mian.humanoidcompanions.common.util.HCUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class SetStationaryPacket {
    public static final ResourceLocation CHANNEL = HCUtil.modLoc("set_stationary");
    public static final StreamCodec<FriendlyByteBuf, SetStationaryPacket> STREAM_CODEC =
            StreamCodec.ofMember(SetStationaryPacket::encode, SetStationaryPacket::new);

    public static CustomPacketPayload.Type<CustomPacketPayload> type(){
        return new CustomPacketPayload.Type<>(CHANNEL);
    }

    private final int entityId;

    public SetStationaryPacket(int entityId){
        this.entityId = entityId;
    }

    public SetStationaryPacket(FriendlyByteBuf buf){
        entityId = buf.readInt();
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(this.entityId);
    }

    public static void handle(PacketContext<SetStationaryPacket> ctx)
    {
        ServerPlayer player = ctx.sender();
        if (player != null && player.level() instanceof ServerLevel level) {
            Entity entity = level.getEntity(ctx.message().getEntityId());
            if (entity instanceof AbstractHumanCompanionEntity companion) {
                companion.setStationary(!companion.isStationary());
            }
        }
    }
}
