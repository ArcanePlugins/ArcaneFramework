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
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.Material;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("unused")
public final class ItemBuilder {

    private Material type;
    private int amount = 1;
    private int damage = 0;
    private final Collection<Enchantment> enchantments = new HashSet<>();
    private final Collection<ItemFlag> itemFlags = EnumSet.noneOf(ItemFlag.class);
    private String displayName = null;
    private String localizedName = null;
    private final List<String> lore = new ArrayList<>();
    private Integer customModelData = null;
    private final Collection<AttributeModifier> attributeModifiers = new HashSet<>();
    private boolean unbreakable = false;
    private final EnumSet<BuilderSettings> builderSettings = EnumSet.noneOf(BuilderSettings.class);

    public ItemBuilder(
        final @Nonnull Material type
    ) {
        this.type = type;
    }

    @Nonnull
    public ItemStack build() {
        final ItemStack stack = new ItemStack(getType(), getAmount());

        if(getBuilderSettings().contains(BuilderSettings.UNSAFE_ENCHANTMENTS)) {
            stack.addUnsafeEnchantments(getEnchantments());
        } else {
            stack.addEnchantments(getEnchantments());
        }

        /*
        For any operations which do not require ItemMeta, do them above, not below this comment.
        Bukkit allows some ItemStacks to have no item meta, so this must be obeyed.
         */

        final ItemMeta meta = stack.getItemMeta();
        if(meta == null) return stack;

        if(meta instanceof Damageable dMeta) {
            dMeta.setDamage(getDamage());
        }

        meta.setDisplayName(getDisplayName());
        meta.setLocalizedName(getLocalizedName());
        meta.setLore(getLore());
        meta.setCustomModelData(getCustomModelData());
        meta.setUnbreakable(isUnbreakable());
        meta.setAttributeModifiers(getAttributeModifiers());
        meta.addItemFlags(getItemFlags().toArray(new ItemFlag[0]));

        return stack;
    }

    @Nonnull
    public ItemBuilder withType(final @Nonnull Material type) {
        this.type = type;
        return this;
    }

    @Nonnull
    public Material getType() {
        return type;
    }

    @Nonnull
    public ItemBuilder withAmount(final int amount) {
        this.amount = amount;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    @Nonnull
    public ItemBuilder withDamage(final int damage) {
        this.damage = damage;
        return this;
    }

    public int getDamage() {
        return damage;
    }

    @Nonnull
    public ItemBuilder withAddedEnchantments(final @Nonnull Collection<Enchantment> enchantments) {
        getEnchantments().addAll(enchantments);
        return this;
    }

    @Nonnull
    public ItemBuilder withAddedEnchantments(final @Nonnull Enchantment... enchantments) {
        Collections.addAll(getEnchantments(), enchantments);
        return this;
    }

    @Nonnull
    public ItemBuilder withNewEnchantments(final @Nonnull Collection<Enchantment> enchantments) {
        getEnchantments().clear();
        return withAddedEnchantments(enchantments);
    }

    @Nonnull
    public ItemBuilder withNewEnchantments(final @Nonnull Enchantment... enchantments) {
        getEnchantments().clear();
        return withAddedEnchantments(enchantments);
    }

    @Nonnull
    public Collection<Enchantment> getEnchantments() {
        return enchantments;
    }

    @Nonnull
    public ItemBuilder withAddedItemFlags(final @Nonnull Collection<ItemFlag> itemFlags) {
        getItemFlags().addAll(itemFlags);
        return this;
    }

    @Nonnull
    public ItemBuilder withAddedItemFlags(final @Nonnull ItemFlag... itemFlags) {
        Collections.addAll(getItemFlags(), itemFlags);
        return this;
    }

    @Nonnull
    public ItemBuilder withNewItemFlags(final @Nonnull Collection<ItemFlag> itemFlags) {
        getEnchantments().clear();
        return withAddedItemFlags(itemFlags);
    }

    @Nonnull
    public ItemBuilder withNewItemFlags(final @Nonnull ItemFlag... itemFlags) {
        getEnchantments().clear();
        return withAddedItemFlags(itemFlags);
    }

    @Nonnull
    public Collection<ItemFlag> getItemFlags() {
        return itemFlags;
    }

    @Nonnull
    public ItemBuilder withDisplayName(final @Nullable String displayName) {
        this.displayName = displayName;
        return this;
    }

    @Nullable
    public String getDisplayName() {
        return displayName;
    }

    @Nonnull
    public ItemBuilder withLocalizedName(final @Nullable String localizedName) {
        this.localizedName = localizedName;
        return this;
    }

    @Nullable
    public String getLocalizedName() {
        return localizedName;
    }

    @Nonnull
    public ItemBuilder withNewLore(final @Nonnull Collection<String> lore) {
        getLore().clear();
        return withAddedLore(lore);
    }

    @Nonnull
    public ItemBuilder withNewLore(final @Nonnull String... lore) {
        getLore().clear();
        return withAddedLore(lore);
    }

    @Nonnull
    public ItemBuilder withAddedLore(final @Nonnull Collection<String> lore) {
        getLore().addAll(lore);
        return this;
    }

    @Nonnull
    public ItemBuilder withAddedLore(final @Nonnull String... lore) {
        Collections.addAll(getLore(), lore);
        return this;
    }

    @Nonnull
    public List<String> getLore() {
        return lore;
    }

    @Nonnull
    public ItemBuilder withCustomModelData(final @Nullable Integer customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    @Nullable
    public Integer getCustomModelData() {
        return customModelData;
    }

    @Nonnull
    public ItemBuilder withAddedAttributeModifiers(final Collection<AttributeModifier> attributeModifiers) {
        getAttributeModifiers().addAll(attributeModifiers);
        return this;
    }

    @Nonnull
    public ItemBuilder withAddedAttributeModifiers(final AttributeModifier... attributeModifiers) {
        Collections.addAll(getAttributeModifiers(), attributeModifiers);
        return this;
    }

    @Nonnull
    public ItemBuilder withNewAttributeModifiers(final Collection<AttributeModifier> attributeModifiers) {
        getBuilderSettings().clear();
        return withAddedAttributeModifiers(attributeModifiers);
    }

    @Nonnull
    public ItemBuilder withNewAttributeModifiers(final AttributeModifier... attributeModifiers) {
        getBuilderSettings().clear();
        return withAddedAttributeModifiers(attributeModifiers);
    }

    @Nonnull
    public Collection<AttributeModifier> getAttributeModifiers() {
        return attributeModifiers;
    }

    @Nonnull
    public ItemBuilder withUnbreakability(final boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    @Nonnull
    public ItemBuilder withAddedBuilderSettings(final Collection<BuilderSettings> builderSettings) {
        getBuilderSettings().addAll(builderSettings);
        return this;
    }

    @Nonnull
    public ItemBuilder withAddedBuilderSettings(final BuilderSettings... builderSettings) {
        Collections.addAll(getBuilderSettings(), builderSettings);
        return this;
    }

    @Nonnull
    public ItemBuilder withNewBuilderSettings(final Collection<BuilderSettings> builderSettings) {
        getBuilderSettings().clear();
        return withAddedBuilderSettings(builderSettings);
    }

    @Nonnull
    public ItemBuilder withNewBuilderSettings(final BuilderSettings... builderSettings) {
        getBuilderSettings().clear();
        return withAddedBuilderSettings(builderSettings);
    }

    @Nonnull
    public Collection<BuilderSettings> getBuilderSettings() {
        return builderSettings;
    }

    //TODO
}
