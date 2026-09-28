package mc.mian.humanoidcompanions.common.network.custom;

import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import mc.mian.humanoidcompanions.common.network.Context;
import mc.mian.humanoidcompanions.common.util.HCUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class ClearTargetPayload implements CustomPacketPayload {
    public static final ResourceLocation CHANNEL = HCUtil.modLoc("clear_target");
    public static final CustomPacketPayload.Type<ClearTargetPayload> TYPE = new CustomPacketPayload.Type<>(CHANNEL);
    public static final StreamCodec<FriendlyByteBuf, ClearTargetPayload> STREAM_CODEC =
            StreamCodec.ofMember(ClearTargetPayload::encode, ClearTargetPayload::new);

    @Override
    public CustomPacketPayload.Type<ClearTargetPayload> type(){
        return TYPE;
    }

    private final int entityId;

    public ClearTargetPayload(int id){
        entityId = id;
    }

    public ClearTargetPayload(FriendlyByteBuf buf){
        entityId = buf.readInt();
    }

    public int getEntityId(){
        return entityId;
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(this.entityId);
    }

    public static void handle(Context<ClearTargetPayload> ctx)
    {
        if (ctx.message() != null) {
            ServerPlayer player = ctx.sender();
            if (player != null && player.level() instanceof ServerLevel) {
                Entity entity = player.level().getEntity(ctx.message().getEntityId());
                if (entity instanceof AbstractHumanCompanionEntity) {
                    AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                    companion.clearTarget();
                }
            }
        }
    }
}
