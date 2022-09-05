package me.lokka30.arcaneframework.file.config;

import javax.annotation.Nonnull;
import me.lokka30.arcaneframework.file.ExternalFile;
import me.lokka30.arcaneframework.file.VersionedFile;
import org.bukkit.plugin.Plugin;

/**
 * Represents a configuration file of no particular file format.
 *
 * @author  Lachlan Adamson
 * @version 1
 * @since   0.1.0
 */
@SuppressWarnings("unused")
abstract class ConfigFile extends ExternalFile implements VersionedFile {

    public ConfigFile(
        @Nonnull final Plugin plugin,
        @Nonnull final String fileName
    ) {
        super(plugin, fileName);
    }

    public ConfigFile(
        @Nonnull final Plugin plugin,
        @Nonnull final String fileName,
        @Nonnull final String relativePath
    ) {
        super(plugin, fileName, relativePath);
    }

}
