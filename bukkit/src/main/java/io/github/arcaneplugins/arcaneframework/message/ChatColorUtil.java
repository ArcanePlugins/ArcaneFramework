package io.github.arcaneplugins.arcaneframework.message;

import static net.md_5.bungee.api.ChatColor.COLOR_CHAR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.md_5.bungee.api.ChatColor;

/**
 * Utilities for colorizing legacy-style chat messages.
 *
 * @author  Lachlan Adamson
 * @version 1
 * @see     ChatColor
 * @since   0.1.0
 */
@SuppressWarnings("unused")
public final class ChatColorUtil {

    public static final Pattern HEX_PATTERN = Pattern.compile("#([A-Fa-f0-9]{6})");

    /*
    This is a modified version of Elementeral's Hex Chat Color Code translator.

    View original code (and thread):
    <https://www.spigotmc.org/threads/hex-chat-class.449300/>

    Elementeral's profile on SpigotMC.org:
    <https://www.spigotmc.org/members/elementeral.717560/>
     */
    @Nonnull
    public static String translateHexColors(final @Nullable String msg) {
        if(msg == null) return "";

        final Matcher matcher = HEX_PATTERN.matcher(msg);
        final StringBuilder builder = new StringBuilder(msg.length() + 4 * 8);
        while (matcher.find()) {
            final String group = matcher.group(1);

            matcher.appendReplacement(builder, COLOR_CHAR + "x"
                + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(builder).toString();
    }

    @Nonnull
    public static String translateStdColors(final @Nullable String msg) {
        if(msg == null) return "";

        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @Nonnull
    public static String translateAllColors(final @Nullable String msg) {
        if(msg == null) return "";
        return translateHexColors(translateStdColors(msg));
    }

}
