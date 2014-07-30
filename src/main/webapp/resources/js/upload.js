$(document).ready(function() {
//    $('#fileupload').fileupload({
//        dataType: 'json',
//        done: function (e, data) {
//            $.each(data.result.files, function (index, file) {
//                $('<p/>').text(file.name).appendTo(document.body);
//            });
//        },
//        progressall: function (e, data) {
//            var progress = parseInt(data.loaded / data.total * 100, 10);
//            $('.progress-bar').css(
//                'width',
//                progress + '%'
//            );
//        },
//        processfail: function(e, data) {
//            alert(data.files[data.index].error);
//            console.log(data);
//        }
//    });
    uploadWidget($(".well"));
});



function uploadWidget(container) {
    var actionPanel = $("<div class='row actionsDiv' />");
    var filesPanel = $("<div class='row allFilesDiv' />");

    var addButton = $("<span class='btn btn-success fileinput-button'></span>");
    $(addButton).append($("<span class='glyphicon glyphicon-plus'></span>"));
    $(addButton).append($("<span>Add file...</span>"));

    var fileInput = $("<input type='file' name='file' data-url='/upload' />");
    var errorSpan = $("<span class='errorsUpload text-danger'></span>");

    $(actionPanel).append(addButton);
    $(actionPanel).append(fileInput);
    $(actionPanel).append(errorSpan);
    $(actionPanel).append($("<hr />"));

    $(addButton).click(function() {
        var jqXHR = null;
        var progressBar = null;
        var progressContent = null
        $(fileInput).fileupload({
            dataType: 'json',
            add: function(e, data) {
                jqXHR = data.submit();
                var fileContent = $("<div class='fileDivContent' />");

                var fileButton = $("<span class='btn btn-default attachmentWidth'></span");

                var fileText = $("<span class='buttontext'></span>");
                var cancelButton = $("<i id='removeFromUploads' class='glyphicon glyphicon-remove xButton'></i>");
                $(cancelButton).click(function() {
                    console.log("plm");
                    jqXHR.abort();
                });
                $(fileText).text(data.files[0].name);

                $(fileButton).append(fileText);
                $(fileButton).append(cancelButton);

                progressContent = $("<div class='progress progress-striped active attachmentWidth newProgressBar' />");
                progressBar = $("<div class='progress-bar' style='width: 0%' />");
                $(progressContent).append(progressBar);

                $(fileContent).append(fileButton);
                $(fileContent).append(progressContent);

                $(filesPanel).append(fileContent);
            },
            done: function(e, data) {
                $(progressContent).remove();
            },
            singleFileUploads: false,
            progressall: function(e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $(progressBar).css('width', progress + '%');
            }
        });
        $(fileInput).click();
    });

    $(container).append(actionPanel);
    $(container).append(filesPanel);

    var progressBar;


}