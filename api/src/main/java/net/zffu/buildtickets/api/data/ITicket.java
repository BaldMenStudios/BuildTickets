package net.zffu.buildtickets.api.data;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * A Building Ticket from the API.
 */
public interface ITicket {

    UUID getTicketUUID();
    String getTicketReason();
    //todo: add priority

    UUID getCreator();

    Collection<UUID> getBuilders();

    String getNote(UUID playerUUID);

    boolean isNeedingHelp();

    int getCompletionState();
}

