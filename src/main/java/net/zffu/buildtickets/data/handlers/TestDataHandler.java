package net.zffu.buildtickets.data.handlers;

import net.zffu.buildtickets.data.IDataElement;
import net.zffu.buildtickets.data.IDataHandler;
import net.zffu.buildtickets.data.TicketBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class TestDataHandler implements IDataHandler {

    private HashMap<UUID, TicketBuilder> builders = new HashMap<>();

    @Nullable
    @Override
    public TicketBuilder getBuilder(UUID uuid) {
        return builders.get(uuid);
    }

    @Override
    public Collection<TicketBuilder> getRegisteredBuilders() {
        return builders.values();
    }

    @Override
    public Collection<TicketBuilder> getRegisteredBuilders(int limit) {
        return getRegisteredBuilders();
    }

    @Override
    public boolean shouldSync() {
        return false;
    }

    @Override
    public void sync() {}

    @Override
    public void addToSyncQueue(IDataElement element) {}
}
