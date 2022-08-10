package me.lokka30.arcaneframework.file.config;

import javax.annotation.Nonnull;
import me.lokka30.arcaneframework.file.ExternalFile;
import me.lokka30.arcaneframework.file.VersionedFile;
import org.bukkit.plugin.Plugin;

abstract class ConfigFile extends ExternalFile implements VersionedFile {

    public ConfigFile(
        @Nonnull Plugin plugin,
        @Nonnull String fileName
    ) {
        super(plugin, fileName);
    }
}
