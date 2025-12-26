package mc.mian.humanoidcompanions.common.entity.custom.ai;

import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;

public class CustomFollowOwnerGoal extends FollowOwnerGoal {

    public AbstractHumanCompanionEntity companion;

    public CustomFollowOwnerGoal(AbstractHumanCompanionEntity companion, double speedModifier, float startDistance, float stopDistance) {
        super(companion, speedModifier, startDistance, stopDistance);
        this.companion = companion;
    }

    public boolean canUse() {
        if (!companion.isFollowing()) {
            return false;
        }
        return super.canUse();
    }
}
