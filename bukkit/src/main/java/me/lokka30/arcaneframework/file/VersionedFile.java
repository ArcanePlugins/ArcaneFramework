package me.lokka30.arcaneframework.file;

/**
 * Classes implementing this interface represents that its file is versioned. Versioned files have
 * a metadata section with the current file version set so that the plugin can identify if the
 * plugin is running an older file than desired. With this information, the plugin can choose to
 * auto-update the file, warn the console, and so on.
 *
 * @author  Lachlan Adamson
 * @version 1
 * @since   0.1.0
 */
@SuppressWarnings("unused")
public interface VersionedFile {

    /**
     * The installed file version is the 'current' file version being used, which may or may not
     * be the ideal file version. Example: user runs file version 3 whilst latest is 5.
     *
     * @return installed file version
     */
    int getInstalledFileVersion();

    /**
     * The ideal file version is the 'latest' file version which the plugin can utilise. If the
     * installed file version is older than the ideal file version, then a warning should be issued
     * to console suggesting the user update their file (unless it is auto-updated).
     *
     * @return ideal file version
     */
    int getIdealFileVersion();

    /**
     * If the installed file version is older than this version, then the file should be assumed to
     * be fully incompatible with the plugin.
     *
     * @return minimum file version
     */
    int getMinimumFileVersion();

    void migrate();



}
