package net.zffu.buildtickets.locale;

import lombok.Getter;

/**
 * Enum to store locale keys, seems useless but is actually a bit useful and allows us to use EnumMap for free optimization so.
 */
@Getter
public enum LocaleString {

    PERMISSION_NOT_MET("permission.not_met"),
    USAGE_MISSING_ARGS("usage.missing_arguments"),

    PAGE_ALREADY_LAST("page.already.last"),
    PAGE_ALREADY_FIRST("page.already.first"),

    PROMPT_ENTER("prompt.enter"),

    TICKET_NOTE_ADDED("ticket.note.added"),
    TICKET_NOTE_EDIT("ticket.note.edit"),

    TICKET_CREATED("ticket.created"),
    TICKET_JOIN_HELP_DISABLED("ticket.join.help.disabled"),
    TICKET_JOIN("ticket.join"),
    TICKET_JOIN_ALREADY("ticket.join.already"),
    TICKET_BUILDER_NOT("ticket.builder.not"),
    TICKET_QUIT("ticket.quit"),

    TICKET_PRIORITY_ALREADY("ticket.priority.already"),
    TICKET_PRIORITY_CHANGE("ticket.priority.change"),

    TICKET_HELP_ON("ticket.help.on"),
    TICKET_HELP_OFF("ticket.help.off"),

    TICKET_COMPLETION_WAITING("ticket.completion.waiting"),
    TICKET_COMPLETION_CONFIRMED("ticket.completion.confirmed"),

    BUILDMODE_ON("buildmode.on"),
    BUILDMODE_OFF("buildmode.off"),

    BUILDPHYSICS_ON("buildphysics.on"),
    BUILDPHYSICS_OFF("buildphysics.off");

    private String key;

    private LocaleString(String key) {
        this.key = key;
    }

}
