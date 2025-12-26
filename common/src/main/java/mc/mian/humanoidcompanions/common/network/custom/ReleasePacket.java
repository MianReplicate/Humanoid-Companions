package mc.mian.humanoidcompanions.common.network.custom;

import commonnetwork.networking.data.PacketContext;
import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import mc.mian.humanoidcompanions.common.util.HCUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class ReleasePacket {
    public static final ResourceLocation CHANNEL = HCUtil.modLoc("release");
    public static final StreamCodec<FriendlyByteBuf, ReleasePacket> STREAM_CODEC =
            StreamCodec.ofMember(ReleasePacket::encode, ReleasePacket::new);

    public static CustomPacketPayload.Type<CustomPacketPayload> type(){
        return new CustomPacketPayload.Type<>(CHANNEL);
    }

    private final int entityId;

    public ReleasePacket(int id){
        entityId = id;
    }

    public ReleasePacket(FriendlyByteBuf buf){
        entityId = buf.readInt();
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(this.entityId);
    }

    public static void handle(PacketContext<ReleasePacket> ctx)
    {
        ServerPlayer player = ctx.sender();
        if (player != null && player.level() instanceof ServerLevel) {
            Entity entity = player.level().getEntity(ctx.message().getEntityId());
            if (entity instanceof AbstractHumanCompanionEntity companion) {
                companion.release();
                String message = companion.getDisplayName().getString().split(" ")[0] +
                        " is no longer your companion.";
                player.sendSystemMessage(Component.literal(message));
            }
        }
    }
}
