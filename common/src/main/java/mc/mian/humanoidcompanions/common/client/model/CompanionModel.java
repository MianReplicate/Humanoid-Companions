package mc.mian.humanoidcompanions.common.client.model;

import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;

public class CompanionModel<T extends AbstractHumanCompanionEntity> extends PlayerModel<T> {
    public CompanionModel(ModelPart root, boolean slim) {
        super(root, slim);
    }

    @Override
    protected void setupAttackAnimation(T livingEntity, float ageInTicks) {
        super.setupAttackAnimation(livingEntity, ageInTicks);
        if(this.attackTime > 0F){
            AnimationUtils.swingWeaponDown(this.rightArm, this.leftArm, livingEntity, this.attackTime, ageInTicks);
        }
    }
}
