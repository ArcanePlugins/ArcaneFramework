package me.lokka30.arcaneframework.support;

import java.util.Locale;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

/**
 * Class which provides a 'Support Checker Builder' for use when a plugin enables to report whether
 * the user's server configuration is seemingly supported. This class also provides several static
 * constants which you can use to check whether a server is running 'at least' a specific Minecraft
 * version, or whether they are running certain server software (or derivates of such).
 *
 * @author  Lachlan Adamson
 * @version 1
 * @since   0.1.0
 */
@SuppressWarnings("unused")
public class SupportChecker {

    /**
     * Whether the server is running the SpigotMC server software (or any derivative).
     */
    public static boolean SPIGOTMC_OR_DERIVATIVE = hasClass("net.md_5.bungee.api.ChatColor");

    /**
     * Whether the server is running the PaperMC server software (or any derivative).
     */
    public static boolean PAPERMC_OR_DERIVATIVE = hasClass("com.destroystokyo.paper.ParticleBuilder");

    /**
     * Whether the server is running MC 1.7 or newer.
     */
    public static boolean V1_7_OR_NEWER = hasMaterial("WHITE_STAINED_GLASS");

    /**
     * Whether the server is running MC 1.8 or newer.
     */
    public static boolean V1_8_OR_NEWER = hasMaterial("PRISMARINE");

    /**
     * Whether the server is running MC 1.9 or newer.
     */
    public static boolean V1_9_OR_NEWER = hasMaterial("END_ROD");

    /**
     * Whether the server is running MC 1.10 or newer.
     */
    public static boolean V1_10_OR_NEWER = hasMaterial("MAGMA_BLOCK");

    /**
     * Whether the server is running MC 1.11 or newer.
     */
    public static boolean V1_11_OR_NEWER = hasMaterial("OBSERVER");

    /**
     * Whether the server is running MC 1.12 or newer.
     */
    public static boolean V1_12_OR_NEWER = hasMaterial("WHITE_CONCRETE");

    /**
     * Whether the server is running MC 1.13 or newer.
     */
    public static boolean V1_13_OR_NEWER = hasEntityType("TURTLE");

    /**
     * Whether the server is running MC 1.14 or newer.
     */
    public static boolean V1_14_OR_NEWER = hasEntityType("PILLAGER");

    /**
     * Whether the server is running MC 1.15 or newer.
     */
    public static boolean V1_15_OR_NEWER = hasEntityType("BEE");

    /**
     * Whether the server is running MC 1.16 or newer.
     */
    public static boolean V1_16_OR_NEWER = hasEntityType("PIGLIN");

    /**
     * Whether the server is running MC 1.17 or newer.
     */
    public static boolean V1_17_OR_NEWER = hasEntityType("AXOLOTL");

    /**
     * Whether the server is running MC 1.18 or newer.
     */
    public static boolean V1_18_OR_NEWER = hasBiome("GROVE");

    /**
     * Whether the server is running MC 1.19 or newer.
     */
    public static boolean V1_19_OR_NEWER = hasEntityType("WARDEN");

    private final Plugin plugin;

    public SupportChecker(
        final @Nonnull Plugin plugin
    ) {
        this.plugin = plugin;

        getPlugin().getLogger().info("Running compatibility checker.");
    }

    @Nonnull
    public SupportChecker withMinSupportedCraftVer(
        final boolean hasVer,
        final @Nonnull String verName
    ) {
        if(hasVer)
            return this;

        warn("You are not eligible to receive official support for this resource, as you " +
            "are not running the minimum supported Minecraft version '" + verName + "'.");

        return this;
    }

    @Nonnull
    public SupportChecker withMinCompatibleCraftVer(
        final boolean hasVer,
        final @Nonnull String verName
    ) {
        if(hasVer)
            return this;

        warn("This plugin may be incompatible with your server's Minecraft version. " +
            "This plugin requires at least '" + verName + "'. Check the resource page for more " +
            "information.");

        return this;
    }

    @Nonnull
    public SupportChecker withUnsupportedlasspaths(
        final @Nonnull UnsupportedClasspath... ucs
    ) {
        for(final UnsupportedClasspath uc : ucs) {
            if(!uc.findsMatch()) continue;

            warn("Identified '" + uc.getIdentifier() + "' on your server: this may cause " +
                "issues. Reason: " + uc.getReason());
        }

        return this;
    }

    @Nonnull
    public SupportChecker withMinSupportedJavaVer(final float javaVer) {
        float currentJavaVer;

        try {
            currentJavaVer = Float.parseFloat(
                System.getProperty("java.specification.version")
            );
        } catch (NumberFormatException ignored) {
            return this;
        }

        if(javaVer >= currentJavaVer)
            return this;

        warn("You are running an unsupported (though, still compatible) Java version. " +
            "Consider updating Java to improve performance and allow plugins to use new features.");

        return this;
    }

    private void warn(final String msg) {
        getPlugin().getLogger().warning(msg);
    }

    @Nonnull
    public Plugin getPlugin() {
        return plugin;
    }

    private record UnsupportedClasspath(
        @Nonnull String identifier,
        @Nonnull String reason,
        @Nonnull String... classpaths
    ) {
        public boolean findsMatch() {
            for(final String classpath : getClasspaths())
                if(hasClass(classpath)) return true;

            return false;
        }

        @Nonnull
        public String getIdentifier() {
            return identifier();
        }

        @Nonnull
        public String getReason() {
            return reason();
        }

        @Nonnull
        public String[] getClasspaths() {
            return classpaths();
        }
    }

    /**
     * Checks whether a given EntityType exists.
     *
     * @param entityTypeStr EntityType (as string)
     * @return whether the given EntityType exists.
     */
    private static boolean hasEntityType(final String entityTypeStr) {
        try {
            EntityType.valueOf(entityTypeStr.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether a given Biome exists.
     *
     * @param biomeStr Biome (as string)
     * @return whether the given Biome exists.
     */
    @SuppressWarnings("SameParameterValue")
    private static boolean hasBiome(final String biomeStr) {
        try {
            Biome.valueOf(biomeStr.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether a given Material exists.
     *
     * @param materialStr Material (as string)
     * @return whether the given Material exists.
     */
    private static boolean hasMaterial(final String materialStr) {
        try {
            Material.valueOf(materialStr.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether a given Class exists.
     *
     * @param classpath classpath
     * @return whether the given Class exists.
     */
    private static boolean hasClass(final @Nonnull String classpath) {
        try {
            Class.forName(classpath);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }

}
