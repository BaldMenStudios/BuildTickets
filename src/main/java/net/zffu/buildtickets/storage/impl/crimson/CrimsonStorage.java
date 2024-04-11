package net.zffu.buildtickets.storage.impl.crimson;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.storage.IStorage;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.crimson.CrimsonDatabase;
import net.zffu.crimson.format.FormattingException;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * The CrimsonDB Storage Implementation for BuildTickets.
 */
public class CrimsonStorage implements IStorage {

    private CrimsonDatabase db;

    @Override
    public BuildTicketsPlugin getPlugin() {
        return BuildTicketsPlugin.getInstance();
    }

    @Override
    public String getStorageName() {
        return "Crimson";
    }

    @Override
    public void init() throws Exception {
        this.db = new CrimsonDatabase(new File(this.getPlugin().getDataFolder(), "db"));
    }

    @Override
    public void shutdown() {
        try {
            this.db.saveTables();
        } catch (Exception e) {
            this.getPlugin().getLogger().warning("Could not save Crimson Database: " + e);
        }
    }

    @Override
    public Set<UUID> getUniqueBuilders() {
        return null;
    }

    @Override
    public Set<BuildTicket> getTickets() {
        return null;
    }

    @Override
    public void saveBuilders() {

    }

    @Override
    public void saveTickets() {

    }

}
