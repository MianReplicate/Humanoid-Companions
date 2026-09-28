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

public class SetHuntingPayload implements CustomPacketPayload {
    public static final ResourceLocation CHANNEL = HCUtil.modLoc("set_hunting");
    public static final StreamCodec<FriendlyByteBuf, SetHuntingPayload> STREAM_CODEC =
            StreamCodec.ofMember(SetHuntingPayload::encode, SetHuntingPayload::new);

    public static final CustomPacketPayload.Type<SetHuntingPayload> TYPE = new CustomPacketPayload.Type<>(CHANNEL);

    @Override
    public CustomPacketPayload.Type<SetHuntingPayload> type(){
        return TYPE;
    }

    private final int entityId;

    public SetHuntingPayload(int entityId){
        this.entityId = entityId;
    }

    public SetHuntingPayload(FriendlyByteBuf buf){
        entityId = buf.readInt();
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(this.entityId);
    }

    public static void handle(Context<SetHuntingPayload> ctx)
    {
        ServerPlayer player = ctx.sender();
        if (player != null && player.level() instanceof ServerLevel level) {
            Entity entity = level.getEntity(ctx.message().getEntityId());
            if (entity instanceof AbstractHumanCompanionEntity companion) {
                companion.setHunting(!companion.isHunting());
                if (companion.isHunting()) {
                    companion.addHuntingGoals();
                } else {
                    companion.removeHuntingGoals();
                }
            }
        }
    }
}
