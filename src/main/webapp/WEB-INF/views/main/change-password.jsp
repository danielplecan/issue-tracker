<div class="col-lg-4 col-lg-offset-4 Absolute-Center login-register-form">
    <div>
        <img src="/resources/img/logo.png" alt="Issue Tracker" class="col-lg-12 logo">
    </div>
    <form id="registerForm" class="form-horizontal col-lg-12" method='POST'>
        <fieldset>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-1">
                    <input class="form-control" id="inputPassword" type="password" placeholder="Password" name="password" />
                    <span id="mainPasswordError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-1">
                    <input  class="form-control" id="inputRetypePassword" type="password" placeholder="Retype password" />
                    <label id="passwordMatchError" class="text-warning hidden" >Passwords don't match </label>
                    <span id="passwordError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-lg-12">
                    <button class="btn btn-primary" id="submitPassword">Change password</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<script src="/resources/js/registerValidationService.js" type="text/javascript"></script>
<script src="/resources/js/change-password.js" type="text/javascript"></script>
