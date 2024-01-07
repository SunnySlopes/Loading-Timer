package io.github.blobanium.lt.mixin;

import io.github.blobanium.lt.LoadingTimer;
import io.github.blobanium.lt.config.ConfigReader;
import io.github.blobanium.lt.util.math.MathUtil;
import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Most of this is inspired from Dashloader by alphaqu, Dashloader uses the LPGL3 License.

@Mixin(Bootstrap.class)
public class BootstrapMixin {
    @Unique
    private static long bootstrapTime;

    @Inject(at = @At("HEAD"), method = "initialize")
    private static void initializeStart(CallbackInfo ci){
        if(ConfigReader.insanePrecision){
            bootstrapTime = System.nanoTime();
        } else {
            bootstrapTime = System.currentTimeMillis();
        }
    }

    @Inject(at = @At("TAIL"), method = "initialize")
    private static void initializeEnd(CallbackInfo ci){
        if(ConfigReader.insanePrecision){
            LoadingTimer.finalBootstrapTime = MathUtil.roundValue(System.nanoTime() - bootstrapTime) / 1000000000;
        } else {
            LoadingTimer.finalBootstrapTime = MathUtil.roundValue(System.currentTimeMillis() - bootstrapTime) / 1000;
        }

        LoadingTimer.LOGGER.info ("Bootstrap Time: " + LoadingTimer.finalBootstrapTime + "s");
    }
}
