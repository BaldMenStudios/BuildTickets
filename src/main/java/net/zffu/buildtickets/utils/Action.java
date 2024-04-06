package net.zffu.buildtickets.utils;

import org.bukkit.event.Event;

@FunctionalInterface
public interface Action<T extends Event> {
    void execute(T var1);
}