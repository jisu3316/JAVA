<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <title>Pricing - Mentor Bootstrap Template</title>
    <meta content="" name="description" />
    <meta content="" name="keywords" />

    <!-- Favicons -->
    <link href="../assets/img/favicon.png" rel="icon" />
    <link href="../assets/img/apple-touch-icon.png" rel="apple-touch-icon" />

    <!-- Google Fonts -->
    <link
      href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
      rel="stylesheet"
    />

    <!-- Vendor CSS Files -->
    <link href="../assets/vendor/animate.css/animate.min.css" rel="stylesheet" />
    <link href="../assets/vendor/aos/aos.css" rel="stylesheet" />"src/main/webapp/resources/assets/img/favicon.png"
    <link
      href="../assets/vendor/bootstrap/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="../assets/vendor/bootstrap-icons/bootstrap-icons.css"
      rel="stylesheet"
    />
    <link href="../assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet" />
    <link href="../assets/vendor/remixicon/remixicon.css" rel="stylesheet" />
    <link href="../assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" />

    <!-- Template Main CSS File -->
    <link href="../assets/css/style.css" rel="stylesheet" />

    <script type="text/javascript" language="javascript" 
		     src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script type="text/javascript">
	/*$(function(){
		$("#phone_id").on("keyup",function(){
			var phonecheck = $("#phone_id").val();
			phonecheck=phonecheck.trim();
			let check = /^[0-9]+$/;
			if(phonecheck != null){
				if (!check.test(phonecheck)) {
					//alert("숫자만 입력 가능합니다.");
					
					$("#phone_id").val("");
				}
			}else{
				
			}
		});
	});	*/
	
	$(function(){
		$("#joinbutton").on("click",function(){
			var emailcheck = $("#email_id").val();
			emailcheck.trim();
			let check = /\S+@\S+\.\S+/;
			if(emailcheck != null){
				if (!check.test(emailcheck)) {
					alert("이메일을 제대로된 형식으로 입력해주세요.");
					$("#email_id").val("");
					return false;
				}
			}
			var pwcheck = $("#pwd_id").val();
			var pwcheck2 = $("#pwd2_id").val();
			if(pwcheck!=pwcheck2){
				alert("비밀번호가 다릅니다");
				return false;
			}
			$.ajax({
				url: "../member/join.json",
				type: "POST",
				data: $('#joinform').serialize(),
				success: function(data){
					console.log(data);
					if(data==0){
						alert("회원가입성공");
						location="../";
					}else if(data==1){
						alert("이미 가입된 번호입니다");
					}else if(data==2){
						alert("이미 가입된 이메일입니다.");
					}
				}
			});
		});
	});
