package mc.mian.humanoidcompanions.common.entity.custom.ai;

import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

public class PatrolGoal extends RandomStrollGoal {

    protected final float probability;
    public Vec3 patrolVec;
    public AbstractHumanCompanionEntity companion;
    public int radius;

    public PatrolGoal(AbstractHumanCompanionEntity companion, int interval, int radius) {
        this(companion, 1.0D, 0.001F, interval, radius);
    }

    public PatrolGoal(AbstractHumanCompanionEntity companion, double speedModifier, float probability, int interval,
                      int radius) {
        super(companion, speedModifier);
        this.probability = probability;
        this.companion = companion;
        this.interval = interval;
        this.radius = radius;
    }

    public boolean canUse() {
        if (companion.getPatrolPos() == null || !companion.isPatrolling()) {
            return false;
        }
        this.patrolVec = Vec3.atBottomCenterOf(companion.getPatrolPos());
        return super.canUse();
    }

    
    protected Vec3 getPosition() {
        Vec3 vec = getRawPosition();
        if (vec != null) {
            double distance = vec.distanceTo(patrolVec);
            if (distance > radius) {
                vec = null;
            }
        }
        return vec;
    }

    public Vec3 getRawPosition() {
        if (this.mob.isInWaterOrBubble()) {
            Vec3 vec3 = LandRandomPos.getPos(this.mob, radius, 7);
            return vec3 == null ? super.getPosition() : vec3;
        } else {
            return this.mob.getRandom().nextFloat() >= this.probability ? LandRandomPos.getPos(this.mob, radius, 7) :
                    super.getPosition();
        }
    }
}
