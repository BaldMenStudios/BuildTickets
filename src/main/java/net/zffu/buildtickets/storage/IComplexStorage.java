package net.zffu.buildtickets.storage;

import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.tickets.BuildTicket;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Inferface for a more "complex" storage.
 */
public interface IComplexStorage {

    void saveBuilder(TicketBuilder builder);
    TicketBuilder loadBuilder(UUID uuid);
    void saveTicket(BuildTicket ticket);
    Map<UUID, TicketBuilder> loadBuilders(Set<UUID> uuids);


}
