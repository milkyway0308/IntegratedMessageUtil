package skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder;

import org.bukkit.entity.Player;
import skywolf46.IntegratedMessageUtil.Data.ParameterWrapper;
import skywolf46.IntegratedMessageUtil.Interface.IPlaceHolder;

public class PlayerMaxHPHolder implements IPlaceHolder {
    private int point;

    public PlayerMaxHPHolder(int point) {
        this.point = point;
    }

    @Override
    public String replaceHolder(ParameterWrapper param) {
        return String.format("%." + point + "f",param.getParameter(Player.class).getMaxHealth());
    }


    @Override
    public Object activeHolder(String str) {
        return null;
    }

    @Override
    public IPlaceHolder getWrappedHolder(String next) {
        return new PlayerMaxHPHolder(0);
    }
}
