package skywolf46.IntegratedMessageUtil.Storage;

import skywolf46.IntegratedMessageUtil.Interface.IPlaceHolder;

import java.util.HashMap;

public class PlaceHolderStorage {
    private char startChar;
    private char endChar;
    private HashMap<String, IPlaceHolder> holderHashMap = new HashMap<>();


    public IPlaceHolder getPlaceHolder(String name) {
        return holderHashMap.get(name);
    }

    public void registerPlaceholder(String str, IPlaceHolder ip) {
        holderHashMap.put(str,ip);
    }
}
