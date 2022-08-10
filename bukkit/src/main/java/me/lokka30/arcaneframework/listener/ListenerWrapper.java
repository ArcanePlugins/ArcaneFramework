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

import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/**
 * The ListenerWrapper class allows for Listener-type classes extending it to gain additional
 * runtime safety by stating whether it is imperative or not for the plugin to register. It also
 * offers a simple method to register the listener using the Bukkit Plugin Manager.
 *
 * @author  Lachlan Adamson
 * @version 1
 * @see     Listener
 * @since   0.1.0
 */
@SuppressWarnings("unused")
public abstract class ListenerWrapper implements Listener {

    private final Plugin plugin;
    private final boolean imperative;

    /**
     * Construct a ListenerWrapper.
     * <p>
     * A plugin reference is linked to each listener wrapper so ArcaneFramework knows which plugin
     * the listener wrapper should be registered to.
     * <p>
     * Imperative listeners *must* be registered for the plugin to function correctly.
     * Non-imperative listeners don't *have* to be registered for the plugin to function correctly.
     * For example, a listener which might run on a niche selection of servers (e.g. by software or
     * version) could be considered non-imperative.
     * It is recommended that most other listeners are imperative.
     *
     * @param plugin associated plugin for the underlying listener
     * @param imperative whether the underlying listener is imperative
     */
    public ListenerWrapper(
        final Plugin plugin,
        final boolean imperative
    ) {
        this.plugin = plugin;
        this.imperative = imperative;
    }

    /**
     * Attempt to register this listener, factoring in whether it is imperative or not.
     *
     * @see ListenerWrapper#isImperative()
     */
    public void register() {
        try {
            Bukkit.getPluginManager().registerEvents(this, getPlugin());
        } catch(Exception ex) {
            if(isImperative()) throw ex;
        }
    }

    /**
     * @return associated plugin for the underlying listener
     */
    @Nonnull
    public Plugin getPlugin() { return plugin; }

    /**
     * @return whether the underlying listener is imperative
     */
    public boolean isImperative() {
        return imperative;
    }
}
