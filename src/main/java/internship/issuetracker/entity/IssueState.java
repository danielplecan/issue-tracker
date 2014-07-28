/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internship.issuetracker.entity;

/**
 *
 * @author atataru
 */
public enum IssueState {
    OPEN, CLOSED;
    
    public static IssueState fromString(String issueState) {
        switch(issueState.toLowerCase()) {
            case "open" :
                return OPEN;
            case "closed" :
                return CLOSED;
            default:
                return null;
        }
    }
}
