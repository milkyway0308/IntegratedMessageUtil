package skywolf46.IntegratedMessageUtil.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceHolderCharacterStorage {
    private HashMap<Character, CharacterHolderStorage> storage = new HashMap<>();

    public boolean containsKey(char c) {
        return storage.containsKey(c);
    }

    public CharacterHolderStorage getStorage(char c) {
        return storage.computeIfAbsent(c, a -> new CharacterHolderStorage());
    }

    public List<Character> getCharacterList() {
        return new ArrayList<>(storage.keySet());
    }

    public boolean isPlaceHolder(char c) {
        return storage.containsKey(c);
    }
}
