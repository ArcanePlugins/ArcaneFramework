package io.github.arcaneplugins.arcaneframework.file.data;

import io.github.arcaneplugins.arcaneframework.file.ExternalFile;
import javax.annotation.Nonnull;
import org.bukkit.plugin.Plugin;

/**
 * Represents an external file which is not intended to be modified by inexperienced administrators.
 * {@link DataFile} does not pertain to a specific file format.
 *
 * @author  Lachlan Adamson
 * @version 1
 * @since   0.1.0
 */
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
