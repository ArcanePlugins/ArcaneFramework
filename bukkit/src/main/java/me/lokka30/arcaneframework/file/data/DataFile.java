package me.lokka30.arcaneframework.file.data;

import javax.annotation.Nonnull;
import me.lokka30.arcaneframework.file.ExternalFile;
import me.lokka30.arcaneframework.file.VersionedFile;
import org.bukkit.plugin.Plugin;

abstract class DataFile extends ExternalFile implements VersionedFile {

    public DataFile(
        @Nonnull Plugin plugin,
        @Nonnull String fileName
    ) {
        super(plugin, fileName);
    }

}
