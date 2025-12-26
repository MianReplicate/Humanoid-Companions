package mc.mian.humanoidcompanions.common.mixin;

import mc.mian.humanoidcompanions.common.config.HCConfiguration;
import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "die", at = @At("TAIL"))
    public void die(DamageSource source, CallbackInfo ci){
        if(source.getEntity() instanceof AbstractHumanCompanionEntity companion && !companion.level().isClientSide()){
            companion.giveExperiencePoints(((LivingEntity) (Object) this).getExperienceReward((ServerLevel) companion.level(), companion));
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    public void onAttacked(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (source.getEntity() instanceof AbstractHumanCompanionEntity companion && !companion.level().isClientSide() && companion.isTame()) {
            LivingEntity entity = (LivingEntity) (Object) this;
            if (!HCConfiguration.FRIENDLY_FIRE_PLAYER.get()) {
                if (entity instanceof Player player) {
                    if (companion.getOwner() == player) {
                        cir.cancel();
                        return;
                    }
                }
            }
            if (!HCConfiguration.FRIENDLY_FIRE_COMPANIONS.get()) {
                if (entity instanceof TamableAnimal tamedEntity) {
                    if (tamedEntity.isTame()) {
                        LivingEntity owner1 = tamedEntity.getOwner();
                        LivingEntity owner2 = companion.getOwner();
                        if (owner1 == owner2) {
                            cir.cancel();
                        }
                    }
                }
            }
        }
    }
}
