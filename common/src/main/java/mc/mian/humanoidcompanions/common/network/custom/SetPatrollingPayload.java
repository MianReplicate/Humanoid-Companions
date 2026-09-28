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

public class SetPatrollingPayload implements CustomPacketPayload {
    public static final ResourceLocation CHANNEL = HCUtil.modLoc("set_patrolling");
    public static final StreamCodec<FriendlyByteBuf, SetPatrollingPayload> STREAM_CODEC =
            StreamCodec.ofMember(SetPatrollingPayload::encode, SetPatrollingPayload::new);

    public static final CustomPacketPayload.Type<SetPatrollingPayload> TYPE = new CustomPacketPayload.Type<>(CHANNEL);

    @Override
    public CustomPacketPayload.Type<SetPatrollingPayload> type(){
        return TYPE;
    }

    private final int entityId;

    public SetPatrollingPayload(int id){
        entityId = id;
    }

    public SetPatrollingPayload(FriendlyByteBuf buf){
        entityId = buf.readInt();
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(this.entityId);
    }

    public static void handle(Context<SetPatrollingPayload> ctx)
    {
        ServerPlayer player = ctx.sender();
        if (player != null && player.level() instanceof ServerLevel level) {
            Entity entity = level.getEntity(ctx.message().getEntityId());
            if (entity instanceof AbstractHumanCompanionEntity companion) {
                if (companion.isFollowing()) {
                    companion.setPatrolling(true);
                    companion.setFollowing(false);
                    companion.setGuarding(false);
                    companion.setPatrolPos(companion.blockPosition());
                } else if (companion.isPatrolling()) {
                    companion.setPatrolling(false);
                    companion.setFollowing(false);
                    companion.setGuarding(true);
                    companion.setPatrolPos(companion.blockPosition());
                } else {
                    companion.setPatrolling(false);
                    companion.setFollowing(true);
                    companion.setGuarding(false);
                }
            }
        }
    }
}
