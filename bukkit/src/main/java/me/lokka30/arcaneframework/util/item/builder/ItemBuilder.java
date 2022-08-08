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
package me.lokka30.arcaneframework.util.item.builder;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public final class ItemBuilder {

    private @Nonnull Material type = Material.AIR;
    private int amount = 1;
    private @Nullable Integer damage = null;
    private @Nonnull Set<Enchantment> enchantments = new HashSet<>();

    private @Nullable String displayName = null;
    private @Nullable String localizedName = null;
    private @Nonnull List<String> lore = new ArrayList<>();
    private @Nullable Integer customModelData = null;
    private boolean unbreakable = false;

    private @Nonnull EnumSet<BuilderSettings> builderSettings = EnumSet.noneOf(BuilderSettings.class);

    //TODO
}
