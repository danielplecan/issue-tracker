<div class=" col-lg-6 col-lg-offset-3">
    <div>
        <img src="/resources/img/logo.png" alt="Issue Tracker" class="col-lg-12 logo">
    </div>
    <form id="editForm"  class="well form-horizontal col-lg-12" method='POST'>
        <fieldset>
            <div class="form-group">
                <label class="col-lg-3 control-label">Name</label>
                <div class="col-lg-9">
                    <input class="form-control" id="inputName" type="text" placeholder="Name" name="name" autofocus value="${name}" />
                    <span id="nameError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">Username</label>
                <div class="col-lg-9">
                    <input class="form-control" id="inputUsername" type="text" placeholder="Username" name="username" value="${username}" />
                    <span id="usernameError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">Email</label>
                <div class="col-lg-9">
                    <input class="form-control" id="inputEmail" type="email" placeholder="Email" name="email" value="${email}" />
                    <span id="emailError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">Old password</label>
                <div class="col-lg-9">
                    <input class="form-control" id="inputOldPassword" type="password" placeholder="Old password" name="oldPassword" />
                    <span id="oldPasswordError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">New password</label>
                <div class="col-lg-9">
                    <input class="form-control" id="inputNewPassword" type="password" placeholder="New password" name="password" />
                    <span id="mainPasswordError" class="text-warning errors" > </span>              
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">Type it again</label>
                <div class="col-lg-9">
                    <input  class="form-control" id="inputRetypePassword" type="password" placeholder="Retype password" />
                    <span id="passwordError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-lg-12">
                    <button class="btn btn-default" id="cancelButton">Cancel</button>
                    <button class="btn btn-primary" id="submitButton">Edit</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<script src="/resources/js/registerValidationService.js" type="text/javascript"></script>
<script src="/resources/js/editProfile.js" type="text/javascript"></script>
