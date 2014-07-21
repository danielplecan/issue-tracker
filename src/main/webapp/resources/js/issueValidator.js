/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
                $("#buttonCreateIssue").attr("disabled", "disabled");
                    $('#textArea1').keyup(function() {
                        if ($(this).val().length !== 0) {
                            $('#buttonCreateIssue').attr('disabled', false);
                        }
                        else
                        {
                            $('#buttonCreateIssue').attr('disabled', true);
                        }
                    });
            });
