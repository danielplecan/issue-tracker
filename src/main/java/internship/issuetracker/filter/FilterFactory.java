/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.filter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dplecan
 */
public class FilterFactory {
    
    private final static Map<String, Class<? extends QueryFilter>> filtersMap = new HashMap<>();
    
    static {
        filtersMap.put("title", IssueTitleQueryFilter.class);
        filtersMap.put("content", IssueContentQueryFilter.class);
        filtersMap.put("state", IssueStateQueryFilter.class);
        filtersMap.put("assignee", IssueAssigneeQueryFilter.class);
//        filtersMap.put("labels", IssueLabelQueryFilter.class);
    }
    
    public static <T> QueryFilter<T> createFilter(String filterName, String filterValue) {
        Class<? extends QueryFilter> filterClass = filtersMap.get(filterName);
        
        if(filterClass == null) {
            return null;
        }
        
        try {
            Constructor<? extends QueryFilter> filterConstructor = filterClass.getConstructor(String.class);
            return filterConstructor.newInstance(filterValue);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            return null;
        }
    }
}
