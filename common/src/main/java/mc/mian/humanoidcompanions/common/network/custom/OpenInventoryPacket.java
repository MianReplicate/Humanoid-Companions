package mc.mian.humanoidcompanions.common.network.custom;

import commonnetwork.networking.data.PacketContext;
import commonnetwork.networking.data.Side;
import mc.mian.humanoidcompanions.common.client.CompanionScreen;
import mc.mian.humanoidcompanions.common.container.CompanionContainer;
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

public class OpenInventoryPacket {
    public static final ResourceLocation CHANNEL = HCUtil.modLoc("open_inventory");
    public static final StreamCodec<FriendlyByteBuf, OpenInventoryPacket> STREAM_CODEC =
            StreamCodec.ofMember(OpenInventoryPacket::encode, OpenInventoryPacket::new);

    public static CustomPacketPayload.Type<CustomPacketPayload> type(){
        return new CustomPacketPayload.Type<>(CHANNEL);
    }

    private final int id;
    private final int size;
    private final int entityId;

    public OpenInventoryPacket(int id, int size, int entityId){
        this.id = id;
        this.size = size;
        this.entityId = entityId;
    }

    public OpenInventoryPacket(FriendlyByteBuf buf){
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

    public static void handle(PacketContext<OpenInventoryPacket> ctx)
    {
        if(ctx.side() == Side.CLIENT){
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                Entity entity = player.level().getEntity(ctx.message().getEntityId());
                if (entity instanceof AbstractHumanCompanionEntity) {
                    AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) entity;
                    LocalPlayer client = Minecraft.getInstance().player;
                    CompanionContainer container = new CompanionContainer(ctx.message().getId(), player.getInventory(), companion.inventory);
                    client.containerMenu = container;
                    Minecraft.getInstance().setScreen(new CompanionScreen(container, player.getInventory(), companion));
                }
            }
        }
    }
}
