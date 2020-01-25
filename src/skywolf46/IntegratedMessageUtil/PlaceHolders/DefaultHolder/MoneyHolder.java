package skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import skywolf46.IntegratedMessageUtil.Data.ParameterWrapper;
import skywolf46.IntegratedMessageUtil.Interface.IPlaceHolder;

public class MoneyHolder implements IPlaceHolder {
    @Override
    public String replaceHolder(ParameterWrapper param) {
        Player p = param.getParameter(Player.class);
        if (p == null)
            return "<money>";
        Economy eco = Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
        if (eco == null)
            return "<money>";
        return String.format("%.0f", eco.getBalance(p));
    }

    @Override
    public Object activeHolder(String str) {
        return null;
    }

    @Override
    public IPlaceHolder getWrappedHolder(String next) {
        return new MoneyHolder();
    }
}
