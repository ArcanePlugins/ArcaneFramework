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
package me.lokka30.arcaneframework.command;


import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nonnull;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract non-sealed class BaseCommand extends Command {

    private final JavaPlugin plugin;
    private final String label;
    private final Set<SubCommand> subcommands = new HashSet<>();

    public BaseCommand(
        final JavaPlugin plugin,
        final String label
    ) {
        this.label = label;
        this.plugin = plugin;
    }

    public void register() {
        final PluginCommand pc = plugin.getCommand(getLabel());

        if(pc == null) {
            throw new IllegalStateException(
                "Unable to get PluginCommand for '" + getLabel() + "'. Internal plugin.yml may " +
                    "be incorrect (possibly user modified?)."
            );
        }

        pc.setExecutor(unwrap());
    }

    @Nonnull
    private String[] convertFromBukkitArgs(
        final @Nonnull String bukkitLabel,
        final @Nonnull String[] bukkitArgs
    ) {
        Objects.requireNonNull(bukkitLabel, "bukkitLabel");
        Objects.requireNonNull(bukkitArgs, "bukkitArgs");

        final String[] wrapperArgs = new String[bukkitArgs.length + 1];
        wrapperArgs[0] = "/" + bukkitLabel;
        System.arraycopy(bukkitArgs, 0, wrapperArgs, 1, bukkitArgs.length);
        return wrapperArgs;
    }

    @Nonnull
    private TabExecutor unwrap() {
        return new TabExecutor() {
            @Override
            public boolean onCommand(
                final @Nonnull CommandSender sender,
                final @Nonnull org.bukkit.command.Command cmd,
                final @Nonnull String label,
                final @Nonnull String[] args
            ) {
                Objects.requireNonNull(sender, "sender");
                Objects.requireNonNull(cmd, "cmd");
                Objects.requireNonNull(label, "label");
                Objects.requireNonNull(args, "args");

                run(sender, convertFromBukkitArgs(label, args));
                return false;
            }

            @Override
            @Nonnull
            public List<String> onTabComplete(
                final @Nonnull CommandSender sender,
                final @Nonnull org.bukkit.command.Command cmd,
                final @Nonnull String label,
                final @Nonnull String[] args
            ) {
                Objects.requireNonNull(sender, "sender");
                Objects.requireNonNull(cmd, "cmd");
                Objects.requireNonNull(label, "label");
                Objects.requireNonNull(args, "args");

                return suggest(sender, convertFromBukkitArgs(label, args));
            }
        };
    }

    @Nonnull
    public String getLabel() {
        return label;
    }

    @Nonnull
    public Set<SubCommand> getSubcommands() {
        return subcommands;
    }

    //TODO
}
