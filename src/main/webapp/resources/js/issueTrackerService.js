function issueTrackerService() {
    var self = {};

    self.login = function(username, password) {
        return $.ajax({});
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
            statusCode: {
                201: function(data) {
                    window.location.replace("/login");
                },
                400: function(data) {
                    $.each(data.responseJSON, function() {
                        $.each(this, function(k, v) {
                            $("#" + k + "Error").append(v);
                        });
                    });
                }
            }
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
    $("#submitButton").on("click", function(event) {
        event.preventDefault();
        $(".errors").empty();
        issueTrackerService().register();
    });
});
