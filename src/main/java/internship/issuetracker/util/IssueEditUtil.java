package internship.issuetracker.util;

import internship.issuetracker.dto.IssueDTO;
import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.entity.Label;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author scalin
 */

public class IssueEditUtil {
    
    private final static Map<String, Boolean> CHANGES = new HashMap<>();
    
    public static Map<String, Boolean> getCHANGES() {
        return CHANGES;
    }
    
    public static void storeChanges(NewIssueDTO issue1, IssueDTO issue2){
        CHANGES.put("title", Boolean.FALSE);
        CHANGES.put("content", Boolean.FALSE);
        CHANGES.put("labels", Boolean.FALSE);
        
        if (!issue1.getIssue().getTitle().equals(issue2.getIssue().getTitle())) {
            CHANGES.put("title", Boolean.TRUE);
        }
        if (!issue1.getIssue().getContent().equals(issue2.getIssue().getContent())) {
            CHANGES.put("content", Boolean.TRUE);
        }       
        
        List<Long> issue2Labels = new ArrayList<>();
        List<Label> labels = issue2.getLabels();
        if(labels != null) {
            for (Label label : labels) {
                issue2Labels.add(label.getId());
            }
        }
        
        
        if (!issue1.getLabelIdList().equals(issue2Labels)){
            CHANGES.put("labels", Boolean.TRUE);
        }
    }
}
