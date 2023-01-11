/*
This file was retrived from the ArcaneFramework source code. Learn more
about ArcaneFramework at <https://github.com/lokka30/ArcaneFramework/>.

Copyright © 2022 lokka30 and contributors

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.arcaneplugins.arcaneframework.file.config;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.bukkit.plugin.Plugin;
import org.spongepowered.configurate.BasicConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.gson.GsonConfigurationLoader;

/**
 * Represents a configuration file using the JSON (.json) format.
 *
 * @author  lokka30
 * @since   0.1.0
 */
@SuppressWarnings("unused")
public abstract class JsonConfigFile extends ConfigFile {

    private final GsonConfigurationLoader loader = GsonConfigurationLoader.builder()
        .file(getDataDirPath().toFile())
        .build();

    private BasicConfigurationNode rootNode = null;

    public JsonConfigFile(
        @Nonnull final Plugin plugin,
        @Nonnull final String fileName
    ) {
        super(plugin, fileName);
    }

    public JsonConfigFile(
        @Nonnull final Plugin plugin,
        @Nonnull final String fileName,
        @Nonnull final String relativePath
    ) {
        super(plugin, fileName, relativePath);
    }

    @Override
    public void load() {
        saveIfNotExists();

        try { // workaround annoying checked exception
            rootNode = loader.load();
        } catch (ConfigurateException ex) { throw new RuntimeException(ex); }

        migrate();
    }

    @Nonnull
    public BasicConfigurationNode getRootNode() {
        return Objects.requireNonNull(rootNode, "rootNode");
    }

    @Nonnull
    public GsonConfigurationLoader getLoader() {
        return loader;
    }

    public void save() {
        try { // workaround annoying checked exception
            getLoader().save(getRootNode());
        } catch (ConfigurateException ex) { throw new RuntimeException(ex); }
    }

}
