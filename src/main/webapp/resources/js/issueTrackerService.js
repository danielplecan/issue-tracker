function issueTrackerService() {
    var self = {};

    self.login = function(username, password) {
         var url = location.origin + "/login";
         var data={
             username:$("#inputUsername").val(),
             password:$("#inputPassword").val()
         };
        return $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data: JSON.stringify(createUser()),
            statusCode: {
                201: function(data) {
                    window.location.replace("/");
                },
                400: function(data) {
                
                }
            }
        });
    };
    self.register = function() {
        var url = location.origin + "/register";
        return $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data: JSON.stringify(createUser()),
        });
    };

    return self;
}
function createUser(){
    var serialized = $("#registerForm").serializeArray();
    var user = {};
    $.each(serialized, function(index, item){
        user[item.name] = item.value;
    });
    return user;
}
$(document).ready(function() {
    $("#loginButton").on("click", function(event) {
        event.preventDefault();
       // $(".errors").empty();
        issueTrackerService().login();
    });
});
