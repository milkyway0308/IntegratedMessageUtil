package skywolf46.IntegratedMessageUtil.Storage;

import java.util.HashMap;

public class CharacterHolderStorage {
    private HashMap<Character, PlaceHolderStorage> phs = new HashMap<>();

    public PlaceHolderStorage getHolderStorage(char c) {
        return phs.computeIfAbsent(c, a -> new PlaceHolderStorage());
    }

    public boolean isEndingCharacter(char c) {
        return phs.containsKey(c);
    }
}
