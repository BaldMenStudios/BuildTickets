package net.zffu.buildtickets.storage;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.tickets.BuildTicket;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Interface for a storage model implementation.
 */
public interface IStorage {

    BuildTicketsPlugin getPlugin();
    String getStorageName();
    void init() throws Exception;
    void shutdown();

    Set<UUID> getUniqueBuilders();

    Set<BuildTicket> getTickets();

    void saveBuilders();
    void saveTickets();

}
