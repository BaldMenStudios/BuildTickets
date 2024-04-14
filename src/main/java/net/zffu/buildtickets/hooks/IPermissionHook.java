package net.zffu.buildtickets.hooks;

import org.bukkit.entity.HumanEntity;

/**
 * A hoop for a permission plugin.
 */
public interface IPermissionHook {

    /**
     * Gives the permission to the {@link HumanEntity}.
     * @param entity
     */
    void givePermission(HumanEntity entity);

    /**
     * Returns true or false depending on if the {@link HumanEntity} has the permission or not.
     * @param entity
     * @return
     */
    boolean hasPermission(HumanEntity entity);

}
