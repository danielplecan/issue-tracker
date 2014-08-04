autocomplete = (function() {
    var self = {};
    var resultList = [];

    getList = function(ajaxFunction, prefix) {
        resultList = [];
        var issueId = $("#issueState").attr('data-id');
        if (prefix !== '') {
            ajaxFunction(issueId, prefix)
                    .done(function(data) {
                        if (data.success) {
                            var size = data.result.length;
                            for (var i = 0; i < size; i++) {
                                resultList.push(data.result[i]);
                            }
                        }
                        else {
                            $.each(data.errors, function(key, value) {
                                $("#" + key + "Error").append(value);
                            });
                        }
                    });
            console.log(resultList);
        }
    };

    var substringMatcher = function(g) {
        return function(prefix, funct) {
            if (prefix !== '') {
                getList(g, prefix);
            }
            funct(resultList);
        };
    };

    var typeaheadCall = function(clas, g) {
        $(clas).typeahead({ 
            hint: true,
            highlight: true,
            minLength: 1
        },
        {
            name: 'result',
            displayKey: 'username',
            source: substringMatcher(g)
        });
    };

    self.filterOwners = function() {
        return typeaheadCall('.typeaheadOwners', issueTrackerService.getFilterOwners);
    };
    
    self.filterAssignees = function() {
        return typeaheadCall('.typeaheadAssignees',issueTrackerService.getFilterAssignee);
    };
    
    self.getAllUsersForAssignTo= function(){
        return typeaheadCall('.typeahead',issueTrackerService.getUsersAssignee);
    };
    return self;
})();