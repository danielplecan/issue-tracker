/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("#inputRetypePassword").on('input', function() {
        var error=$("#passwordMatchError");
        if($('#inputPassword').val()!==$('#inputRetypePassword').val()){
              $("#submitButton").attr("disabled", "disabled");
              error.attr("class","text-warning visible");
        } else {
            $("#submitButton").removeAttr("disabled");
             error.attr("class","hidden");
        }
    });
});
