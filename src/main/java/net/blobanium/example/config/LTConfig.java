package net.blobanium.example.config;

import net.blobanium.example.LoadingTimer;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.util.fabric.UtilsImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* ATTENTION!
// This Is The Example from the autoconfig wiki by shedaniel
// I Will change this once I Get the config file working
// Once i get the config working i will change this class
*/



public class LTConfig {

    @Config(name = "loadingtimer")
    public class ModConfig implements ConfigData {
        boolean insanePrecision = true;
        boolean toggleB = false;
    }

    public static void init() {
        try {
            ConfigHolder<ModConfig> holder = AutoConfig.register(ModConfig.class, (GsonConfigSerializer::new));
            ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        } catch (RuntimeException e) {
            createFile();
            ConfigHolder<ModConfig> holder = AutoConfig.register(ModConfig.class, (GsonConfigSerializer::new));
            ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
            System.out.println("A Config File should be generated, however at this state it is not working yet");
        }
    }

    public static void createFile() {
        File file = new File(UtilsImpl.getConfigFolder() + "/loadingtimer.json");
        System.out.println(file);
        System.out.println(UtilsImpl.getConfigFolder());
        try{
            FileWriter myWriter = new FileWriter(UtilsImpl.getConfigFolder() + "/loadingtimer.json");
            myWriter.write("{ }");
            myWriter.close();
        } catch (IOException e) {
            System.err.println("Something caused Config Creation to fail");
            e.printStackTrace();
        }
    }
}