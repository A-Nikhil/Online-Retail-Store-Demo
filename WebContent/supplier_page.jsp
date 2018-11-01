<html>
<head>
    <link rel="stylesheet" href="stylecart.css">
    <title>Online Portal</title>
    <link href="https://fonts.googleapis.com/css?family=Titillium+Web:900" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Raleway|Sacramento" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Nova+Square" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Titillium+Web:900" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</head>

<body>


<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">WELCOME
    <div class="username">${userACName}</div>
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">

    </ul>
    <form class="form-inline my-2 my-lg-0">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="landing.html">LOGOUT <span class="sr-only">(current)</span></a>
        </li>

      </ul>
    </form>

  </div>
</nav>
<!-- NAVBAR ENDS -->


  <div class="container-fluid main" align="middle" >


        <div class="container" align="center">
            <div class="container-fluid t1">
              <h2><strong>SUPPLIER DASHBOARD</strong><hr></h2>
            </div>
            <div class="container-fluid dropList">

              <div class="list-group">
                <div class="list-group-item" align="left">
                  <div class="row">

                    <div class="col-sm-7">
                      Product Title: <strong>${ProductList[0][0]}</strong><div id="name1"></div><br>
                      Product Description: <strong>${ProductList[0][1]}</strong><br>
                      category: <strong>${ProductList[0][2]}</strong><br>
                      Price Offered: <strong>${ProductList[0][3]}</strong><br>
                    </div>
                    <div class="col-sm-4 del" id="a1" align="right">
                    </div>
                  </div>
                </div>
                <div class="list-group-item" align="left">
                  <div class="row">

                    <div class="col-sm-7">
                      Product Title: <strong>${ProductList[1][0]}</strong><div id="name1"></div><br>
                      Product Description: <strong>${ProductList[1][1]}</strong><br>
                      category: <strong>${ProductList[1][2]}</strong><br>
                      Price Offered: <strong>${ProductList[1][3]}</strong><br>
                    </div>
                    <div class="col-sm-4 del" id="a2" align="right">
                    </div>
                  </div>
                </div>
                <div class="list-group-item" align="left">
                  <div class="row">

                    <div class="col-sm-7">
                      Product Title: <strong>${ProductList[2][0]}</strong><div id="name1"></div><br>
                      Product Description: <strong>${ProductList[2][1]}</strong><br>
                      category: <strong>${ProductList[2][2]}</strong><br>
                      Price Offered: <strong>${ProductList[2][3]}</strong><br>
                    </div>
                    <div class="col-sm-4 del" id="a2" align="right">
                    </div>
                  </div>
                </div>
                <div class="list-group-item" align="left">
                  <div class="row">

                    <div class="col-sm-7">
                      Product Title: <strong>${ProductList[3][0]}</strong><div id="name1"></div><br>
                      Product Description: <strong>${ProductList[3][1]}</strong><br>
                      category: <strong>${ProductList[3][2]}</strong><br>
                      Price Offered: <strong>${ProductList[3][3]}</strong><br>
                    </div>
                    <div class="col-sm-4 del" id="a3" align="right">
                    </div>
                  </div>
                </div>
              </div>
        </div>
        </div>
     </div>

<br><br><br>

<div class="container" align="center">
  <button type="button" align="center" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Add item</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
  <form action="addproduct" method="post" id="form1">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Add Product Details</h4>
        </div>
        <div class="modal-body">
            <div id="fld1">
            <div class="input-group ip1" align="middle">
                <input type="text" id="i1" name="itemname" placeholder="Product Title" class="form-control f1" >
            </div>
            </div>
            <br>
            <div id="fld2">
            <div class="input-group ip1" align="middle">
                <input type="text" id="i2" name="description" placeholder="Product Description" class="form-control f2" >
            </div>
            </div>
            <br>
            <div id="fld3">
            <div class="input-group ip1" align="middle">
                <input type="text" id="i3" name="category" placeholder="Category" class="form-control f3" >
            </div>
            <br>
            </div>
            <div id="fld4">
            <div class="input-group ip1" align="middle">
                <input type="text" id="i3" name="price" placeholder="Price" class="form-control f3" >
            </div>
            </div>
            <br>
        </div>
        <div class="modal-footer">
          <button type="submit" id="ref" name="submit" class="btn btn-default" >Refresh Page To Update Table</button>
        </div>
      </div>
    </div>
    </form>
  </div>
</div>



<script>
// $('#form1').submit(function() {
//   console.log("Clicked");
//     if($('#i1').val() == '' || $('#i2').val() == '' || $('#i3').val() == ''){
//         alert("Please fill in all details");
//         console.log("entered if");
//         return false;
//     }
// });


$(".del").click(function() {
    alert(this.id); // or alert($(this).attr('id'));
});



$(".btn2").click(function(){
  $('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
  });
  console.log("WOking");
});
$(".form1").hide();
$("#ref").click(function(){
     window.location.reload();
});
$("#b1").click(function(){
     window.location.href="final.html";
})

function addItem(){
  $(".form1").show("slow");
}
</script>
</body>
</html>
