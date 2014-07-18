function issueTrackerService() {
    var self = {};
    
    self.login = function(username, password) {
        return $.ajax({});
    };
    self.register = function() {
        var url =location.origin + "/register/createUser";
        var data={name:$('#inputName').val(),
            username:$('#inputUsername').val(),
            email:$('#inputEmail').val(),
            password:$('#inputPassword').val()};
        return $.ajax({
            url: url, 
            type: 'POST', 
            dataType: 'json', 
            contentType: 'application/json',
            mimeType: 'application/json',
            data: JSON.stringify(data),
            statusCode: {
                201: function(data) {
                    console.log(data);
                },
                400: function(data) {
                    console.log(data);
                    $.each(data.errors, function(key, val) {
                        console.log(key + " " + val);
                    });
                    console.log("eeeeeeeeee");
                }
            }
        });
    };
    
    return self;
}
$(document).ready(function(){
    $("#submitButton").on("click", issueTrackerService().register);
});
