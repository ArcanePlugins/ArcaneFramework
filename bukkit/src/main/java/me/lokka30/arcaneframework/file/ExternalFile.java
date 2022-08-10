package me.lokka30.arcaneframework.file;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("unused")
public abstract class ExternalFile {

    private final Plugin plugin;
    private final String fileName;
    private final Path path;

    public ExternalFile(
        final @Nonnull Plugin plugin,
        final @Nonnull String fileName
    ) {
        this.plugin = Objects.requireNonNull(plugin, "plugin");
        this.fileName = Objects.requireNonNull(fileName, "fileName");

        this.path = Path.of(plugin.getDataFolder() + File.separator + getFileName());
    }

    public abstract void load();

    public void saveIfNotExists() {
        if(getPath().toFile().exists()) return;
        getPlugin().saveResource(getFileName(), false);
    }

    @Nonnull
    public Plugin getPlugin() {
        return plugin;
    }

    @Nonnull
    public String getFileName() {
        return fileName;
    }

    @Nonnull
    public Path getPath() {
        return path;
    }
}
