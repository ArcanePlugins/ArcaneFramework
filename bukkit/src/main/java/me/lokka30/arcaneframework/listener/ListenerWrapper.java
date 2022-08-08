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
package me.lokka30.arcaneframework.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public abstract class ListenerWrapper implements Listener {

    private final boolean imperative;

    public ListenerWrapper(
        final boolean imperative
    ) {
        this.imperative = imperative;
    }

    public ListenerWrapper() {
        this.imperative = false;
    }

    public void register(final Plugin plugin) {
        final PluginManager pm = Bukkit.getPluginManager();

        try {
            pm.registerEvents(this, plugin);
        } catch(Exception ex) {
            if(isImperative())
                throw ex;
        }
    }

    public boolean isImperative() {
        return imperative;
    }
}
