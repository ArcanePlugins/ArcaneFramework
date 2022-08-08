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
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public abstract non-sealed class SubCommand extends Command {

    private final BaseCommand baseCommand;
    private final SubCommand parentSubCommand;
    private final Set<SubCommand> subCommands = new HashSet<>();

    public SubCommand(
        final BaseCommand baseCommand,
        final SubCommand parentSubCommand
    ) {
        this.baseCommand = Objects.requireNonNull(baseCommand, "baseCommand");
        this.parentSubCommand = Objects.requireNonNull(parentSubCommand, "parentSubCommand");
    }

    public SubCommand(
        final BaseCommand baseCommand
    ) {
        this.baseCommand = baseCommand;
        this.parentSubCommand = null;
    }

    @Nonnull
    public BaseCommand getBaseCommand() {
        return baseCommand;
    }

    @Nonnull
    public Optional<SubCommand> getParentSubCommand() {
        return Optional.ofNullable(parentSubCommand);
    }

    @Nonnull
    public Set<SubCommand> getSubCommands() {
        return subCommands;
    }
}
