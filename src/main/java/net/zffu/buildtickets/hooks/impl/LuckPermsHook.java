package net.zffu.buildtickets.hooks.impl;

import net.luckperms.api.LuckPerms;
import net.zffu.buildtickets.hooks.IPermissionHook;
import org.bukkit.entity.HumanEntity;

public class LuckPermsHook implements IPermissionHook {

    private LuckPerms api;

    public LuckPermsHook(LuckPerms api) {
        this.api = api;
    }

    @Override
    public void givePermission(HumanEntity entity) {

    }

    @Override
    public boolean hasPermission(HumanEntity entity) {
        return false;
    }
}
