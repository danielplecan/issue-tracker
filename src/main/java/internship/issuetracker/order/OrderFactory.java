package internship.issuetracker.order;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class OrderFactory {
    
    private final static Map<String, Class<? extends QueryOrder>> ordersMap = new HashMap<>();
    
    static {
        ordersMap.put("title", IssueTitleQueryOrder.class);
        ordersMap.put("updateDate", IssueUpdateDateQueryOrder.class);
    }
    
    public static <T> QueryOrder<T> createFilter(String orderName, String orderType) {
        Class<? extends QueryOrder> filterClass = ordersMap.get(orderName);
        
        if(filterClass == null) {
            return null;
        }
        
        try {
            Constructor<? extends QueryOrder> filterConstructor = filterClass.getConstructor(String.class);
            return filterConstructor.newInstance(orderType);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            return null;
        }
    }
}
