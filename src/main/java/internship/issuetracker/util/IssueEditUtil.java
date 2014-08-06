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
    private IssueEditUtil() {}
    
    private final static Map<String, Boolean> changes = new HashMap<>();
    
    public static Map<String, Boolean> getChanges() {
        return changes;
    }
    
    public static void storeChanges(NewIssueDTO issue1, IssueDTO issue2){
        changes.put("title", Boolean.FALSE);
        changes.put("content", Boolean.FALSE);
        changes.put("labels", Boolean.FALSE);
        
        if (!issue1.getIssue().getTitle().equals(issue2.getIssue().getTitle())) {
            changes.put("title", Boolean.TRUE);
        }
        if (!issue1.getIssue().getContent().equals(issue2.getIssue().getContent())) {
            changes.put("content", Boolean.TRUE);
        }       
        
        List<Long> issue2Labels = new ArrayList<>();
        List<Label> labels = issue2.getLabels();
        if(labels != null) {
            for (Label label : labels) {
            issue2Labels.add(label.getId());
        }
        }
        
        
        if (!issue1.getLabelIdList().equals(issue2Labels)){
            changes.put("labels", Boolean.TRUE);
        }
    }
}
