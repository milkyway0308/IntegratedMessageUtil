package skywolf46.IntegratedMessageUtil.Data;


import java.util.HashMap;

public class ParameterWrapper {
    private HashMap<Class, Object> param = new HashMap<>();
    private HashMap<String, Object> indexedParam = new HashMap<>();
    private static final Class OBJ_CLASS = Object.class;

    public void setParameter(Object o) {
        Class c = o.getClass();
        while (!c.equals(OBJ_CLASS)) {
            for (Class l : c.getInterfaces())
                param.put(l, o);
            c = c.getSuperclass();
        }
    }

    public void setNamedParameter(String index, Object o) {
        indexedParam.put(index, o);
    }

    public <T> T getParameter(Class<T> c) {
        return (T) param.get(c);
    }

    public static ParameterWrapper of(Object... obj) {
        ParameterWrapper pw = new ParameterWrapper();
        for(Object o : obj)
            pw.setParameter(o);
        return pw;
    }
}
