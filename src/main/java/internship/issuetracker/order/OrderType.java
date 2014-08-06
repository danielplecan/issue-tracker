package internship.issuetracker.order;

/**
 *
 * @author dplecan
 */
public enum OrderType {
    ASCENDENT, DESCENDENT;
    
    public static OrderType fromString(String orderType) {
        if(orderType == null) {
            return null;
        }
        
        switch(orderType.toUpperCase()) {
            case "ASC":
                return ASCENDENT;
            case "DESC":
                return DESCENDENT;
            default:
                return null;
        }
    }
}
