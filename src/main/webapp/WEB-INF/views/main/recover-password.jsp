<div class="col-lg-4 col-lg-offset-4 Absolute-Center login-register-form">
    <div>
        <img src="/resources/img/logo.png" alt="Issue Tracker" class="col-lg-12 logo">
    </div>
    <form id="registerForm" class="form-horizontal col-lg-12" method='POST'>
        <fieldset>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-1">
                    <input class="form-control" id="inputUsername" type="text" placeholder="Username" name="username" />
                    <span id="usernameError" class="text-warning errors"></span>
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
<script src="/resources/js/recover-password.js" type="text/javascript"></script>