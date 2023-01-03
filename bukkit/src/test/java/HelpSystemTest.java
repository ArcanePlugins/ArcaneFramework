import me.lokka30.arcaneframework.command.HelpSystem;
import me.lokka30.arcaneframework.command.HelpSystem.HomeChapter;
import me.lokka30.arcaneframework.command.HelpSystem.SubChapter;
import org.junit.jupiter.api.Test;

public class HelpSystemTest {

    @Test
    void helpSystemTest() {
        HelpSystem.setChapterPageSender(System.out::println);

        final HelpSystem hs = new HelpSystem();

        // CurrentChapter: `Home`
        final HomeChapter home = hs.getHomeChapter();
        home.addPages("""
            • Click here to view command help.
            • Click here to view documentation.
            • Click here to ask for support with LM.""");
        System.out.println(home.getFormattedPage(1));

        {
            // CurrentChapter: `Home` -> `Support`
            final SubChapter homeSupport = new SubChapter(home, "Support");
            homeSupport.addPages(
                """
                LevelledMobs Volunteer User Support is provided at the following sites:
                                
                 1. The ArcanePlugins Discord Guild
                 2. The ArcanePlugins Matrix Space
                 
                Please do not use other sites to request support from our staff.
                """
            );
        }

        {
            // CurrentChapter: `Home` -> `/LM`
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
            System.out.println(homeLm.getFormattedPage(2));

            {
                // CurrentChapter: `Home` -> `/LM` -> `Summon`
                final SubChapter homeLmSummon = new SubChapter(homeLm, "Summon");
                homeLmSummon.addPages(
                    """
                    • /LM Summon <EntityType> <?> <?> <?>
                    ⎣ Summon a levelled mob by a generic entity type. [see chapter]
                    
                    • /LM Summon <CustomEntityId> <?> <?> <?>
                    ⎣ Summon a levelled mob by a custom entity id. [see chapter]"""
                );

                System.out.println(homeLmSummon.getFormattedPage(1));

                {
                    // CurrentChapter: `Home` -> `/LM` -> `Summon` -> `EntityType`
                    @SuppressWarnings("unused")
                    final SubChapter homeLmSummonEntityType = new SubChapter(homeLmSummon, "EntityType");
                    homeLmSummonEntityType.addPages(
                        """
                        • /LM Summon <EntityType> <?> <?> <?>
                        ⎣ Some full description of the command here.
                        ⎣ Example: /LM Summon Zombie ? ? ?"""
                    );

                    System.out.println(homeLmSummonEntityType.getFormattedPage(1));
                }
            }
        }
    }

}
