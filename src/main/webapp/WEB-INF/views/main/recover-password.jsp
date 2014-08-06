<div class="col-lg-4 col-lg-offset-4 Absolute-Center login-register-form">
    <form id="registerForm" class="form-horizontal col-lg-12" method='POST'>
        <fieldset>
            <legend class="col-lg-10 col-lg-offset-1">Enter the username or email of your account </legend>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-1">
                    <input class="form-control" id="inputUsernameEmail" type="text" placeholder="Username or e-mail" name="username" />
                    <span id="usernameEmailError" class="text-warning errors"></span>
                </div>
            </div>
            <div class="form-group text-center">
                <div class="col-lg-12">
                    <button class="btn btn-primary" id="submitButton">Recover password</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<script src="/resources/js/issueTrackerService.js" type="text/javascript"></script>
<script src="/resources/js/recover-password.js" type="text/javascript"></script>