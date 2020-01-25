package skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder;

import org.bukkit.entity.Player;
import skywolf46.IntegratedMessageUtil.Data.ParameterWrapper;
import skywolf46.IntegratedMessageUtil.Interface.IPlaceHolder;

public class PlayerNameHolder implements IPlaceHolder {
    public PlayerNameHolder() {

    }

    @Override
    public String replaceHolder(ParameterWrapper param) {
        return param.getParameter(Player.class).getName();
    }

    @Override
    public Object activeHolder(String str) {
        return null;
    }

    @Override
    public IPlaceHolder getWrappedHolder(String next) {
        return new PlayerNameHolder();
    }
}
