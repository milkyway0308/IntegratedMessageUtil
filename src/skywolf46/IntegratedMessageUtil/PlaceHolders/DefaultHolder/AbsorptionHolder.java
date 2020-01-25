package skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import skywolf46.IntegratedMessageUtil.Data.ParameterWrapper;
import skywolf46.IntegratedMessageUtil.Interface.IPlaceHolder;

public class AbsorptionHolder implements IPlaceHolder {
    @Override
    public String replaceHolder(ParameterWrapper param) {
        return String.format("%.0f",((CraftPlayer)param.getParameter(Player.class)).getHandle().getAbsorptionHearts());
    }

    @Override
    public Object activeHolder(String s) {
        return null;
    }

    @Override
    public IPlaceHolder getWrappedHolder(String s) {
        return new AbsorptionHolder();
    }
}
