<%@taglib prefix="z" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/team.css" rel="stylesheet" type="text/css">


<z:layout pageTitle="Team">


<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="<c:url value="/resources/css/team.css"/>" rel="stylesheet" type="text/css">


	<div class="container-dir">                   
                        <div class="heading-title">
                            <h3 class="text-uppercase">Our professionals </h3>
                            <p class="txt"> Leanelda Group has been working in the web development sector for months<br>
                            				 Befloral is the first project of the group.<br>Our team is made up of highly professional figures.
                            				 <br>Think positive, think java</p>
                        </div>
                        
                        
				<div class="col">
                        <div class="col-md-4 col-sm-4">
                            <div class="team-member">
                                <div class="team-img"> 
                                    <img src="${pageContext.request.contextPath}/resources/images/team/ilCapo.jpg" alt="team member" class="img-responsive img-team">
                                </div>
                                <div class="team-hover">
                                    <div class="desk">
                                        <h4>Hi There !</h4>
                                        <p>I love to introduce myself as a problem solver</p>
                                    </div>
                                    <div class="s-link">
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                        <a href="#"><i class="fa fa-google-plus"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="team-title">
                                <h5>Daniele Giaquinto</h5>
                                <span>founder & ceo</span>
                            </div>
                        </div>


                        <div class="col-md-4 col-sm-4">
                            <div class="team-member">
                                <div class="team-img">
                                    <img src="${pageContext.request.contextPath}/resources/images/team/monacoUSA.jpg" alt="team member" class="img-responsive img-team">
                                </div> 
                                <div class="team-hover">
                                    <div class="desk">
                                        <h4>Hello!</h4>
                                        <p>I love helping colleagues and Coffee'</p>
                                    </div>
                                    <div class="s-link">
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                        <a href="#"><i class="fa fa-google-plus"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="team-title">
                                <h5>Leonardo Monaco</h5>
                                <span>Marketing Manager & helper</span>
                            </div>
                        </div>
                        
                        
                        <div class="col-md-4 col-sm-4">
                            <div class="team-member">
                                <div class="team-img">
                                    <img src="${pageContext.request.contextPath}/resources/images/team/aniello.jpg" alt="team member" class="img-responsive img-team">
                                </div>
                                <div class="team-hover">
                                    <div class="desk">
                                        <h4>Error 404!</h4>
                                        <p>I was joking</p>
                                    </div>
                                    <div class="s-link">
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                        <a href="#"><i class="fa fa-google-plus"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="team-title">
                                <h5>Aniello Mugnano</h5>
                                <span>Senior Analyst Programmer & hard tester</span>
                            </div>
                        </div>
 
                        <div class="col-md-4 col-sm-4">
                            <div class="team-member">
                                <div class="team-img">
                                    <img src="${pageContext.request.contextPath}/resources/images/team/mazza.jpg" alt="team member" class="img-responsive img-team">
                                </div>
                                <div class="team-hover">
                                    <div class="desk">
                                        <h4>Hello!</h4>
                                        <p>I only made this page</p>
                                    </div>
                                    <div class="s-link">
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                        <a href="#"><i class="fa fa-google-plus"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="team-title">
                                <h5>Elpidio Mazza</h5>
                                <span>cleaning services & security manager</span>
                            </div>
                        </div> 
                        
                        
                        <div>
                        	<a class="btn btn-success mt-2" id="call"  >Call Us</a>
                        	<a class="btn btn-success mt-2" id="send" >Send email</a>
                        	<p class="toWrite" id="toWrite"></p>
                        </div>
                                                
				</div>
	</div> 
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
document.getElementById("send").addEventListener("mouseenter", function( event ) {
	  // highlight the mouseenter target
	  document.getElementById("toWrite").innerHTML = "exSnake.gmail.com!";

	  // reset the color after a short delay
	  setTimeout(function() {
		  document.getElementById("toWrite").innerHTML = "";
	  }, 5000);
	}, false);

document.getElementById("call").addEventListener("mouseenter", function( event ) {
	  // highlight the mouseenter target
	  document.getElementById("toWrite").innerHTML = "3663992534";

	  // reset the color after a short delay
	  setTimeout(function() {
		  document.getElementById("toWrite").innerHTML = "";
	  }, 5000);
	}, false);
</script>
	              
</z:layout>
 