<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<% 
String userid =null;
 if(session.getAttribute("id") !=null){
	 userid = (String)session.getAttribute("id");
 }
 String seid =session.getId();

/*if(userid==null){
	userid = "GUEST";
}*/
%>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Full Width Pics - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Responsive navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href=""><%=userid %>접속중 </a>
                <a class="navbar-brand" href="">세션ID:<%=seid %> </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul id ="se" class="navbar-nav ms-auto mb-2 mb-lg-0">	
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>     
                        <li id="join"class="nav-item"><a class="nav-link" href="login/join.jsp">회원가입(JOIN)</a></li>
                        <li id="game"class="nav-item"><a class="nav-link" href="login/game.jsp">게임</a></li>
                        <li id="login"class="nav-item"><a class="nav-link" href="login/login.jsp">로그인</a></li>
                        <li id="list"class="nav-item"><a class="nav-link" href="list/list.do">글목록</a></li>
                        <li id="MM"class="nav-item"><a class="nav-link" href="login/login.do?m=MM&id=${id }">회원관리</a></li>
                        <li id="logout"class="nav-item"><a class="nav-link" href="login/sessionLogout.jsp">로그아웃</a></li>
                    </ul>
                </div>    
            </div>
        </nav>
      <script>
        document.getElementById('logout').style.display = 'none';
        document.getElementById('list').style.display = 'none';
      </script>
        <% 
        	boolean flag =false;
        	if(userid!=null) {
        		flag =true;
        	}
        	System.out.println(flag);
        %>
        <script>      
	
           	if(<%=flag%>){
           		document.getElementById('join').style.display = 'none';
           		document.getElementById('login').style.display = 'none';   
                document.getElementById('logout').style.display = '';
                document.getElementById('list').style.display = '';
           		
                /*document.getElementById('join').remove();
           		var a = document.createElement('a');
                a.setAttribute('href', 'list/list.do?m=list');
                a.appendChild(document.createTextNode('글목록'));
                var tar = document.getElementById('se');
                tar.replaceChild(a, se.firstChild); //tar의 첫번째child 를 a 로 replace*/
                
                //document.getElementById('login').style.visibility = "hidden";//사라지지만 그 자리를 차지하고있다.
                
                /*let li = document.createElement('a');   //li태그생성해서 로그아웃택스트노드를 li붙이고 원래 login아이디 가진애를 logout으로 바꾼다.
           	 	li.setAttribute('href', 'login/sessionLogout.jsp');
           		li.appendChild(document.createTextNode('로그아웃'));        		           		  		
           		let originalp = document.getElementById('login');
           		let oldp = originalp.parentNode.replaceChild(li,originalp);
           		oldp;*/
           		
           		
           	}
        
        </script>
        <!-- Header - set the background image for the header in the line below-->
        <header class="py-5 bg-image-full" style="background-image: url('https://source.unsplash.com/wfh8dDlNFOk/1600x900')">
            <div class="text-center my-5">
               <!--  <img class="img-fluid rounded-circle mb-4" src="C://Users//Kosmo_8//Desktop//Jisu//images.jpg" width="272" height="185" alt="..." /> 사진넣는칸 -->
                <h1 class="text-white fs-3 fw-bolder"> HOMEPAGE</h1>
                <p class="text-white-50 mb-0">Landing Page Template</p>
            </div>
        </header>
        <!-- Content section-->
        <section class="py-5">
            <div class="container my-5">
                <div class="row justify-content-center">
                    <div class="col-lg-6">
                        <h2>Full Width Backgrounds</h2>
                        <p class="lead">A single, lightweight helper class allows you to add engaging, full width background images to sections of your page.</p>
                        <p class="mb-0">The universe is almost 14 billion years old, and, wow! Life had no problem starting here on Earth! I think it would be inexcusably egocentric of us to suggest that we're alone in the universe.</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- Image element - set the background image for the header in the line below-->
        <div class="py-5 bg-image-full" style="background-image: url('https://source.unsplash.com/4ulffa6qoKA/1200x800')">
            <!-- Put anything you want here! The spacer below with inline CSS is just for demo purposes!-->
            <div style="height: 20rem"></div>
        </div>
        <!-- Content section-->
        <section class="py-5">
            <div class="container my-5">
                <div class="row justify-content-center">
                    <div class="col-lg-6">
                        <h2>Engaging Background Images</h2>
                        <p class="lead">The background images used in this template are sourced from Unsplash and are open source and free to use.</p>
                        <p class="mb-0">I can't tell you how many people say they were turned off from science because of a science teacher that completely sucked out all the inspiration and enthusiasm they had for the course.</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
