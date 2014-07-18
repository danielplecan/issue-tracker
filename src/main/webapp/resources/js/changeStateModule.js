var openStateToggle = function(button, label) {
    $(button).click( function(){
        console.log('click');
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
                       console.log('fail');
                   }
            }
            });
    });
};
(function (){
    var button = $('#changeState-reopen');
    var span = $('#issueState');
    openStateToggle(button, span);
})();
