var openStateToggle = function(button, label) {
    $(button).click( function(){
        var action = 'open';
        var issueId = $(label).attr('data-id');
        var url = 'http://localhost:8080/issue-tracker/issue/' + issueId + '/' + action;

        $.ajax({
            type: 'POST',
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                   if( data.status == 'succes') {
                       $(label).text('OPEN');
                       $(label).removeClass('label-danger');
                       $(label).addClass('label-warning');
                       $('.toggle').hide();
                       $('#changeState-close').show();
                   } else {
                   }
            }
            });
    });
};

var closeStateToggle = function(button, label) {
    $(button).click( function(){
        var action = 'close';
        var issueId = $(label).attr('data-id');
        var url = 'http://localhost:8080/issue-tracker/issue/' + issueId + '/' + action;

        $.ajax({
            type: 'POST',
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                   if( data.status == 'succes') {
                       $(label).text('CLOSED');
                       $(label).removeClass('label-succes');
                       $(label).addClass('label-danger');
                       $('.toggle').hide();
                       $('#changeState-reopen').show();
                   } else {
                   }
            }
            });
    });
};

var reopenStateToggle = function(button, label) {
    $(button).click( function(){
        var action = 'reopen';
        var issueId = $(label).attr('data-id');
        var url = 'http://localhost:8080/issue-tracker/issue/' + issueId + '/' + action;

        $.ajax({
            type: 'POST',
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                   if( data.status == 'succes') {
                       $(label).text('REOPENED');
                       $(label).removeClass('label-danger');
                       $(label).addClass('label-warning');
                       $('.toggle').hide();
                       $('#changeState-close').show();
                   } else {
                   }
            }
            });
    });
};
(function (){
    var span = $('#issueState');
    openStateToggle($('#changeState-open'), span);
    reopenStateToggle($('#changeState-reopen'), span);
    closeStateToggle($('#changeState-close'), span);
})();
