import io.github.arcaneplugins.arcaneframework.command.HelpSystem;
import io.github.arcaneplugins.arcaneframework.command.HelpSystem.HomeChapter;
import io.github.arcaneplugins.arcaneframework.command.HelpSystem.SubChapter;
import org.junit.jupiter.api.Test;

public class HelpSystemTest {

    private static final boolean PRINT_FORMATTED_PAGES = false;

    @Test
    void helpSystemTest() {
        final HelpSystem hs = new HelpSystem();

        // Breadcrumb: `Home`
        final HomeChapter home = hs.getHomeChapter();
        home.addPages("""
            • Click here to view command help.
            • Click here to view documentation.
            • Click here to ask for support with LM.""");
        print(home.getFormattedPage(1));
        print(" ");

        {
            // Breadcrumb: `Home` -> `Support`
            final SubChapter homeSupport = new SubChapter(home, "Support");
            homeSupport.addPages(
                """
                LevelledMobs Volunteer User Support is provided at the following sites:
                                
                 1. The ArcanePlugins Discord Guild
                 2. The ArcanePlugins Matrix Space
                 
                Please do not use other sites to request support from our staff.
                """
            );
            print(homeSupport.getFormattedPage(1));
            print(" ");
        }

        {
            // Breadcrumb: `Home` -> `/LM`
            final SubChapter homeLm = new SubChapter(home, "/LM");
            homeLm.addPages(
                """
                • /LM Summon
                ⎣ Summons a levelled mob in your chosen specifications. [see chapter]
                ⎣ Click to view 2x subcommand(s)
                
                • /LM Help
                ⎣ Learn how to use LM and view command help. [see chapter]""",

                """
                • /LM Egg
                ⎣ Creates a levelled mob spawn egg in your chosen specifications. [see chapter]
                ⎣ Click to view 2x subcommand(s)
                
                • /LM About
                ⎣ Check info about the installed version of LM. [see chapter]""");
            print(homeLm.getFormattedPage(2));
            print(" ");

            {
                // Breadcrumb: `Home` -> `/LM` -> `Summon`
                final SubChapter homeLmSummon = new SubChapter(homeLm, "Summon");
                homeLmSummon.addPages(
                    """
                    • /LM Summon <EntityType> <?> <?> <?>
                    ⎣ Summon a levelled mob by a generic entity type. [see chapter]
                    
                    • /LM Summon <CustomEntityId> <?> <?> <?>
                    ⎣ Summon a levelled mob by a custom entity id. [see chapter]"""
                );

                print(homeLmSummon.getFormattedPage(1));
                print(" ");

                {
                    // Breadcrumb: `Home` -> `/LM` -> `Summon` -> `EntityType`
                    @SuppressWarnings("unused")
                    final SubChapter homeLmSummonEntityType = new SubChapter(homeLmSummon, "EntityType");
                    homeLmSummonEntityType.addPages(
                        """
                        • /LM Summon <EntityType> <?> <?> <?>
                        ⎣ Some full description of the command here.
                        ⎣ Example: /LM Summon Zombie ? ? ?"""
                    );

                    print(homeLmSummonEntityType.getFormattedPage(1));
                    print(" ");
                }
            }
        }
    }

    static void print(final String str) {
        if(PRINT_FORMATTED_PAGES) System.out.println(str);
    }

}
