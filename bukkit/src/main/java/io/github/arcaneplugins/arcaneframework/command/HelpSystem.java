package io.github.arcaneplugins.arcaneframework.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Allows for easy implementation of help commands, such as `/lm help`.
 *
 * @author lokka30
 * @since v0.2.1
 */
@SuppressWarnings("unused")
public final class HelpSystem {

    private static @Nullable Consumer<String> chapterPageSender = null;

    public static void setChapterPageSender(
        final @Nonnull Consumer<String> chapterPageSender
    ) {
        HelpSystem.chapterPageSender = chapterPageSender;
    }

    public static @Nonnull Consumer<String> getChapterPageSender() {
        return Objects.requireNonNull(
            chapterPageSender,
            "chapterPageSender not initialized"
        );
    }

    private final HomeChapter homeChapter;

    public HelpSystem() {
        Objects.requireNonNull(chapterPageSender, "chapterPageSender not initialized");

        homeChapter = new HomeChapter(this);
    }

    public @Nonnull HomeChapter getHomeChapter() {
        return homeChapter;
    }

    public static sealed class Chapter permits HomeChapter, SubChapter {

        private final String id;
        private final List<String> pages = new ArrayList<>();
        private final List<SubChapter> subChapters = new ArrayList<>();

        public Chapter(
            final @Nonnull String id
        ) {
            this.id = Objects.requireNonNull(id, "id");
        }

        public @Nonnull String getFormattedPage(
            final int index
        ) {
            return """
               ------- LM Help: %s -------
               
               %s
               
               ------- (««) Page %s of %s (»») -------"""
                .formatted(
                    getBreadcrumbs(),

                    getPages().get(index - 1),

                    index,
                    getPages().size()
                );
        }

        public void addPages(
            final @Nonnull String... pages
        ) {
            Collections.addAll(getPages(), Objects.requireNonNull(
                pages,
                "pages"
            ));
        }

        public @Nonnull String getBreadcrumbs() {
            final List<String> reversedBreadcrumb = new ArrayList<>();

            final Consumer<Chapter> consumer = chapter -> {
                if(reversedBreadcrumb.isEmpty()) {
                    reversedBreadcrumb.add(chapter.getId());
                } else {
                    reversedBreadcrumb.add(chapter.getId() + " « ");
                }
            };

            Chapter c = this;
            while(true) {
                consumer.accept(c);
                if(c instanceof SubChapter sc) {
                    c = sc.getParentChapter();
                } else if(c instanceof HomeChapter hc) {
                    break;
                } else {
                    throw new IllegalStateException(c.getClass().getName());
                }
            }

            Collections.reverse(reversedBreadcrumb);

            final StringBuilder sb = new StringBuilder();
            for(final String s : reversedBreadcrumb) { sb.append(s); }
            return sb.toString();
        }

        public @Nonnull String getId() { return id; }

        public @Nonnull List<String> getPages() { return pages; }

        public @Nonnull List<SubChapter> getSubChapters() {
            return subChapters;
        }

    }

    public static final class HomeChapter extends Chapter {

        private final HelpSystem helpSystem;

        public HomeChapter(
            final @Nonnull HelpSystem helpSystem
        ) {
            super("Home");

            this.helpSystem = Objects.requireNonNull(
                helpSystem,
                "helpSystem"
            );
        }

        public @Nonnull HelpSystem getHelpSystem() { return helpSystem; }

    }

    public static final class SubChapter extends Chapter {

        private final Chapter parentChapter;

        public SubChapter(
            final @Nonnull Chapter parentChapter,
            final @Nonnull String id
        ) {
            super(id);
            this.parentChapter = Objects.requireNonNull(parentChapter, "parentChapter");
            getParentChapter().getSubChapters().add(this);
        }

        public @Nonnull Chapter getParentChapter() {
            return parentChapter;
        }

    }

}
