package net.zffu.buildtickets.storage;

/**
 * A Simple Storage Interface of BuildTickets.
 */
public interface IStorage {

    void init() throws Exception;
    void shutdown();

}
