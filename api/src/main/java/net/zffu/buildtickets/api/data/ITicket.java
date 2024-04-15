package net.zffu.buildtickets.api.data;

import java.util.Collection;
import java.util.UUID;

/**
 * A Building Ticket from the API.
 */
public interface ITicket {

    /**
     * Gets the ticket's internal UUID.
     * @return
     */
    UUID getTicketUUID();

    /**
     * Gets the reason of the ticket.
     * @return
     */
    String getTicketReason();

    //todo: add priority

    /**
     * Gets the ticket creator's uuid.
     * @return
     */
    UUID getCreator();

    /**
     * Gets the builder that are working on the ticket.
     * @return
     */
    Collection<UUID> getBuilders();

    /**
     * Gets the note of the provided player's uuid.
     * @param playerUUID
     * @return
     */
    String getNote(UUID playerUUID);

    /**
     * Returns true or false depending on the help status of the ticket.
     * @return
     */
    boolean isNeedingHelp();

    /**
     * <pReturns the completion state of the ticket.</p>
     * <p>Current Possible States:</p>
     * <p>- -1: Not waiting for confirmation</p>
     * <p>- 0: Waiting for confirmation</p>
     * <p>- 1: Is Completed</p>
     * @return
     */
    int getCompletionState();
}

