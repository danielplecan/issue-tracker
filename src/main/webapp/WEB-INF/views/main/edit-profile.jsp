<div class=" col-lg-6 col-lg-offset-3">
    <div>
        <img src="/resources/img/logo.png" alt="Issue Tracker" class="col-lg-12 logo">
    </div>
    <form id="editForm"  class="well form-horizontal col-lg-12" method='POST'>
        <fieldset>
            <legend class="col-lg-12">Edit your profile</legend>
            <div class="form-group">
                <label class="col-lg-2 control-label">Name</label>
                <div class="col-lg-10">
                    <input class="form-control" id="inputName" type="text" placeholder="Name" name="name" autofocus value="${name}" />
                    <span id="nameError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">Username</label>
                <div class="col-lg-10">
                    <input class="form-control" id="inputUsername" type="text" placeholder="Username" name="username" value="${username}" />
                    <span id="usernameError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">Email</label>
                <div class="col-lg-10">
                    <input class="form-control" id="inputEmail" type="email" placeholder="Email" name="email" value="${email}" />
                    <span id="emailError" class="text-warning errors"></span>
                </div>
            </div>
            <div  class="panel ">
                <div id="changePasswordButton" class="panel-heading">
                    Change the password
                </div>
                <div id="passwordGroup" style="display:none;" class="panel-body">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">New password</label>
                        <div class="col-lg-10">
                            <input class="form-control" id="inputNewPassword" type="password" placeholder="New password" name="password" />
                            <span id="mainPasswordError" class="text-warning errors" > </span>              
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Type it again</label>
                        <div class="col-lg-10">
                            <input  class="form-control" id="inputRetypePassword" type="password" placeholder="Retype password" />
                            <span id="newPasswordError" class="text-warning errors"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label">Password</label>
                <div class="col-lg-10">
                    <input title="Confirm modifications with your password" class="form-control" id="inputPassword" type="password" placeholder="Password" name="oldPassword" />
                    <span id="oldPasswordError" class="text-warning errors"></span>
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
