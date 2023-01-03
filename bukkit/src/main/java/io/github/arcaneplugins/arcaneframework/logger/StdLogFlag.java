package io.github.arcaneplugins.arcaneframework.logger;

/**
 * A bunch of standard log flags ideally used in several plugins implementing ArcaneFramework.
 *
 * @author  Lachlan Adamson
 * @version 1
 * @see     LogFlag
 * @since   0.1.0
 */
@SuppressWarnings("unused")
public enum StdLogFlag implements LogFlag {

    /**
     * This flag is used when an error occurs which is known to often be caused from user error.
     */
    LIKELY_USER_ERROR("""
        [User Error?] This type of error is *usually* caused by the user, rather than an issue in the plugin itself. Ensure your configuration files are valid."""),

    /**
     * This flag is used when an error occurs relating to an error occurring during the parsing of a
     * YAML file (.yml).
     */
    SUGGEST_YAML_PARSER("""
        [Tried a YAML Parser?] Use a YAML parser to scan for syntax errors in your configuration files. We recommend < https://www.yaml-online-parser.appspot.com/ >."""),

    /**
     * This flag is used when an error occurs which does not seem very severe, so support is hinted
     * but not heavily recommended to the user.
     *
     * @see StdLogFlag#RECOMMEND_SUPPORT
     */
    HINT_SUPPORT("""
        [Need Support?] Unable to solve this issue yourself? Join our Discord at < https://www.discord.io/arcaneplugins >, or check the Wiki for alternative contact methods."""),

    /**
     * This flag is used when a severe error occurs, heavily recommending the user contacts the
     * support team / author of the plugin.
     *
     * @see StdLogFlag#HINT_SUPPORT
     */
    RECOMMEND_SUPPORT("""
        [Contact Us!] We highly recommend you contact us to resolve this issue. Join our Discord at < https://www.discord.io/arcaneplugins >, or check the Wiki for alternative contact methods."""),

    /**
     * This flag is used when a stack trace was printed alongside a log message to describe what
     * the huge wall of text means. It also reminds the uninformed that reviews sections are for...
     * reviews... and not posting your 300 line long stack trace, blocking other reviews from being
     * easily read. (Facepalm.)
     */
    STACK_TRACE_PROVIDED("""
        [Stack Trace] A stack trace has been printed below to aid developers and advanced users in solving this error. Remember that absolutely no support is provided in review sections, do not post stack traces there.""")

    ;

    private final String msg;

    StdLogFlag(
        final String msg
    ) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}
