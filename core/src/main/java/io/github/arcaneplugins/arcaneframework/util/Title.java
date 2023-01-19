package io.github.arcaneplugins.arcaneframework.util;

import java.util.Collection;

/**
 * Abstract builder class making it easier to create and send titles.
 *
 * @author MrIvanPlays
 * @since 0.2
 */
public interface Title<Player> {

    /**
     * Defines the title to be sent.
     *
     * @param title title
     * @return this instance for chaining
     */
    Title<Player> title(String title);

    /**
     * Defines the subtitle to be sent.
     *
     * @param subTitle sub title
     * @return this instance for chaining
     */
    Title<Player> subTitle(String subTitle);

    /**
     * Defines the fade in time of the title.
     *
     * @param fadeIn fade in time
     * @return this instance for chaining
     */
    Title<Player> fadeIn(int fadeIn);

    /**
     * Defines the time the title stays.
     *
     * @param stay stay time
     * @return this instance for chaining
     */
    Title<Player> stay(int stay);

    /**
     * Defines the fade out time of the title.
     *
     * @param fadeOut fade out time
     * @return this instance for chaining
     */
    Title<Player> fadeOut(int fadeOut);

    /**
     * Send the title to the specified player.
     *
     * @param player the player to send it to
     */
    void send(Player player);

    /**
     * Send the title to the specified players.
     *
     * @param players the players to send it to
     */
    default void send(Player[] players) {
        if (players == null || players.length == 0) {
            return;
        }
        for (Player player : players) {
            if (player == null) {
                continue;
            }
            this.send(player);
        }
    }

    /**
     * Send this title to the specified players.
     *
     * @param players the players to send it to
     */
    default void send(Collection<Player> players) {
        if (players == null || players.isEmpty()) {
            return;
        }
        for (Player player : players) {
            if (player == null) {
                continue;
            }
            this.send(player);
        }
    }
}
