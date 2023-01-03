package io.github.arcaneplugins.arcaneframework.file.config;

import io.github.arcaneplugins.arcaneframework.file.ExternalFile;
import io.github.arcaneplugins.arcaneframework.file.VersionedFile;
import javax.annotation.Nonnull;
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
