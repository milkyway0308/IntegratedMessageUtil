package skywolf46.IntegratedMessageUtil.Data;

import skywolf46.IntegratedMessageUtil.IntegratedMessageUtil;
import skywolf46.IntegratedMessageUtil.Interface.IPlaceHolder;
import skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder.TextHolder;
import skywolf46.IntegratedMessageUtil.Storage.CharacterHolderStorage;
import skywolf46.IntegratedMessageUtil.Storage.PlaceHolderCharacterStorage;
import skywolf46.IntegratedMessageUtil.Storage.PlaceHolderStorage;

import java.util.ArrayList;
import java.util.List;

public class PreparsedDataSentence {
    private List<IPlaceHolder> listHolder = new ArrayList<>();
    private String original;

    public PreparsedDataSentence(String str) {
        this(str, null);
    }

    public PreparsedDataSentence(String data, PlaceHolderCharacterStorage temp) {
        this.original = data;
        int lastProcessed = 0;
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (IntegratedMessageUtil.isPlaceHolder(c) || (temp != null && temp.isPlaceHolder(c))) {
                CharacterHolderStorage chs = (
                        IntegratedMessageUtil.isPlaceHolder(c) ?
                                IntegratedMessageUtil.getCharacterHolder(c) :
                                temp.getStorage(c)
                );
                PlaceHolderStorage phs = null;
                int x = i + 1;
                for (; x < data.length(); x++) {
                    if (chs.isEndingCharacter(data.charAt(x))) {
                        phs = chs.getHolderStorage(data.charAt(x));
                        // end char detected
                        break;
                    }
                }
                if (phs == null) {
                    listHolder.add(new TextHolder(data.substring(lastProcessed, data.length())));
                    return;
                }
                String next = data.substring(i + 1, x);
                if (next.length() == 0) {
                    i = x + 1;
                    continue;
                }
                int index = next.indexOf(":");
                String sepString = index == -1 ? next : next.substring(0, index);
                IPlaceHolder ip = phs.getPlaceHolder(sepString);
                if (ip != null) {
                    String before = data.substring(lastProcessed, i);
                    lastProcessed = x + 1;

                    listHolder.add(new TextHolder(before));
                    listHolder.add(ip.getWrappedHolder(next));
                }
                i = x + 1;
            }
        }
        listHolder.add(new TextHolder(data.substring(lastProcessed)));
    }

    private PreparsedDataSentence() {

    }


    public String toString(ParameterWrapper pwr) {
        StringBuilder sb = new StringBuilder();
        for (IPlaceHolder ipl : listHolder)
            sb.append(ipl.replaceHolder(pwr));
        return sb.toString();
    }


    public String getOriginal() {
        return original;
    }

    @Override
    public PreparsedDataSentence clone() {
        PreparsedDataSentence n = new PreparsedDataSentence();
        n.original = original;
        for (IPlaceHolder ph : listHolder)
            n.listHolder.add(ph.cloneHolder());
        return n;
    }

    public String toString(ParameterWrapper pw, BuilderHashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (IPlaceHolder ipl : listHolder)
            sb.append(ipl.replaceHolder(pw));
        String s = sb.toString();
        for (String n : map.getMap().keySet())
            s = s.replace(n, map.get(n));
        return s;
    }
}
