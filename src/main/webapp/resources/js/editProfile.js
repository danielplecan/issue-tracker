function passwordsMatch() {
    return $('#inputNewPassword').val()!==""?$('#inputNewPassword').val() === $('#inputRetypePassword').val():true;
}

function createEditData(){
    var serialized = $("#editForm").serializeArray();
    var user = {};
    $.each(serialized, function(index, item){
        user[item.name] = item.value;
    });
    
    return user;
}

$(document).ready(function() {
    var submitButton = $("#submitButton");
    var cancelButton = $("#cancelButton");
    
    cancelButton.click( function(event) {
        event.preventDefault();
        window.location.replace("/settings");
    });
    
    submitButton.on("click", function(event) {
        event.preventDefault();
        $(".errors").empty();
        
        if(passwordsMatch()) {
            $("#passwordMatchError").attr("class", "hidden");
            
            var editData = createEditData();
            
            issueTrackerService.edit(editData).done(function(data) {
                if (data.success) {
                    window.location.replace("/profile/"+data.username);
                }
                else {
                   $.each(data.errors, function(key, value) {
                        $("#" + key + "Error").append(value);
                    }); 
                }
            });
        } else {
            $("#passwordMatchError").attr("class", "text-warning visible");
        }
    });
});


