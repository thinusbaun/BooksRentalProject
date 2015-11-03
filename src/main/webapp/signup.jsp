        <div class="jumbotron">
            <form id="signupform" method="post" class="form-horizontal">
                <div class="form-group has-feedback">
                    <label for="imieInput" class="control-label col-md-2">Imię</label>

                    <div class="col-md-6">
                        <input type="text" name="imie" id="imieInput" class="form-control" placeholder="Imię"/>
                        <span class="glyphicon form-control-feedback" id="fname1"></span>
                        <span class="hidden" id="imieEmpty">Imię nie może być puste.</span>
                        <span class="hidden" id="imieTooLong">Imię zbyt długie.</span>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="nazwiskoInput" class="control-label col-md-2">Nazwisko</label>

                    <div class="col-md-6">
                        <input type="text" name="nazwisko" id="nazwiskoInput" class="form-control"
                               placeholder="Nazwisko"/>
                        <span class="glyphicon form-control-feedback" id="nazwisko"></span>
                        <span class="hidden" id="nazwiskoEmpty">Nazwisko nie może być puste.</span>
                        <span class="hidden" id="nazwiskoTooLong">Nazwisko zbyt długie.</span>
                    </div>
                </div>
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

                <div class="form-group has-feedback">
                    <label for="emailInput" class="control-label col-md-2">Email</label>

                    <div class="col-md-6">
                        <input type="text" name="email" id="emailInput" class="form-control" placeholder="Email"/>
                        <span class="glyphicon form-control-feedback" id="email"></span>
                        <span class="hidden" id="emailEmpty">Email nie może być pusty.</span>
                        <span class="hidden" id="emailTooLong">Email zbyt długi.</span>
                        <span class="hidden" id="emailDoesNotMatch">Email nie jest prawidłowy.</span>
                    </div>
                </div>

                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary pull-right">Rejestracja</button>
                </div>
            </form>

        </div>
    </div>
        <script src="js/signup.js"></script>

