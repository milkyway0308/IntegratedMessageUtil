package skywolf46.IntegratedMessageUtil.CustomEvent;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlaceHolderRegisterEvent extends Event {
    private static HandlerList handlerList = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
