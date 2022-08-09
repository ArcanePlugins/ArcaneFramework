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
package me.lokka30.arcaneframework.util.logging;

import java.util.Objects;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("unused")
public abstract class LogWrapperBase {

    private final Logger logger;

    public LogWrapperBase(final @Nonnull Plugin plugin) {
        Objects.requireNonNull(plugin, "plugin");

        this.logger = plugin.getLogger();
    }

    public void log(final @Nonnull LogLevel level, final @Nonnull String msg) {
        switch(level) {
            case INFO -> getLogger().info(msg);
            case WARNING -> getLogger().warning(msg);
            case SEVERE -> getLogger().severe(msg);
        }
    }

    public void debugLog(final @Nonnull String tag, final @Nonnull String msg) {
        if(!listensForDebugTag(tag)) return;
        getLogger().info("[DEBUG - " + tag + "]: " + msg);
    }

    protected abstract boolean listensForDebugTag(final @Nonnull String tag);

    @Nonnull
    public Logger getLogger() {
        return logger;
    }

}
