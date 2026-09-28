package mc.mian.humanoidcompanions.common.network.custom;

import mc.mian.humanoidcompanions.common.client.CompanionScreen;
import mc.mian.humanoidcompanions.common.menu.CompanionMenu;
import mc.mian.humanoidcompanions.common.network.Context;
import mc.mian.humanoidcompanions.common.network.Side;
import mc.mian.humanoidcompanions.common.util.HCUtil;
import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class OpenInventoryPayload implements CustomPacketPayload {
    public static final ResourceLocation CHANNEL = HCUtil.modLoc("open_inventory");
    public static final CustomPacketPayload.Type<OpenInventoryPayload> TYPE = new CustomPacketPayload.Type<>(CHANNEL);
    public static final StreamCodec<FriendlyByteBuf, OpenInventoryPayload> STREAM_CODEC =
            StreamCodec.ofMember(OpenInventoryPayload::encode, OpenInventoryPayload::new);

    @Override
    public CustomPacketPayload.Type<OpenInventoryPayload> type(){
        return TYPE;
    }

    private final int id;
    private final int size;
    private final int entityId;

    public OpenInventoryPayload(int id, int size, int entityId){
        this.id = id;
        this.size = size;
        this.entityId = entityId;
    }

    public OpenInventoryPayload(FriendlyByteBuf buf){
        id = buf.readByte();
        size = buf.readVarInt();
        entityId = buf.readInt();
    }

    public int getId() {
        return this.id;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeByte(this.id);
        buf.writeVarInt(this.size);
        buf.writeInt(this.entityId);
    }

    public static void handle(Context<OpenInventoryPayload> ctx)
    {
        if(ctx.side() == Side.CLIENT){
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                Entity entity = player.level().getEntity(ctx.message().getEntityId());
                if (entity instanceof AbstractHumanCompanionEntity) {
                    AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                    LocalPlayer client = Minecraft.getInstance().player;
                    CompanionMenu container = new CompanionMenu(ctx.message().getId(), player.getInventory(), companion.inventory);
                    client.containerMenu = container;
                    Minecraft.getInstance().setScreen(new CompanionScreen(container, player.getInventory(), companion));
                }
            }
        }
    }
}
