package io.github.arcaneplugins.arcaneframework.sound;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Easily create portable sound wrapper objects which can play sounds to players (local) or
 * globally, or at certain locations.
 *
 * @author  Lachlan Adamson
 * @version 1
 * @see     Sound
 * @since   0.1.0
 */
@SuppressWarnings("unused")
public class SoundWrapper {

    private Sound sound;
    private float volume;
    private float pitch;

    /**
     * Create a new SoundWrapper object with given sound, volume, and pitch.
     * These values are mutable within the object.
     *
     * @param sound sound to play
     * @param volume volume of the sound
     * @param pitch pitch of the sound
     */
    public SoundWrapper(
        final @Nonnull Sound sound,
        final float volume,
        final float pitch
    ) {
        setSound(sound);
        setVolume(volume);
        setPitch(pitch);
    }

    public void playLocally(final @Nonnull Player player) {
        playLocallyAtLocation(player.getLocation(), player);
    }

    public void playLocally(final @Nonnull Player player1, final @Nonnull Player... players) {
        playLocally(player1);
        for(final Player player : players) {
            playLocally(player);
        }
    }

    public void playLocally(final @Nonnull Iterable<Player> players) {
        for(final Player player : players) {
            playLocally(player);
        }
    }

    public void playLocally(final @Nonnull Player[] players) {
        for(final Player player : players) {
            playLocally(player);
        }
    }

    public void playLocallyAtLocation(final @Nonnull Location location, final @Nonnull Player player) {
        Objects.requireNonNull(location, "location");
        Objects.requireNonNull(player, "player");

        player.playSound(location, getSound(), getVolume(), getPitch());
    }

    public void playLocallyAtLocation(final @Nonnull Location location, final Player player1, final @Nonnull Player... players) {
        playLocallyAtLocation(location, player1);
        for(final Player player : players) {
            playLocallyAtLocation(location, player);
        }
    }

    public void playLocallyAtLocation(final @Nonnull Location location, final @Nonnull Iterable<Player> players) {
        for(final Player player : players) {
            playLocallyAtLocation(location, player);
        }
    }

    public void playLocallyAtLocation(final @Nonnull Location[] locations, final @Nonnull Player[] players) {
        for(final Location location : locations) {
            for(final Player player : players) {
                playLocallyAtLocation(location, player);
            }
        }
    }

    public void playLocallyAtLocation(final @Nonnull Iterable<Location> locations, final @Nonnull Iterable<Player> players) {
        for(final Location location : locations) {
            for(final Player player : players) {
                playLocallyAtLocation(location, player);
            }
        }
    }

    public void playLocallyAtLocation(final @Nonnull Location[] locations, final @Nonnull Player player) {
        for(final Location location : locations) {
            playLocallyAtLocation(location, player);
        }
    }

    public void playGlobally(final @Nonnull Location location) {
        Objects.requireNonNull(location, "location");
        Objects.requireNonNull(location.getWorld(), "location#getWorld()");

        location.getWorld().playSound(location, getSound(), getVolume(), getPitch());
    }

    public void playGlobally(final @Nonnull Location location1, final @Nonnull Location... locations) {
        playGlobally(location1);
        for(final Location location : locations) {
            playGlobally(location);
        }
    }

    public void playGlobally(final @Nonnull Iterable<Location> locations) {
        for(final Location location : locations) {
            playGlobally(location);
        }
    }

    public void playGlobally(final @Nonnull Location[] locations) {
        for(final Location location : locations) {
            playGlobally(location);
        }
    }

    @Nonnull
    public Sound getSound() {
        return sound;
    }

    public void setSound(final @Nonnull Sound sound) {
        Objects.requireNonNull(sound, "sound");
        this.sound = sound;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(final float volume) {
        this.volume = volume;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }

}
