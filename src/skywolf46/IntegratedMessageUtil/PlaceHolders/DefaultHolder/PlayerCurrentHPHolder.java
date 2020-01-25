package skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder;

import org.bukkit.entity.Player;
import skywolf46.IntegratedMessageUtil.Data.ParameterWrapper;
import skywolf46.IntegratedMessageUtil.Interface.IPlaceHolder;

public class PlayerCurrentHPHolder implements IPlaceHolder {
    private int point;

    public PlayerCurrentHPHolder(int point) {
        this.point = point;
    }

    @Override
    public String replaceHolder(ParameterWrapper param) {
        return String.format("%." + point + "f",param.getParameter(Player.class).getHealth());
    }


    @Override
    public Object activeHolder(String str) {
        return null;
    }

    @Override
    public IPlaceHolder getWrappedHolder(String next) {
        return new PlayerCurrentHPHolder(0);
    }
}
