<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Online Portal</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Raleway|Sacramento" rel="stylesheet">
    <script
  			  src="https://code.jquery.com/jquery-3.3.1.js"
  			  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  			  crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Nova+Square" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</head>

<body>


<div class="container-fluid main" align="middle" >
	<form action="loginsupplier" method="post">
    <div class="container c1" align="middle">
      <div id="cont1">
        <div id="txt1">Supplier SignIn<br>_________<br>Sign into your account</div><hr>
        <div id="mail">
        <div class="input-group ip1" align="middle">
            <input type="text" id="i1" placeholder="USER ID" name="userid" class="form-control f1" >
        </div>
        </div>
        <div id="psw">
        <div class="input-group ip2"  align="middle">
            <input type="password" placeholder="Password" name="password" id="i2" class="form-control f2" >
        </div>
        </div>
        <button class="btn" type="submit" id="b1" onclick="nav()"><strong>LOGIN</strong></button><BR>
        <div id="txt2">Forgot your password?</div><hr>
        <div id="txt2">Don't have an account? Sign Up.</div>

    </div>
  </div>
	</form>
</div>
<!-- <div class = "container-fluid auth" align="center">
  <h1>...Authenticating...</h1>
</div> -->

<script>
// $(".auth").hide();
function nav(){
  $(".main").fadeOut();
  var delay = 2000;
  // setTimeout(function() {}, delay);
  // $(".auth").show();
  setTimeout(function() {
    window.location.href="supplier_page.html";
}, delay);

}
</script>
</body>
</html>
