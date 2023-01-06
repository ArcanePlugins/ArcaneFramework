package io.github.arcaneplugins.arcaneframework.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import javax.annotation.Nonnull;

/**
 * Allows for easy implementation of help commands, such as `/lm help`.
 *
 * @author lokka30
 * @since v0.2.1
 */
@SuppressWarnings("unused")
public final class HelpSystem {

    private HelpSystem() throws IllegalAccessException {
        throw new IllegalAccessException("Illegal instantiation of utility class");
    }

    public static String msgLeadingHelpCommand = "/???ReplaceMe??? help";

    public static String msgHeading =
        """
        &8┌ &f&lAF &fHelp Menu &8• &7Path: %breadcrumbs%
        
        &r%page-contents%&r
        
        &8└ &8&m-&8{%previous-page%&8}&7 Page %page-current%&7 of %page-max%&8 &8{%next-page%&8}&m-""";

    public static String msgHeadingPreviousPage = """
        [«««](color=blue format=bold run_command=%leading-help-command% %chapter-path%%previous-index%)""";

    public static String msgHeadingNextPage = """
        [»»»](color=blue format=bold run_command=%leading-help-command% %chapter-path%%next-index%)""";

    public static String msgHeadingPageMax = """
        [%max-index%](color=gray format=italic run_command=%leading-help-command% %chapter-path%%max-index%)""";

    public static String msgHeadingPageCurrent = """
        [%current-index%](color=gray format=italic run_command=%leading-help-command% %chapter-path%%current-index%)""";

    public static String msgBreadcrumb = """
        [%chapter-id%](color=white format=underlined run_command=%leading-help-command% %chapter-path%)""";

    public static String msgBreadcrumbSeparator = """
        &8 »\s""";

    private static final HomeChapter HOME_CHAPTER = new HomeChapter();

    public static @Nonnull HomeChapter getHomeChapter() {
        return HOME_CHAPTER;
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
            return msgHeading
                .replace("%breadcrumbs%", getBreadcrumbs())
                .replace("%page-contents%", getPages().get(index - 1))
                .replace("%previous-page%", msgHeadingPreviousPage)
                .replace("%page-current%", msgHeadingPageCurrent)
                .replace("%current-index%", Integer.toString(index))
                .replace("%page-max%", msgHeadingPageMax)
                .replace("%next-page%", msgHeadingNextPage)
                .replace("%max-index%", Integer.toString(getPages().size()))
                .replace("%next-index%", Integer.toString(index + 1))
                .replace("%previous-index%", Integer.toString(index - 1))
                .replace("%chapter-path%", getChapterPath())
                .replace("%leading-help-command%", msgLeadingHelpCommand);
        }

        public void addPages(
            final @Nonnull String... pages
        ) {
            Collections.addAll(
                getPages(),
                Objects.requireNonNull(pages, "pages")
            );
        }

        public @Nonnull String getChapterPath() {
            List<String> reversePath = new ArrayList<>();

            final Consumer<Chapter> consumer = chapter -> {
                if(reversePath.isEmpty()) {
                    reversePath.add(chapter.getId());
                } else {
                    reversePath.add(chapter.getId() + " ");
                }
            };

            Chapter c = this;
            while(true) {
                if(c instanceof SubChapter sc) {
                    consumer.accept(c);
                    c = sc.getParentChapter();
                } else if(c instanceof HomeChapter hc) {
                    break;
                } else {
                    throw new IllegalStateException(c.getClass().getName());
                }
            }

            Collections.reverse(reversePath);

            final StringBuilder sb = new StringBuilder();
            for(final String s : reversePath) { sb.append(s); }
            sb.append(" ");
            return sb.toString();
        }

        public @Nonnull String getBreadcrumbs() {
            final List<String> reversedBreadcrumb = new ArrayList<>();

            final Consumer<Chapter> consumer = chapter -> {
                final String breadcrumb = msgBreadcrumb
                    .replace("%chapter-id%", chapter.getId())
                    .replace("%leading-help-command%", msgLeadingHelpCommand)
                    .replace("%chapter-path%", getChapterPath());

                if(reversedBreadcrumb.isEmpty()) {
                    reversedBreadcrumb.add(breadcrumb);
                } else {
                    reversedBreadcrumb.add(breadcrumb + msgBreadcrumbSeparator);
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
            for(final String breadcrumb : reversedBreadcrumb) { sb.append(breadcrumb); }
            return sb.toString();
        }

        public @Nonnull String getId() { return id; }

        public @Nonnull List<String> getPages() { return pages; }

        public @Nonnull List<SubChapter> getSubChapters() {
            return subChapters;
        }

    }

    public static final class HomeChapter extends Chapter {

        HomeChapter() {
            super("home");
        }

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
