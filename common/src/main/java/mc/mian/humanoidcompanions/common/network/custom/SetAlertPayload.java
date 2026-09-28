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

public class SetAlertPayload implements CustomPacketPayload {
    public static final ResourceLocation CHANNEL = HCUtil.modLoc("set_alert");
    public static final StreamCodec<FriendlyByteBuf, SetAlertPayload> STREAM_CODEC =
            StreamCodec.ofMember(SetAlertPayload::encode, SetAlertPayload::new);

    public static final CustomPacketPayload.Type<SetAlertPayload> TYPE = new CustomPacketPayload.Type<>(CHANNEL);

    @Override
    public CustomPacketPayload.Type<SetAlertPayload> type(){
        return TYPE;
    }

    private final int entityId;

    public SetAlertPayload(int id){
        entityId = id;
    }

    public SetAlertPayload(FriendlyByteBuf buf){
        entityId = buf.readInt();
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(this.entityId);
    }

    public static void handle(Context<SetAlertPayload> ctx)
    {
        ServerPlayer player = ctx.sender();
        if (player != null && player.level() instanceof ServerLevel level) {
            Entity entity = level.getEntity(ctx.message().getEntityId());
            if (entity instanceof AbstractHumanCompanionEntity companion) {
                companion.setAlert(!companion.isAlert());
                if (companion.isAlert()) {
                    companion.addAlertGoals();
                } else {
                    companion.removeAlertGoals();
                }
            }
        }
    }
}
