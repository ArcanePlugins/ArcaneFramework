package io.github.arcaneplugins.arcaneframework.file;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.bukkit.plugin.Plugin;

/**
 * Represents an 'external' file situated within the implementation's data directory (e.g.,
 * {@code plugins/LevelledMobs/}.
 *
 * @author  lokka30
 * @since   0.1.0
 */
@SuppressWarnings("unused")
public abstract class ExternalFile {

    private final Plugin plugin;
    private final String fileName;
    private final Path dataDirPath;
    private final String relativePath;

    public ExternalFile(
        final @Nonnull Plugin plugin,
        final @Nonnull String fileName
    ) {
        this(plugin, fileName, "");
    }

    public ExternalFile(
        final @Nonnull Plugin plugin,
        final @Nonnull String fileName,
        final @Nonnull String relativePath
    ) {
        Objects.requireNonNull(plugin, "plugin");
        Objects.requireNonNull(fileName, "fileName");
        Objects.requireNonNull(relativePath, "path");

        this.plugin = plugin;
        this.fileName = fileName;
        this.relativePath = relativePath + fileName;
        this.dataDirPath = Path.of(plugin.getDataFolder() + File.separator + relativePath);
    }

    public abstract void load();

    public void saveIfNotExists() {
        if(getDataDirPath().toFile().exists()) return;
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
    public Path getDataDirPath() {
        return dataDirPath;
    }

    @Nonnull
    public String getRelativePath() {
        return relativePath;
    }
}
