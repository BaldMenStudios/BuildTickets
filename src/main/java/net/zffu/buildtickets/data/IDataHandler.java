package net.zffu.buildtickets.data;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.UUID;

/**
 * Interface of a Data Handler of the plugin.
 */
public interface IDataHandler {

    /**
     * Gets the builder from the UUID.
     * @param uuid
     * @return
     */
    @Nullable
    TicketBuilder getBuilder(UUID uuid);

    /**
     * Gets every single registered builders.
     * @return
     */
    Collection<TicketBuilder> getRegisteredBuilders();

    /**
     * Gets the registered builders with the limit.
     * @param limit
     * @return
     */
    Collection<TicketBuilder> getRegisteredBuilders(int limit);

    /**
     * Should sync the data with the database.
     * @return
     */
    boolean shouldSync();

    /**
     * Sync the sync queue into the database.
     */
    void sync();

    /**
     * Adds the element into the sync queue.
     * @param element
     */
    void addToSyncQueue(IDataElement element);

}
