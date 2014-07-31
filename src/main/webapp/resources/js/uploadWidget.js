function uploadWidget(container) {
    var files = [];
    
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
        var progressBar = null;
        var progressContent = null;
        var fileButton = null;
        var fileContent = null;
        var removeButton = null;

        $(fileInput).fileupload({
            dataType: 'json',
            done: function(e, data) {
                if (data.result.success) {
                    files.push(data.result.fileId);
                    $(progressContent).remove();
                    $(fileContent).click(function(event){
                       event.preventDefault();
                       window.location = location.origin + "/attachment/download/" + data.result.fileId;
                    });
                    removeButton = createRemoveButton(data.result.fileId, fileContent);
                    $(fileButton).append(removeButton);
                    
                    $(fileButton).removeClass("disabled");
                    
                } else {
                    errorSpan.text(data.result.error);
                }
            },
            singleFileUploads: false,
            //maxFileSize: 1,
            progressall: function(e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $(progressBar).css('width', progress + '%');
            },
            processfail: function(e, data) {
                $(errorSpan).text(data.files[data.index].error);
            },
            processdone: function(e, data) {
                fileContent = createFileContent();
                fileButton = createFileButton();

                var fileText = createFileText(data.files[data.index].name);
                $(fileButton).append(fileText);

                progressContent = createProgressContent();
                progressBar = createProgressBar();
                $(progressContent).append(progressBar);

                $(fileContent).append(fileButton);
                $(fileContent).append(progressContent);
                $(filesPanel).append(fileContent);
            }
        });

        $(fileInput).click();
    });

    $(container).append(actionPanel);
    $(container).append(filesPanel);

    function createFileContent() {
        return $("<div class='fileDivContent' />");
    }

    function createFileButton() {
        return $("<span class='btn btn-default attachmentWidth disabled'></span");
    }

    function createFileText(fileName) {
        var fileText = $("<span class='buttontext'></span>");
        $(fileText).text(fileName);
        return fileText;
    }

    function createProgressContent() {
        return $("<div class='progress progress-striped active attachmentWidth newProgressBar' />");
    }

    function createProgressBar() {
        return $("<div class='progress-bar' style='width: 0%' />");
    }

    function createRemoveButton(fileId, fileContent) {
        var removeButton = $("<i class='glyphicon glyphicon-remove xButton'></i>");
        $(removeButton).click(function(event) {
            event.preventDefault();
            event.stopPropagation();
            issueTrackerService.removeFile(fileId).done(function(data) {
                if (data.success) {
                    $(fileContent).remove();
                    var position = $.inArray(fileId, files);
                    if(position !== -1) {
                        files.splice(position, 1);
                    }
                }
            });
        });
        
        return removeButton;
    }
    
    return {
        getUploadedFiles: function() {
            return files;
        }
    };
}


