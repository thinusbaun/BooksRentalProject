        <div class="jumbotron">
            <form id="signupform" method="post" class="form-horizontal">
                <div class="form-group has-feedback" id="logindiv">
                    <label for="loginInput" class="control-label col-md-2">Login</label>

                    <div class="col-md-6">
                        <input type="text" name="login" id="loginInput" class="form-control" placeholder="Login"/>
                        <span class="glyphicon form-control-feedback" id="login"></span>

                        <div class="hidden" id="loginexists">Login już istnieje!</div>
                        <span class="hidden" id="loginEmpty">Login nie może być pusty.</span>
                        <span class="hidden" id="loginTooLong">Login zbyt długi.</span>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="hasloInput" class="control-label col-md-2">Hasło</label>

                    <div class="col-md-6">
                        <input type="password" name="haslo" id="hasloInput" class="form-control" placeholder="Hasło"/>
                        <span class="glyphicon form-control-feedback" id="haslo"></span>
                        <span class="hidden" id="hasloEmpty">Hasło nie może być puste.</span>
                        <span class="hidden" id="hasloTooLong">Hasło zbyt długie.</span>
                    </div>
                </div>

                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary pull-right">Zaloguj</button>
                </div>
            </form>

        </div>
    </div>
