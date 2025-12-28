package mc.mian.humanoidcompanions.common.client.model;

import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.InteractionHand;

public class CompanionModel<T extends AbstractHumanCompanionEntity> extends PlayerModel<T> {
    public CompanionModel(ModelPart root, boolean slim) {
        super(root, slim);
    }

    @Override
    protected void setupAttackAnimation(T livingEntity, float ageInTicks) {
        super.setupAttackAnimation(livingEntity, ageInTicks);
        ArmPose pose = livingEntity.getArmPose(InteractionHand.MAIN_HAND);
        if(livingEntity.getTarget() != null){
            if(pose == ArmPose.ITEM){
                AnimationUtils.swingWeaponDown(this.rightArm, this.leftArm, livingEntity, this.attackTime, ageInTicks);
            } else if(pose == ArmPose.EMPTY){
                AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.attackTime, ageInTicks);
            }
        } else {
            AnimationUtils.bobArms(this.rightArm, this.leftArm, ageInTicks);
        }
    }
}
