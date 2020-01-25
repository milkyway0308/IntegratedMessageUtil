package skywolf46.IntegratedMessageUtil.Data;


import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PreparsedSentenceStorage {
    private static final List<PreparsedDataSentence> zeroList = new ArrayList<>();
    private HashMap<String, List<PreparsedDataSentence>> sentence = new HashMap<>();
    private boolean requireSave = false;


    public void removeSentence(String str) {
        sentence.remove(str);
    }

    public void clear() {
        sentence.clear();
    }

    public void addSentence(String str, PreparsedDataSentence preparsed) {
        getRawList(str).add(preparsed);
    }

    public PreparsedDataSentence getSentence(String str) {
        List<PreparsedDataSentence> sent = getRawList(str);
        return sent.size() > 0 ? sent.get(0) : null;
    }

    private List<PreparsedDataSentence> getRawList(String str) {
        return sentence.computeIfAbsent(str, a -> new ArrayList<>());
    }

    public List<PreparsedDataSentence> getAll(String str) {
        return new ArrayList<>(sentence.getOrDefault(str, zeroList));
    }

    public void addSentenceIfnotExists(String str, PreparsedDataSentence p) {
        if (!sentence.containsKey(str)) {
            getRawList(str).add(p);
            requireSave = true;
        }
    }

    public void addSentenceIfnotExists(String str, String p) {
        if (!sentence.containsKey(str)) {
            getRawList(str).add(new PreparsedDataSentence(p));
            requireSave = true;
        }
    }


    public void addSentenceIfnotExists(String str, List<String> sent) {
        if (!sentence.containsKey(str)) {
            for (String n : sent)
                getRawList(str).add(new PreparsedDataSentence(n));
            requireSave = true;
        }
    }

    public void writeToFile(File f) {
        requireSave = false;
        YamlConfiguration conf = new YamlConfiguration();
        for (String n : sentence.keySet()) {
            List<PreparsedDataSentence> sent = getRawList(n);
            if (sent.size() == 0)
                continue;
            if (sent.size() == 1)
                conf.set(n, sent.get(0).getOriginal());
            else {
                List<String> str = new ArrayList<>();
                sent.forEach(
                        s -> str.add(s.getOriginal())
                );
                conf.set(n, str);
            }
        }
        try {
            conf.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRequireSave() {
        return requireSave;
    }

    public List<String> getSentenceKey() {
        return new ArrayList<>(sentence.keySet());
    }
}
