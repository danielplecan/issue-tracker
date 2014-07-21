var openStateToggle = function(button, label) {
    $(button).click( function(){
        $(button).attr('disabled',true);
        $(button).addClass('disabledButton');
        
        var action = 'open';
        var issueId = $(label).attr('data-id');
        var url = location.origin + '/issue/' + issueId + '/' + action;

        $.ajax({
            type: 'POST',
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
           statusCode: {
                200: function() {
                    $(label).text('OPEN');
                    $(label).removeClass('label-danger');
                    $(label).addClass('label-warning');
                    $('.toggle').hide();
                    $('#changeState-close').show();
                },
                404: function() {
                    
                }
            }
        });
        
        $(button).removeAttr('disabled'); 
        $(button).removeClass('disabledButton');
    });
};

var closeStateToggle = function(button, label) {
    $(button).click( function(){
        $(button).attr('disabled',true);
        $(button).addClass('disabledButton');
        
        var action = 'close';
        var issueId = $(label).attr('data-id');
        var url = location.origin + '/issue/' + issueId + '/' + action;

        $.ajax({
            type: 'POST',
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            statusCode: {
                200: function() {
                    $(label).text('CLOSED');
                    $(label).removeClass('label-success');
                    $(label).addClass('label-danger');
                    $('.toggle').hide();
                    $('#changeState-reopen').show();
                },
                404: function() {
                    
                }
            }
        });
        $(button).removeAttr('disabled'); 
        $(button).removeClass('disabledButton');
    });
};

var reopenStateToggle = function(button, label) {
    $(button).click( function(){
        $(button).attr('disabled',true);
        $(button).addClass('disabledButton');
        
        var action = 'reopen';
        var issueId = $(label).attr('data-id');
        var url = location.origin + '/issue/' + issueId + '/' + action;

        $.ajax({
            type: 'POST',
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
           statusCode: {
                200: function() {
                    $(label).text('REOPENED');
                    $(label).removeClass('label-danger');
                    $(label).addClass('label-warning');
                    $('.toggle').hide();
                    $('#changeState-close').show();
                },
                404: function() {
                    
                }
            }
        });
        $(button).removeAttr('disabled'); 
        $(button).removeClass('disabledButton');
    });
};
(function (){
    var span = $('#issueState');
    openStateToggle($('#changeState-open'), span);
    reopenStateToggle($('#changeState-reopen'), span);
    closeStateToggle($('#changeState-close'), span);
})();