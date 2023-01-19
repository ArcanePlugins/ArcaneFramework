package io.github.arcaneplugins.arcaneframework.util;

import java.util.Objects;
import org.bukkit.entity.Player;

/**
 * Represents the bukkit implementation of the {@link Title} class.
 *
 * @author MrIvanPlays
 * @since 0.2
 */
public final class BukkitTitle implements Title<Player> {

    /**
     * Create a new {@code BukkitTitle} instance.
     * 
     * @return new title instance
     */
    public static BukkitTitle of() {
        return new BukkitTitle();
    }

    private BukkitTitle() {
    }

    private String title, subTitle;
    private int fadeIn = -1, stay = -1, fadeOut = -1;

    /**
     * {@inheritDoc}
     */
    @Override
    public Title<Player> title(String title) {
        this.title = title;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title<Player> subTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title<Player> fadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title<Player> stay(int stay) {
        this.stay = stay;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Title<Player> fadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(Player player) {
        Objects.requireNonNull(player, "player");
        Objects.requireNonNull(this.title, "title not set");
        player.resetTitle(); // reset any previous timings if no timings are specified in this object
        player.sendTitle(this.title, this.subTitle, this.fadeIn, this.stay, this.fadeOut);
    }
}