</script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
    //카카오회원가입
      window.Kakao.init("11400a9267d93835389eb9255fcaad0b");
      function kakaojoin(){
    	  Kakao.Auth.loginForm({
            scope:'profile_nickname, account_email, gender,	birthday',
            success:function(authObj){
                console.log(authObj);
                window.Kakao.API.request({
                    url:'/v2/user/me',
                    success:res => {
                        const kakao_account =res.kakao_account;
                        console.log(kakao_account);
                        var kakaoname = res.properties['nickname'];
                        var kakaoemail = res.kakao_account.email;
                        var kakaogender = res.kakao_account.gender;
                        var kakaobirth = res.kakao_account.birthday;
                        $("#mname_id").val(kakaoname);
                        $("#email_id").val(kakaoemail);
                        $("#birth_id").val(kakaobirth);
                        if(kakaogender=="male") $("#gender1_id").prop('checked', true);
                        if(kakaogender=="female") $("#gender2_id").prop('checked', true);
                  		alert("이름: "+kakaoname+",이메일: "+kakaoemail+",생일 : "+kakaobirth+",성별 : "+kakaogender);
                      //alert(res.kakao_account.email + ' (' + res.properties['nickname'] + ') 님 환영합니다.');
                              
                    }
                })
            }
          });
      }
      </script>
  </head>

  <body>
    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top">
      <div class="container d-flex align-items-center">
        <h1 class="logo me-auto"><a href="/">Mentor</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

        <nav id="navbar" class="navbar order-last order-lg-0">
          <ul>
            <li><a class="active" href="/">Home</a></li>
            <li><a href="about.html">About</a></li>
            <li><a href="myGroup.html">나의 모임</a></li>
            <!--로그인시에만 보이게 하기-->
            <li><a href="boardMain.html">게시판</a></li>
            <li>
              <a href="wishlist.html"
                >찜목록
                <span class="badge bg-dark text-white ms-1 rounded-pill"
                  >0</span
                >
              </a>
            </li>

            <li class="dropdown">
              <a href="#"
                ><span>고객지원</span> <i class="bi bi-chevron-down"></i
              ></a>
              <ul>
                <li><a href="notice.html">공지사항</a></li>
                <li><a href="FAQ.html">자주묻는 질문</a></li>
                <li><a href="QA.html">Q&A</a></li>
                <li><a href="contact.html">Contact</a></li>
              </ul>
            </li>
            <li><a href="login.html">로그인</a></li>
          </ul>
          <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <!-- .navbar -->
        <!--로그인전에는 회원가입만 보이고 로그인하면 모임만들기만 보이게 하는건 어떤지??-->
        <a href="join.html" class="get-started-btn">회원가입</a>
        <a href="groupCreate.html" class="get-started-btn">모임만들기</a>
      </div>
    </header>
    <!-- End Header -->

    <main id="main">
      <!-- ======= Breadcrumbs ======= -->
      <div class="breadcrumbs" data-aos="fade-in">
        <div class="container">
          <h1>회원가입</h1>
        </div>
      </div>
      <!-- End Breadcrumbs -->

      <!-- ======= Pricing Section ======= -->
      <section
        class="vh-100"
        style="background-color: #eee; box-sizing: content-box"
      >
        <div class="container h-100" data-aos="flip-down">
          <div
            class="row d-flex justify-content-center align-items-center h-100"
          >
            <div class="col-lg-12 col-xl-11">
              <div class="card text-black" style="border-radius: 25px">
                <div class="card-body p-md-5">
                  <div class="row justify-content-center">
                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                      <p class="text-center h1 fw-bold mb-3 mx-1 mx-md-4 mt-2">
                        <button
                          type="button"
                          class="btn btn-warning btn-lg btn-block mt-0 mb-0"
                          onclick="location.href='javascript:kakaojoin()'"
                        >
                          <i class="bi bi-chat-fill"></i>
                          카카오톡 계정으로 회원가입
                        </button>
                      </p>
                      <p class="divider-text mt-2 mb-2">
                        <span class="bg-light">OR</span>
                      </p>
                      <!--거주지/관심지역/이름/생년월일/비번/비번확인/전화번호/성별-->
                      <form class="mx-1 mx-md-4" id="joinform">
                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example1c"
                              >이름</label
                            >
                            <input
                              type="text"
                              name="mname"
                              id="mname_id"
                              class="form-control"
                              value=""
                            />
                          </div>
                        </div>
                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example1c"
                              >이메일</label
                            >
                            <input
                              type="email"
                              name="email"
                              id="email_id"
                              class="form-control"
                              placeholder="example@google.com"
                              value=""
                            />
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example3c"
                              >생년월일</label
                            >
                            <input
                              type="date"
                              name="birth"
                              id="birth_id"
                              class="form-control"
                              value=""
                              
                            />
                          </div>
                        </div>
                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example4c"
                              >비밀번호</label
                            >
                            <input
                              type="password"
                              name="pwd"
                              id="pwd_id"
                              class="form-control"
                              value=""
                            />
                          </div>
                        </div>
                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example4c"
                              >비밀번호 확인</label
                            >
                            <input
                              type="password"
                              name="pwd2_id"
                              id="pwd2_id"
                              class="form-control"
                            />
                          </div>
                        </div>
                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example4c"
                              >전화번호</label
                            >
                            <input
                              type="text"
                              name="phone"
                              pattern="[0-9]+"
                              id="phone_id"
                              class="form-control"
                              maxlength="11"
                              value=""
                              oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                            />
                          </div>
                        </div>
                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example4cd"
                              >관심사</label
                            >
                            <select class="form-control" name="category">
                              <option>선택</option>
                              <option value="운동">운동</option>
                              <option value="공부">공부</option>
                              <option value="놀기">놀기</option>
                            </select>
                          </div>
                        </div>
                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example4cd"
                              >거주지</label
                            >
                            <select class="form-control" name="maddr">
                              <option>선택</option>
                              <option value="서울">서울</option>
                              <option value="경기">경기</option>
                              <option value="인천">인천</option>
                            </select>
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example4cd"
                              >관심지역</label
                            >
                            <select class="form-control" name="pfr_loc">
                              <option>선택</option>
                              <option value="서울">서울</option>
                              <option value="경기">경기</option>
                              <option value="인천">인천</option>
                            </select>
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-0">
                          <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-2">
                            <label class="form-label mb-0" for="form3Example4cd"
                              >성별</label
                            ><br />
                            <div
                              class="btn-group btn-group-toggle col-12"
                              data-toggle="buttons"
                            >
                              <label class="btn btn-light active">
                                <input
                                  type="radio"
                                  name="gender"
                                  id="gender1_id"
                                  value="male"
                                  autocomplete="off"
                                  checked
                                />
                                남
                              </label>
                              <label class="btn btn-light">
                                <input
                                  type="radio"
                                  name="gender"
                                  value="female"
                                  id="gender2_id"
                                  autocomplete="off"
                                />
                                여
                              </label>
                            </div>
                            <br />
                            <br />
                          </div>
                        </div>

                        <div
                          class="form-check d-flex justify-content-center mb-3"
                        >
                          <input
                            class="form-check-input me-2"
                            type="checkbox"
                            value=""
                            id="form2Example3c"
                          />
                          <label class="form-check-label" for="form2Example3">
                            <a href="#!">ToGather 이용약관</a>에
                            동의합니다(필수)
                          </label>
                        </div>

                        <div
                          class="d-flex justify-content-center mx-4 mb-3 mb-lg-4"
                        >
                          <button
                            type="button"
                            class="btn btn-success"
                            style="margin-right: 30px" id="joinbutton"
                            
                          >
                            가입하기
                          </button>
                          <button type="button" class="btn btn-secondary">
                            취소
                          </button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- End Pricing Section -->
    </main>
    <!-- End #main -->

    <!-- ======= Footer ======= -->
    <footer id="footer" style="padding-top: 50px">
      <div class="footer-top">
        <div class="container">
          <div class="row">
            <div class="col-lg-3 col-md-6 footer-contact">
              <h3>Mentor</h3>
              <p>
                A108 Adam Street <br />
                New York, NY 535022<br />
                United States <br /><br />
                <strong>Phone:</strong> +1 5589 55488 55<br />
                <strong>Email:</strong> info@example.com<br />
              </p>
            </div>

            <div class="col-lg-2 col-md-6 footer-links">
              <h4>Useful Links</h4>
              <ul>
                <li>
                  <i class="bx bx-chevron-right"></i> <a href="#">Home</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i> <a href="#">About us</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i> <a href="#">Services</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="#">Terms of service</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="#">Privacy policy</a>
                </li>
              </ul>
            </div>

            <div class="col-lg-3 col-md-6 footer-links">
              <h4>Our Services</h4>
              <ul>
                <li>
                  <i class="bx bx-chevron-right"></i> <a href="#">Web Design</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="#">Web Development</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="#">Product Management</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i> <a href="#">Marketing</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="#">Graphic Design</a>
                </li>
              </ul>
            </div>

            <div class="col-lg-4 col-md-6 footer-newsletter">
              <h4>Join Our Newsletter</h4>
              <p>
                Tamen quem nulla quae legam multos aute sint culpa legam noster
                magna
              </p>
              <form action="" method="post">
                <input type="email" name="email" /><input
                  type="submit"
                  value="Subscribe"
                />
              </form>
            </div>
          </div>
        </div>
      </div>

      <div class="container d-md-flex py-4">
        <div class="me-md-auto text-center text-md-start">
          <div class="copyright">
            &copy; Copyright <strong><span>Mentor</span></strong
            >. All Rights Reserved
          </div>
          <div class="credits">
            <!-- All the links in the footer should remain intact. -->
            <!-- You can delete the links only if you purchased the pro version. -->
            <!-- Licensing information: https://bootstrapmade.com/license/ -->
            <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/ -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
          </div>
        </div>
        <div class="social-links text-center text-md-right pt-3 pt-md-0">
          <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
          <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
          <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
          <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
          <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
        </div>
      </div>
    </footer>
    <!-- End Footer -->
  </body>
  <div id="preloader"></div>
  <a
    href="#"
    class="back-to-top d-flex align-items-center justify-content-center"
    ><i class="bi bi-arrow-up-short"></i
  ></a>

  <!-- Vendor JS Files -->
  <script src="../assets/vendor/purecounter/purecounter.js"></script>
  <script src="../assets/vendor/aos/aos.js"></script>
  <script src="../assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="../assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="../assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="../assets/js/main.js"></script>
</html>
