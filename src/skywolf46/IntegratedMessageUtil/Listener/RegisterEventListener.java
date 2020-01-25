package skywolf46.IntegratedMessageUtil.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import skywolf46.IntegratedMessageUtil.CustomEvent.PlaceHolderRegisterEvent;
import skywolf46.IntegratedMessageUtil.IntegratedMessageUtil;
import skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder.PlayerNameHolder;
import skywolf46.IntegratedMessageUtil.Storage.PlaceHolderStorage;

public class RegisterEventListener implements Listener {
    @EventHandler
    public void ev(PlaceHolderRegisterEvent e) {
        PlaceHolderStorage storage = IntegratedMessageUtil.getHolderStorage('<','>');
        storage.registerPlaceholder("player",new PlayerNameHolder());
        storage.registerPlaceholder("hp",new PlayerNameHolder());
    }
}
