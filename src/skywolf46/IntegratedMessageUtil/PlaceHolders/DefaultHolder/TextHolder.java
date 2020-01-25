package skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder;

import skywolf46.IntegratedMessageUtil.Data.ParameterWrapper;
import skywolf46.IntegratedMessageUtil.Interface.IPlaceHolder;

public class TextHolder implements IPlaceHolder {
    private String text;

    public TextHolder(String text) {
        this.text = text;
    }

    @Override
    public String replaceHolder(ParameterWrapper param) {
        return text;
    }


    @Override
    public Object activeHolder(String str) {
        return text;
    }

    @Override
    public IPlaceHolder getWrappedHolder(String next) {
        return new TextHolder(next);
    }
}
