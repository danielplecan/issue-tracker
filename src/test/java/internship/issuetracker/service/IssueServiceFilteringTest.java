package internship.issuetracker.service;

import internship.issuetracker.dto.NewIssueDTO;
import internship.issuetracker.dto.UserDTO;
import internship.issuetracker.entity.Issue;
import internship.issuetracker.entity.IssueState;
import internship.issuetracker.entity.User;
import internship.issuetracker.filter.FilterResult;
import internship.issuetracker.filter.IssueContentQueryFilter;
import internship.issuetracker.filter.IssueSearchCriteria;
import internship.issuetracker.filter.IssueStateQueryFilter;
import internship.issuetracker.filter.IssueTitleQueryFilter;
import internship.issuetracker.filter.QueryFilter;
import internship.issuetracker.order.IssueTitleQueryOrder;
import internship.issuetracker.order.OrderType;
import internship.issuetracker.order.QueryOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Testing the filtering functionality of the IssueService class
 *
 * @author dplecan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:config/datasource/h2.xml",
    "classpath:config/application-context.xml"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IssueServiceFilteringTest {

    @Autowired
    private UserService userService;

    @Autowired
    private IssueService issueService;

    private static boolean initializationHasRun = false;

    @Before
    public void setUp() throws Exception {
        if (!initializationHasRun) {
            registerUser();
            addIssues();
            initializationHasRun = true;
        }
    }

    @Test
    public void testTitleFilterOne() {
        IssueSearchCriteria searchCriteria = new IssueSearchCriteria();
        Map<String, Object> filters = new HashMap<>();
        Map<String, Object> orders = new HashMap<>();

        filters.put("title", "title");
        orders.put("title", "ASC");

        searchCriteria.setFilters(filters);
        searchCriteria.setOrders(orders);
        searchCriteria.setNumberOfItemsPerPage(5);
        searchCriteria.setPageNumber(1);

        FilterResult filteredIssues = issueService.filterIssues(searchCriteria.getQueryFilters(), 
                searchCriteria.getQueryOrder(), searchCriteria.getPageNumber(), searchCriteria.getNumberOfItemsPerPage());
        
        assertTrue(filteredIssues.getTotalResultCount() == 5);
    }

    @Test
    public void testTitleFilterTwo() {
        IssueSearchCriteria searchCriteria = new IssueSearchCriteria();
        Map<String, Object> filters = new HashMap<>();
        Map<String, Object> orders = new HashMap<>();

        filters.put("title", "title3");
        orders.put("updateDate", "ASC");

        searchCriteria.setFilters(filters);
        searchCriteria.setOrders(orders);
        searchCriteria.setNumberOfItemsPerPage(5);
        searchCriteria.setPageNumber(1);

        FilterResult filteredIssues = issueService.filterIssues(searchCriteria.getQueryFilters(), 
                searchCriteria.getQueryOrder(), searchCriteria.getPageNumber(), searchCriteria.getNumberOfItemsPerPage());
        assertTrue((filteredIssues.getTotalResultCount() == 1) == (filteredIssues.getIssues().get(0).getIssue().getTitle().startsWith("title3")));
    }

    @Test
    public void testContentFilterOne() {
        IssueSearchCriteria searchCriteria = new IssueSearchCriteria();
        Map<String, Object> filters = new HashMap<>();
        Map<String, Object> orders = new HashMap<>();

        filters.put("content", "content");
        orders.put("updateDate", "DESC");

        searchCriteria.setFilters(filters);
        searchCriteria.setOrders(orders);
        searchCriteria.setNumberOfItemsPerPage(5);
        searchCriteria.setPageNumber(1);

        FilterResult filteredIssues = issueService.filterIssues(searchCriteria.getQueryFilters(), 
                searchCriteria.getQueryOrder(), searchCriteria.getPageNumber(), searchCriteria.getNumberOfItemsPerPage());
        assertTrue(filteredIssues.getTotalResultCount() == 5);
    }

    @Test
    public void testContentFilterTwo() {
       IssueSearchCriteria searchCriteria = new IssueSearchCriteria();
        Map<String, Object> filters = new HashMap<>();
        Map<String, Object> orders = new HashMap<>();

        filters.put("content", "content1");
        orders.put("updateDate", "DESC");

        searchCriteria.setFilters(filters);
        searchCriteria.setOrders(orders);
        searchCriteria.setNumberOfItemsPerPage(5);
        searchCriteria.setPageNumber(1);

        FilterResult filteredIssues = issueService.filterIssues(searchCriteria.getQueryFilters(), 
                searchCriteria.getQueryOrder(), searchCriteria.getPageNumber(), searchCriteria.getNumberOfItemsPerPage());
        assertTrue((filteredIssues.getTotalResultCount() == 1) == (filteredIssues.getIssues().get(0).getIssue().getContent().startsWith("content1")));
    }

    @Test
    public void testOpenStateFilter() {
        IssueSearchCriteria searchCriteria = new IssueSearchCriteria();
        Map<String, Object> filters = new HashMap<>();
        Map<String, Object> orders = new HashMap<>();

        filters.put("state", "open");
        orders.put("updateDate", "ASC");

        searchCriteria.setFilters(filters);
        searchCriteria.setOrders(orders);
        searchCriteria.setNumberOfItemsPerPage(5);
        searchCriteria.setPageNumber(1);

        FilterResult filteredIssues = issueService.filterIssues(searchCriteria.getQueryFilters(), 
                searchCriteria.getQueryOrder(), searchCriteria.getPageNumber(), searchCriteria.getNumberOfItemsPerPage());
        assertTrue(filteredIssues.getTotalResultCount() == 3);
    }

    @Test
    public void testClosedStateFilter() {
       IssueSearchCriteria searchCriteria = new IssueSearchCriteria();
        Map<String, Object> filters = new HashMap<>();
        Map<String, Object> orders = new HashMap<>();

        filters.put("state", "closed");
        orders.put("updateDate", "ASC");

        searchCriteria.setFilters(filters);
        searchCriteria.setOrders(orders);
        searchCriteria.setNumberOfItemsPerPage(5);
        searchCriteria.setPageNumber(1);

        FilterResult filteredIssues = issueService.filterIssues(searchCriteria.getQueryFilters(), 
                searchCriteria.getQueryOrder(), searchCriteria.getPageNumber(), searchCriteria.getNumberOfItemsPerPage());
        assertTrue(filteredIssues.getTotalResultCount() == 2);
    }

    @Test
    public void testMixedFiltering() {
        IssueSearchCriteria searchCriteria = new IssueSearchCriteria();
        Map<String, Object> filters = new HashMap<>();
        Map<String, Object> orders = new HashMap<>();

        filters.put("content", "content");
        filters.put("title", "title5");
        filters.put("state", "closed");
        filters.put("unexisting", "unexistingvalue");
        orders.put("updateDate", "ASC");

        searchCriteria.setFilters(filters);
        searchCriteria.setOrders(orders);
        searchCriteria.setNumberOfItemsPerPage(5);
        searchCriteria.setPageNumber(1);

        FilterResult filteredIssues = issueService.filterIssues(searchCriteria.getQueryFilters(), 
                searchCriteria.getQueryOrder(), searchCriteria.getPageNumber(), searchCriteria.getNumberOfItemsPerPage());
        
        assertTrue(filteredIssues.getTotalResultCount() == 1);
    }

    @Transactional
    public final void registerUser() {

        UserDTO userDTO = new UserDTO();
        userDTO.setName("name");
        userDTO.setEmail("email@email");
        userDTO.setUsername("username");
        userDTO.setPassword("password");

        userService.registerUser(userDTO);
    }

    @Transactional
    public User getUser() {
        return userService.getUserByUsername("username");
    }

    @Transactional
    public final void addIssues() {
        Issue issue1 = new Issue();
        issue1.setTitle("title1");
        issue1.setContent("content1");
        issue1.setDate(new Date(1234L));
        issue1.setUpdateDate(new Date(4321L));
        issue1.setState(IssueState.CLOSED);

        Issue issue2 = new Issue();
        issue2.setTitle("title2");
        issue2.setContent("content2");
        issue2.setDate(new Date(1235L));
        issue2.setUpdateDate(new Date(5321L));
        issue2.setState(IssueState.OPEN);

        Issue issue3 = new Issue();
        issue3.setTitle("title3");
        issue3.setContent("content3");
        issue3.setDate(new Date(1236L));
        issue3.setUpdateDate(new Date(6321L));
        issue3.setState(IssueState.OPEN);

        Issue issue4 = new Issue();
        issue4.setTitle("title4");
        issue4.setContent("content4");
        issue4.setDate(new Date(1237L));
        issue4.setUpdateDate(new Date(7321L));
        issue4.setState(IssueState.OPEN);

        Issue issue5 = new Issue();
        issue5.setTitle("title5");
        issue5.setContent("content5");
        issue5.setDate(new Date(1238L));
        issue5.setUpdateDate(new Date(8321L));
        issue5.setState(IssueState.CLOSED);

        NewIssueDTO newIssueDTO1 = new NewIssueDTO();
        NewIssueDTO newIssueDTO2 = new NewIssueDTO();
        NewIssueDTO newIssueDTO3 = new NewIssueDTO();
        NewIssueDTO newIssueDTO4 = new NewIssueDTO();
        NewIssueDTO newIssueDTO5 = new NewIssueDTO();

        newIssueDTO1.setIssue(issue1);
        newIssueDTO1.setLabelIdList(new ArrayList<Long>());
        newIssueDTO1.setAttachments(new ArrayList<Long>());

        newIssueDTO2.setIssue(issue2);
        newIssueDTO2.setLabelIdList(new ArrayList<Long>());
        newIssueDTO2.setAttachments(new ArrayList<Long>());

        newIssueDTO3.setIssue(issue3);
        newIssueDTO3.setLabelIdList(new ArrayList<Long>());
        newIssueDTO3.setAttachments(new ArrayList<Long>());

        newIssueDTO4.setIssue(issue4);
        newIssueDTO4.setLabelIdList(new ArrayList<Long>());
        newIssueDTO4.setAttachments(new ArrayList<Long>());

        newIssueDTO5.setIssue(issue5);
        newIssueDTO5.setLabelIdList(new ArrayList<Long>());
        newIssueDTO5.setAttachments(new ArrayList<Long>());

        issueService.createIssueFromIssueDTO(newIssueDTO1, getUser());
        issueService.createIssueFromIssueDTO(newIssueDTO2, getUser());
        issueService.createIssueFromIssueDTO(newIssueDTO3, getUser());
        issueService.createIssueFromIssueDTO(newIssueDTO4, getUser());
        issueService.createIssueFromIssueDTO(newIssueDTO5, getUser());
    }
}
