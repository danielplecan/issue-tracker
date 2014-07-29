<!--<form id="uploadForm" action="/upload" method="POST" enctype="multipart/form-data">
    File: 
    <input type="file" name="file" />
    <input type="submit" value="Upload file:" /> Click to upload file:
</form>
<script src="/resources/js/upload.js" type="text/javascript"></script>-->

<div style="width:500px;padding:20px">
 
    <input id="fileupload" type="file" name="file" data-url="/upload" multiple>
 
    <div id="dropzone">Drop files here</div>
 
    <div id="progress">
        <div style="width: 0%;"></div>
    </div>
 
    <table id="uploaded-files">
        <tr>
            <th>File Name</th>
            <th>File Size</th>
            <th>File Type</th>
            <th>Download</th>
        </tr>
    </table>
    <div class="progress progress-striped active">
        <div class="progress-bar" style="width: 0%"></div>
    </div>
</div>
<script src="/resources/js/upload.js" type="text/javascript"></script>