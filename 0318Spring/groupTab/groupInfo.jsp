<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html >
  <head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <title>Togather</title>
    <meta content="" name="description" />
    <meta content="" name="keywords" />

    <!-- Favicons -->
    <link href="/assets/img/favicon.png" rel="icon" />
    <link href="/assets/img/apple-touch-icon.png" rel="apple-touch-icon" />

    <!-- Google Fonts -->
    <link
      href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
      rel="stylesheet"
    />

    <!-- Vendor CSS Files -->
    <link href="/assets/vendor/animate.css/animate.min.css" rel="stylesheet" />
    <link href="/assets/vendor/aos/aos.css" rel="stylesheet" />
    <link
      href="/assets/vendor/bootstrap/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
      rel="stylesheet"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
      rel="stylesheet"
    />

    <link
      href="/assets/vendor/bootstrap-icons/bootstrap-icons.css"
      rel="stylesheet"
    />
    <link href="/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet" />
    <link href="/assets/vendor/remixicon/remixicon.css" rel="stylesheet" />
    <link href="/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" />
	<script type="text/javascript" language="javascript" 
		     src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <!-- Template Main CSS File -->
    <link href="/assets/css/style.css" rel="stylesheet" />
    <script type="text/javascript">
    
    function groupJoin(){
    		var joinGroup =confirm("모임에 가입하시겠습니까?"); 
    		var mnum = ${m.mnum};
  			var gseq = ${groupInfo.gseq};
  			var result = {"mnum":mnum,"gseq":gseq};
    		if(joinGroup==true){
	   			$(function(){
    				$.ajax({
	   					url: "memInGroup.json",
	   					type: "POST",
	   					data: result,
	   					success: function(data){
	   						console.log(data);
	   						if(data==ok){
	   							alert("가입완료");
	   							
	   						}else{
	   							alert("가입실패");
	   					
	   						}
	   					}
	   				});  
    				location.reload();
	   			});
    		}else{
    			
    		}
    	}

    	function groupQuit(){
    		var joinGroup =confirm("모임에 탈퇴하시겠습니까?"); 
    		var mnum = ${m.mnum};
  			var gseq = ${groupInfo.gseq};
  			var result = {"mnum":mnum,"gseq":gseq};
    		if(joinGroup==true){
	   			$(function(){
    				$.ajax({
	   					url: "groupQuit.json",
	   					type: "POST",
	   					data: result,
	   					success: function(data){
	   						console.log(data);
	   						if(data==0){
	   							alert("탈퇴완료");
	   							location.href="/";
	   						}else{
	   							
	   						}
	   					}
	   				});  
    				location.reload();
	   			});
    		}else{
    			
    		}
    	}
    	
    	function groupDeleteCheck(){ 
    		var mnum = ${m.mnum};
  			var gseq = ${groupInfo.gseq};
  			var result = {"mnum":mnum,"gseq":gseq};
	   			$(function(){
    				$.ajax({
	   					url: "groupDeletecheck.json",
	   					type: "POST",
	   					data: result,
	   					success: function(data){
	   						if(data==0){//모임장일때일때
	   							groupDelete();
	   							console.log("check0: "+data);
	   						}else{//모임장 아닐때
	   							console.log("check1: "+data);
	   							alert("모임장만 삭제 가능합니다");
	   						}
	   					}
	   				});  
	   			});
    	}
    	
    	function groupUpdateCheck(){ 
    		var mnum = ${m.mnum};
  			var gseq = ${groupInfo.gseq};
  			var result = {"mnum":mnum,"gseq":gseq};
	   			$(function(){
    				$.ajax({
	   					url: "groupUpdatecheck.json",
	   					type: "POST",
	   					data: result,
	   					success: function(data){
	   						if(data==0){//모임장일때일때
	   							groupUpdate();
	   							console.log("check0: "+data);
	   						}else if(data==1){//운영진일때
	   							groupUpdate();
	   							console.log("check1: "+data);
	   							//alert("모임장,운영자만 수정 가능합니다");
	   						}else if(data==2){//일반회원
	   							console.log("check2: "+data);	
	   							alert("모임장,운영자만 수정 가능합니다");
	   						}else {//모임에없을때
	   							alert("모임장,운영자만 수정 가능합니다");
	   							console.log("check3: "+data);
	   						}
	   					}
	   				});  
	   			});
    	}
    	
    	function groupUpdate(){
    		location="groupUpdate.do?gseq=${groupInfo.gseq}";
    	}
    	
    	function groupDelete(){
    		location="groupDelete.do?gseq=${groupInfo.gseq}";
    	}
    	
    	function memberInfo(index){
    		var arr = new Array();
    		<c:forEach var="memInGroupName" items="${memInGroupName}">	              
	        	arr.push({name:"${memInGroupName}"});
        	</c:forEach>
        	console.log(arr[index].name);
        	baby_login = window.open(
        	  "../member/memberInfo?mname="+arr[index].name, "memberInfo", 
        	   "width=1000, height=900, top=100, left=100");
        }
    </script>
  </head>

  <body>
    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top">
      <div class="container d-flex align-items-center">
        <h1 class="logo me-auto"><a href="../">Togather</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

        <nav id="navbar" class="navbar order-last order-lg-0">
          <ul>
            <li><a class="active" href="../">Home</a></li>
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
            <c:choose>
           		<c:when test="${m eq null}">
		            <li><a href="login.html">로그인</a></li>		       
	          	</c:when>
       			<c:otherwise>
       			<li><a href="javascript:void(0);" onclick="signout();">로그아웃</a></li>
            	</c:otherwise>
         	</c:choose>
            </ul>
          <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <!-- .navbar -->

        <!--로그인전에는 회원가입만 보이고 로그인하면 모임만들기만 보이게 하는건 어떤지??-->
        <c:choose>
           		<c:when test="${m eq null}">
		        	<a href="../member/joinform.do" class="get-started-btn">회원가입</a>
		        </c:when>
		        <c:otherwise>
		        	<a href="groupCreate.do" class="get-started-btn">모임만들기</a>
		        </c:otherwise>
         </c:choose>
      </div>
    </header>
    <!-- End Header -->

    <main id="main">
      <!-- ======= Breadcrumbs ======= -->
      <div class="breadcrumbs" data-aos="fade-in">
        <div class="container">
          <h1>${groupInfo.gname}</h1>
          <p>모임 소개? or 지역?</p>
        </div>
      </div>
      <!-- End Breadcrumbs -->

      <!-- ======= Cource Details Section ======= -->
      <section id="course-details" class="course-details">
        <div class="container" data-aos="fade-up">
          <div class="row">
            <div class="col-lg-8">
              <img
                src="/assets/img/course-details.jpg"
                class="img-fluid"
                alt=""
              />
              <h3>${groupInfo.gname}</h3>
              <p>
                ${groupInfo.gintro}
              </p>
            </div>
            <div class="col-lg-4">
              <div
                class="course-info d-flex justify-content-between align-items-center"
              >
                <h5>모임장</h5>
                <p><a href="#">${groupMemberName.mname }</a></p>
              </div>

              <div
                class="course-info d-flex justify-content-between align-items-center"
              >
                <h5>지역</h5>
                <p>${groupInfo.gloc}</p>
              </div>

              <div
                class="course-info d-flex justify-content-between align-items-center"
              >
                <h5>관심사</h5>
                <p>${groupInfo.interest}</p>
              </div>

              <div
                class="course-info d-flex justify-content-between align-items-center"
              >
                <h5>정원</h5>
                <p>${groupMemberCount}/${groupInfo.limit}</p>
              </div>
              <!-- 정모목록 부분-->
              <div class="accordion acoordion-flush" id="accordionExample">
                <div class="accordion-item">
                  <h2 class="accordion-header" id="headingOne">
                    <button
                      class="accordion-button collapsed"
                      type="button"
                      data-bs-toggle="collapse"
                      data-bs-target="#collapseOne"
                      aria-expanded="true"
                      aria-controls="collapseOne"
                    >
                     	 정모목록
                    </button>
                  </h2>
                  <div
                    id="collapseOne"
                    class="accordion-collapse collapse"
                    aria-labelledby="headingOne"
                    data-bs-parent="#accordionExample"
                  >
                    <div class="accordion-body">
                      <a href="">정모목록1</a>
                    </div>
                    <div class="accordion-body">
                      <a href="">정모목록2</a>
                    </div>
                    <div class="accordion-body">
                      <a href="">정모목록3</a>
                    </div>
                    <div class="accordion-body">
                      <a href="">정모목록4</a>
                    </div>
                    <div class="accordion-body">
                      <a href="">정모목록5</a>
                    </div>
                  </div>
                </div>
              </div>
              <!--정모목록 끝-->
              <div
                class="course-info d-flex justify-content-between align-items-center"
              >
             <nav id="navbar" class="navbar order-last order-lg-0">
		       <ul>
			       <li class="dropdown">
		              <a href="#">
		              	모임멤버 
		              	<i class="bi bi-chevron-down"></i>
		              	</a>
			              <ul >
			              	<c:forEach var="memInGroupName" items="${memInGroupName}" varStatus="index">   
			                	<c:choose>
				                	<c:when test="${m.mname eq memInGroupName}">
				                		<li><a href="javascript:void(0)">		           
					                	${memInGroupName}</a></li>
				                	</c:when>
				                	<c:otherwise>
					                	<li><a href="javascript:void(0)" 
					                	onclick="location.href='javascript:memberInfo(${index.index})'">		           
					                	${memInGroupName}</a></li>
					                </c:otherwise>
				                </c:choose>
			                </c:forEach>
			              </ul>
			             
	            	</li>
	            </ul>
	          </nav>
	          </div>
              <div class="d-grid gap-2 mt-3 mb-3">
                <button
                  type="button"
                  class="btn btn-outline-success"
                  onclick="location.href='gatheringCreate.html'"
                >
                 	정모만들기
                </button>
                <button type="button" class="btn btn-outline-secondary" 
                	onclick="location.href='javascript:groupUpdateCheck()'">
                  	모임 수정하기
                </button>
                <button type="button" class="btn btn-outline-secondary"
                onclick="location.href='javascript:groupDeleteCheck()'">
                 	 모임 삭제하기
                </button>
                <c:choose>
			         <c:when test="${memInGroupCheck eq null }">
		                <button type="button" class="btn btn-outline-secondary"
		                onclick="location.href='javascript:groupJoin()'">
		                 	 모임 가입하기
		                </button>
		              </c:when>
			          <c:otherwise>
		                <button type="button" class="btn btn-outline-secondary"
		                onclick="location.href='javascript:groupQuit()'">
		                 	 모임 탈퇴하기
		                </button>
	                </c:otherwise>
                </c:choose>
              </div>
		         
            </div>
           
          </div>
          
        </div>
      </section>
      <!-- End Cource Details Section -->

      <!-- ======= Cource Details Tabs Section ======= -->
      <section id="cource-details-tabs" class="cource-details-tabs">
        <div class="container" data-aos="fade-up">
          <div class="row">
            <div class="col-lg-3">
              <ul class="nav nav-tabs flex-column">
                <li class="nav-item">
                  <a
                    class="nav-link active show"
                    data-bs-toggle="tab"
                    href="#tab-1"
                    >Modi sit est</a
                  >
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-bs-toggle="tab" href="#tab-2"
                    >Unde praesentium sed</a
                  >
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-bs-toggle="tab" href="#tab-3"
                    >Pariatur explicabo vel</a
                  >
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-bs-toggle="tab" href="#tab-4"
                    >Nostrum qui quasi</a
                  >
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-bs-toggle="tab" href="#tab-5"
                    >Iusto ut expedita aut</a
                  >
                </li>
              </ul>
            </div>
            <div class="col-lg-9 mt-4 mt-lg-0">
              <div class="tab-content">
                <div class="tab-pane active show" id="tab-1">
                  <div class="row">
                    <div class="col-lg-8 details order-2 order-lg-1">
                      <h3>Architecto ut aperiam autem id</h3>
                      <p class="fst-italic">
                        Qui laudantium consequatur laborum sit qui ad sapiente
                        dila parde sonata raqer a videna mareta paulona marka
                      </p>
                      <p>
                        Et nobis maiores eius. Voluptatibus ut enim blanditiis
                        atque harum sint. Laborum eos ipsum ipsa odit magni.
                        Incidunt hic ut molestiae aut qui. Est repellat minima
                        eveniet eius et quis magni nihil. Consequatur dolorem
                        quaerat quos qui similique accusamus nostrum rem vero
                      </p>
                    </div>
                    <div class="col-lg-4 text-center order-1 order-lg-2">
                      <img
                        src="/assets/img/course-details-tab-1.png"
                        alt=""
                        class="img-fluid"
                      />
                    </div>
                  </div>
                </div>
                <div class="tab-pane" id="tab-2">
                  <div class="row">
                    <div class="col-lg-8 details order-2 order-lg-1">
                      <h3>Et blanditiis nemo veritatis excepturi</h3>
                      <p class="fst-italic">
                        Qui laudantium consequatur laborum sit qui ad sapiente
                        dila parde sonata raqer a videna mareta paulona marka
                      </p>
                      <p>
                        Ea ipsum voluptatem consequatur quis est. Illum error
                        ullam omnis quia et reiciendis sunt sunt est. Non
                        aliquid repellendus itaque accusamus eius et velit ipsa
                        voluptates. Optio nesciunt eaque beatae accusamus lerode
                        pakto madirna desera vafle de nideran pal
                      </p>
                    </div>
                    <div class="col-lg-4 text-center order-1 order-lg-2">
                      <img
                        src="/assets/img/course-details-tab-2.png"
                        alt=""
                        class="img-fluid"
                      />
                    </div>
                  </div>
                </div>
                <div class="tab-pane" id="tab-3">
                  <div class="row">
                    <div class="col-lg-8 details order-2 order-lg-1">
                      <h3>Impedit facilis occaecati odio neque aperiam sit</h3>
                      <p class="fst-italic">
                        Eos voluptatibus quo. Odio similique illum id quidem non
                        enim fuga. Qui natus non sunt dicta dolor et. In
                        asperiores velit quaerat perferendis aut
                      </p>
                      <p>
                        Iure officiis odit rerum. Harum sequi eum illum corrupti
                        culpa veritatis quisquam. Neque necessitatibus illo
                        rerum eum ut. Commodi ipsam minima molestiae sed
                        laboriosam a iste odio. Earum odit nesciunt fugiat sit
                        ullam. Soluta et harum voluptatem optio quae
                      </p>
                    </div>
                    <div class="col-lg-4 text-center order-1 order-lg-2">
                      <img
                        src="/assets/img/course-details-tab-3.png"
                        alt=""
                        class="img-fluid"
                      />
                    </div>
                  </div>
                </div>
                <div class="tab-pane" id="tab-4">
                  <div class="row">
                    <div class="col-lg-8 details order-2 order-lg-1">
                      <h3>
                        Fuga dolores inventore laboriosam ut est accusamus
                        laboriosam dolore
                      </h3>
                      <p class="fst-italic">
                        Totam aperiam accusamus. Repellat consequuntur iure
                        voluptas iure porro quis delectus
                      </p>
                      <p>
                        Eaque consequuntur consequuntur libero expedita in
                        voluptas. Nostrum ipsam necessitatibus aliquam fugiat
                        debitis quis velit. Eum ex maxime error in consequatur
                        corporis atque. Eligendi asperiores sed qui veritatis
                        aperiam quia a laborum inventore
                      </p>
                    </div>
                    <div class="col-lg-4 text-center order-1 order-lg-2">
                      <img
                        src="/assets/img/course-details-tab-4.png"
                        alt=""
                        class="img-fluid"
                      />
                    </div>
                  </div>
                </div>
                <div class="tab-pane" id="tab-5">
                  <div class="row">
                    <div class="col-lg-8 details order-2 order-lg-1">
                      <h3>
                        Est eveniet ipsam sindera pad rone matrelat sando reda
                      </h3>
                      <p class="fst-italic">
                        Omnis blanditiis saepe eos autem qui sunt debitis porro
                        quia.
                      </p>
                      <p>
                        Exercitationem nostrum omnis. Ut reiciendis repudiandae
                        minus. Omnis recusandae ut non quam ut quod eius qui.
                        Ipsum quia odit vero atque qui quibusdam amet. Occaecati
                        sed est sint aut vitae molestiae voluptate vel
                      </p>
                    </div>
                    <div class="col-lg-4 text-center order-1 order-lg-2">
                      <img
                        src="/assets/img/course-details-tab-5.png"
                        alt=""
                        class="img-fluid"
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- End Cource Details Tabs Section -->
    </main>
    <!-- End #main -->

    <!-- ======= Footer ======= -->
    <footer id="footer">
      <div class="footer-top">
        <div class="container">
          <div class="row">
            <div class="col-lg-3 col-md-6 footer-contact">
              <h3>Togather</h3>
              <p>
                서울시 금천구 <br />
                가산 디지털 2로 123<br />
                월드메르디앙 2차 <br /><br />
                <strong>Phone:</strong> +82 2 1234 1234<br />
                <strong>Email:</strong> service@togather.com<br />
              </p>
            </div>

            <div class="col-lg-2 col-md-6 footer-links">
              <h4>Useful Links</h4>
              <ul>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="../">Home</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="about.html">About us</a>
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
                  <i class="bx bx-chevron-right"></i>
                  <a href="notice.html">공지사항</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="FAQ.html">자주 묻는 질문</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="QA.html">Q & A</a>
                </li>
                <li>
                  <i class="bx bx-chevron-right"></i>
                  <a href="contact.html">Contact</a>
                </li>
              </ul>
            </div>

            <div class="col-lg-4 col-md-6 footer-newsletter">
              <h4>뉴스레터 구독하기</h4>
              <p>
                최신뉴스 및 프로모션 행사에 대한 안내메일을 받으실 수 있습니다.
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
            &copy; Copyright <strong><span>Togather</span></strong
            >. All Rights Reserved
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

    <div id="preloader"></div>
    <a
      href="#"
      class="back-to-top d-flex align-items-center justify-content-center"
      ><i class="bi bi-arrow-up-short"></i
    ></a>

    <!-- Vendor JS Files -->
    <script src="/assets/vendor/purecounter/purecounter.js"></script>
    <script src="/assets/vendor/aos/aos.js"></script>
    <script src="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/assets/vendor/swiper/swiper-bundle.min.js"></script>
    <script src="/assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="/assets/js/main.js"></script>
  </body>
</html>
