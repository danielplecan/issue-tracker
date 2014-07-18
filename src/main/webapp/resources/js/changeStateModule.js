var changeStateModule = function(button, label) {
    $(button).click( function(){
        console.log('click');
        var action;
        switch($(label).text()) {
            case 'OPEN':
                action = 'open';
                break;
            case 'CLOSED':
                action = 'close';
                break;
            case 'REOPENED':
                action = 'reopen';
                break;
            default:
                return;
        }

        var issueId = $(label).attr('data-id');
        console.log("http://localhost:8080/issue-tracker/issue/" + issueId + "/" + action);

        $.ajax({
           type: 'POST',
           url: "http://localhost:8080/issue-tracker/issue/" + issueId + "/" + action
        });
    });
};
(function (){
    var button = $('#changeState');
    var span = $('#issueState');
    console.log(button);
    changeStateModule(button, span);
})();
