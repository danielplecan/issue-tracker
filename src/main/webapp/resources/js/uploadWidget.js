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

        $(errorSpan).empty();

        $(fileInput).fileupload({
            dataType: 'json',
            done: function(e, data) {
                if (data.result.success) {
                    files.push(data.result.fileId);
                    $(progressContent).remove();
                    $(fileContent).click(function(event) {
                        event.preventDefault();
                        window.location = location.origin + "/attachment/download/" + data.result.fileId;
                    });
                    removeButton = createRemoveButton(data.result.fileId, fileContent);
                    $(fileButton).append(removeButton);

                    $(fileButton).removeClass("disabled");

                } else {
                    $(errorSpan).text(data.result.error);
                    $(fileContent).remove();
                }
            },
            singleFileUploads: false,
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
            },
            maxFileSize: 5242880,
            acceptFileTypes: /(\.|\/)(jpe?g|png)$/i,
            messages: {
                acceptFileTypes: "Wrong file format. Only images are allowed.",
                maxFileSize: "File too large. Maximum allowed size is 5 MB."
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
                    if (position !== -1) {
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
        },
        reset: function() {
            while (files.length > 0) {
                files.pop();
            }
            filesPanel.empty();
        },
        initialize: function(issueId) {
            reset();
            
            issueTrackerService.getAttachmentsForIssue(issueId).done(function(data) {
                if (data.success) {
                    for (var index = 0; index < data.attachments.length; index++) {
                        files.push(data.attachments[index].id);
                        
                        var fileContent = createFileContent();
                        var fileButton = createFileButton();

                        var fileText = createFileText(data.attachments[index].originalName);
                        
                        var removeButton = createRemoveButton(data.attachments[index].id, fileContent);
                        
                        $(fileButton).append(fileText);

                        $(fileContent).append(fileButton);
                        $(filesPanel).append(fileContent);
                        
                        $(fileButton).append(removeButton);

                        $(fileButton).removeClass("disabled");

                        
                        $(fileContent).click(function(event) {
                            event.preventDefault();
                            window.location = location.origin + "/attachment/download/" + data.attachments[index].id;
                        });
                    }
                }
            });
        }
    };
}


