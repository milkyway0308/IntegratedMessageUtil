package skywolf46.IntegratedMessageUtil.Interface;

import skywolf46.IntegratedMessageUtil.Data.ParameterWrapper;

public interface IPlaceHolder {
    String replaceHolder(ParameterWrapper param);
    default Object activeHolder(String str){
        return null;
    }
    IPlaceHolder getWrappedHolder(String next);

    default IPlaceHolder cloneHolder(){
        return this;
    }
}
