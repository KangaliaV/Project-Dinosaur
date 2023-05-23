package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ProjectDinosaur.MODID);

    // Australovenator
    public static final RegistryObject<SoundEvent> AUSTRALOVENATOR_GROWL = registerSoundEvent("australovenator_growl");
    public static final RegistryObject<SoundEvent> AUSTRALOVENATOR_SNARL = registerSoundEvent("australovenator_snarl");
    public static final RegistryObject<SoundEvent> AUSTRALOVENATOR_HURT = registerSoundEvent("australovenator_hurt");
    public static final RegistryObject<SoundEvent> AUSTRALOVENATOR_DEATH = registerSoundEvent("australovenator_death");

    // Gastornis
    public static final RegistryObject<SoundEvent> GASTORNIS_CALL = registerSoundEvent("gastornis_call");
    public static final RegistryObject<SoundEvent> GASTORNIS_FLAPPING = registerSoundEvent("gastornis_flapping");
    public static final RegistryObject<SoundEvent> GASTORNIS_HISS = registerSoundEvent("gastornis_hiss");
    public static final RegistryObject<SoundEvent> GASTORNIS_HURT = registerSoundEvent("gastornis_hurt");
    public static final RegistryObject<SoundEvent> GASTORNIS_DEATH = registerSoundEvent("gastornis_death");

    // Scelidosaurus
    public static final RegistryObject<SoundEvent> SCELIDOSAURUS_CALL = registerSoundEvent("scelidosaurus_call");
    public static final RegistryObject<SoundEvent> SCELIDOSAURUS_WARNING = registerSoundEvent("scelidosaurus_warning");
    public static final RegistryObject<SoundEvent> SCELIDOSAURUS_HURT = registerSoundEvent("scelidosaurus_hurt");
    public static final RegistryObject<SoundEvent> SCELIDOSAURUS_DEATH = registerSoundEvent("scelidosaurus_death");


    // Helper Methods
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(ProjectDinosaur.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
