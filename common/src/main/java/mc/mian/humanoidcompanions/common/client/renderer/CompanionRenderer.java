package mc.mian.humanoidcompanions.common.client.renderer;

import mc.mian.humanoidcompanions.common.client.model.CompanionModel;
import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;


public class CompanionRenderer extends HumanoidMobRenderer<AbstractHumanCompanionEntity, CompanionModel<AbstractHumanCompanionEntity>> {

    public CompanionRenderer(Context context) {
        super(context, new CompanionModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this,
                new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)),
                context.getModelManager()
        ));
    }

    public void render(AbstractHumanCompanionEntity companion, float companionYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        this.setModelProperties(companion);
        super.render(companion, companionYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    private void setModelProperties(AbstractHumanCompanionEntity companion) {
        PlayerModel<AbstractHumanCompanionEntity> companionModel = this.getModel();
        HumanoidModel.ArmPose humanoidmodel$armpose = companion.getArmPose(InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose humanoidmodel$armpose1 = companion.getArmPose(InteractionHand.OFF_HAND);

        if (companion.getMainArm() == HumanoidArm.RIGHT) {
            companionModel.rightArmPose = humanoidmodel$armpose;
            companionModel.leftArmPose = humanoidmodel$armpose1;
        } else {
            companionModel.rightArmPose = humanoidmodel$armpose1;
            companionModel.leftArmPose = humanoidmodel$armpose;
        }
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractHumanCompanionEntity entity) {
        return entity.getResourceLocation();
    }

    protected void scale(AbstractHumanCompanionEntity p_117798_, PoseStack p_117799_, float p_117800_) {
        float f = 0.9375F;
        p_117799_.scale(f, f, f);
    }
}

