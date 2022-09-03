package me.lokka30.arcaneframework.file.data;

import javax.annotation.Nonnull;
import me.lokka30.arcaneframework.file.ExternalFile;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("unused")
abstract class DataFile extends ExternalFile {

    public DataFile(
        @Nonnull final Plugin plugin,
        @Nonnull final String fileName
    ) {
        super(plugin, fileName);
    }

    public DataFile(
        @Nonnull final Plugin plugin,
        @Nonnull final String fileName,
        @Nonnull final String relativePath
    ) {
        super(plugin, fileName, relativePath);
    }

}
