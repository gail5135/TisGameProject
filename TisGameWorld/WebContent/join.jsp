<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
</head>

<body class="">
  <div class="text-center bg-dark text-white py-3">
    <div class="container">
      <div class="row bg-dark">
        <div class="p-5 col-lg-6 col-10 mx-auto border-0">
          <img class="img-fluid d-block mb-3" style="width: 200px; text-align:center" src="./images/tisgameworldlogo.png" id="logo">
          <form class="text-left">
            <div class="form-group text-center"> <label for="form18">Your ID</label> <input type="text" class="form-control text-center" id="form18" placeholder="사용하실 ID를 입력해주세요."> </div>
            <div class="form-row">
              <div class="form-group col-md-6 text-center"> <label for="form19">Password</label> <input type="password" class="form-control text-center" id="form19" placeholder="••••••••"> </div>
              <div class="form-group col-md-6 text-center"> <label for="form20">Confirm Password</label> <input type="password" class="form-control text-center" id="form20" placeholder="••••••••"> </div>
            </div>
            <div class="form-group text-center"> <label for="form17">Your Nickname</label> <input type="text" class="form-control text-center" id="form17" placeholder="사용하실 닉네임을 입력하세요."> </div>
            <div class="form-group text-center"> <label for="form17">Your Gender</label>
              <div class="form-row">
                <div class="form-group col-md-6 text-center">
                  <div class="form-check"> <input class="form-check-input" type="checkbox" id="form21" value="on"> <label class="form-check-label" for="form21">Male</label> </div>
                </div>
                <div class="form-group col-md-6 text-center">
                  <div class="form-check"> <input class="form-check-input" type="checkbox" id="form21" value="on"> <label class="form-check-label" for="form21">Female</label> </div>
                </div>
              </div>
            </div>
            <div class="form-group text-right">
              <div class="form-check"> <input class="form-check-input" type="checkbox" id="form21" value="on"> <label class="form-check-label" for="form21"><a href="#">이용약관</a>에&nbsp;동의합니다.&nbsp;</label> </div>
            </div> <button type="submit" class="btn btn-block btn-success mb-2">회원가입</button><button type="submit" class="btn btn-block btn-secondary">다시쓰기</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>