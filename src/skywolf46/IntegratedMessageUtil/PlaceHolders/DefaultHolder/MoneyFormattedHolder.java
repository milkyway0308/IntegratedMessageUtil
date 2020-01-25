package skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import skywolf46.IntegratedMessageUtil.Data.ParameterWrapper;
import skywolf46.IntegratedMessageUtil.Interface.IPlaceHolder;

import java.text.DecimalFormat;

public class MoneyFormattedHolder implements IPlaceHolder {
    private DecimalFormat format = new DecimalFormat("#");
    @Override
    public String replaceHolder(ParameterWrapper param) {
        Player p = param.getParameter(Player.class);
        if (p == null)
            return "<moneyformat>";
        Economy eco = Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
        if (eco == null)
            return "<moneyformat>";
        return format.format(eco.getBalance(p));
    }

    @Override
    public Object activeHolder(String str) {
        return null;
    }

    @Override
    public IPlaceHolder getWrappedHolder(String next) {
        MoneyFormattedHolder mf = new MoneyFormattedHolder();
        int index = next.indexOf(':');
        if(index != -1)
            try {
                mf.format = new DecimalFormat(next.substring(index+1));
            }catch (Exception ex){

            }
        return mf;
    }
}
