<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jakarta.servlet.http.HttpSession" %>

<%@ page import="User.User" %>
<%
// Récupérer l'objet user depuis la session
User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">

  <head>
 <script>
function toggleText(targetId, buttonToShow) {
    var shortText = document.getElementById('shortText');
    var fullText = document.getElementById('fullText');
    var readMoreButton = document.getElementsByClassName('read-more')[0];
    var showLessButton = document.getElementsByClassName('show-less')[0];

    if (targetId === 'fullText') {
        shortText.style.display = 'none';
        fullText.style.display = 'block';
        readMoreButton.style.display = 'none';
        showLessButton.style.display = 'inline'; // Afficher le bouton "Show Less"
    } else {
        shortText.style.display = 'block';
        fullText.style.display = 'none';
        readMoreButton.style.display = 'inline'; // Afficher le bouton "Read More"
        showLessButton.style.display = 'none';
    }
}

</script>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:100,200,300,400,500,600,700,800,900" rel="stylesheet">

    <title>healthCare</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-eduwell-style.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/lightbox.css">
<!--

TemplateMo 573 EduWell

https://templatemo.com/tm-573-eduwell

-->
  </head>

<body>


  <!-- ***** Header Area Start ***** -->
  <header class="header-area header-sticky">
      <div class="container">
          <div class="row">
              <div class="col-12">
                  <nav class="main-nav">
                      <!-- ***** Logo Start ***** -->
                      <a href="home.jsp" class="logo">
                          <img src="assets/images/logo1.png" style="width: 20%; height: auto;" alt="EduWell Template">
                           <span style="font-size: 14px;">HealthCare</span> 
                      </a>
             
                      
                      <!-- ***** Logo End ***** -->
                      <!-- ***** Menu Start ***** -->
                      <ul class="nav">
                       
                          <li class="scroll-to-section"><a href="#top" class="active">Home</a></li>
                          <li class="scroll-to-section"><a href="#services">Services</a></li>
                          <li class="scroll-to-section"><a href="#testimonials">About Endometriosis</a></li>
                          
                             <% if (user == null) { %> 
                             <li class="has-sub"><a href="javascript:void(0)">Wanna Know How?</a>
                              <ul class="sub-menu">
                                  <li><a href="Login.jsp"> Log In</a></li>
                                  <li><a href="Signup.jsp">Sign up</a></li>
                                 
                              </ul>
                              </li>
                              <% } else { %>
                              <li >
                              <a href="LogoutServlet">Log Out</a>
                              </li>
                              <% } %>
                          
                          <li class="scroll-to-section"><a href="#simple-cta">Informations</a></li> 
                          <li ><a href="ViewBlogsServlet">Our Blogs</a></li> 
                      </ul>        
                      <a class='menu-trigger'>
                          <span>Menu</span>
                      </a>
                      <!-- ***** Menu End ***** -->
                  </nav>
              </div>
          </div>
      </div>
  </header>
  <!-- ***** Header Area End ***** -->

  <!-- ***** Main Banner Area Start ***** -->
  <section class="main-banner" id="top">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 align-self-center">
          <div class="header-text">
          <% if (user == null) { %> 
           <h6>Welcome to our web application</h6>
           <h2>Where Women Can Combate  <em>The Endometriosis !</em></h2>
            <div class="main-button-gradient">
          <a href="Signup.jsp">Join Us Now!</a>
          
            </div>
           <% } else { %>
            <h6>Welcome to our web application <%= ((User) session.getAttribute("user")).getusername() %>!</h6>
           <h2>Where Women Can Combate  <em>The Endometriosis !</em></h2>
             <div class="main-button-gradient">
          <a href="ProcessSuiviFormServlet">Voir mes suivis </a>
            </div>
            <% } %>
            
           
          </div>
        </div>
        <div class="col-lg-6">
          <div class="right-image">
            <img src="assets/images/banner-right-image.png" alt="">
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- ***** Main Banner Area End ***** -->
 <!-- 
  <section class="services" id="services">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="section-heading">
          
            <h6>Our Services</h6>
            <h4>Provided <em>Services</em></h4>
          </div>
        </div>
        <div class="col-lg-12">
          <div class="owl-service-item owl-carousel">
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-01.png" alt="">
                </div>
                <h4>Useful Tricks</h4>
                <p>EduWell is the professional HTML5 template for your school or university websites.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-02.png" alt="">
                </div>
                <h4>Creative Ideas</h4>
                <p>You can download and use this EduWell Template for your teaching and learning stuffs.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-03.png" alt="">
                </div>
                <h4>Ready Target</h4>
                <p>Please tell your friends about the best CSS template website that is TemplateMo.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-04.png" alt="">
                </div>
                <h4>Technology</h4>
                <p>Aenean bibendum consectetur ex eu porttitor. Pellentesque id ultrices metus.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-01.png" alt="">
                </div>
                <h4>Useful Tricks</h4>
                <p>In non nisi eget magna efficitur ultricies non quis sapien. Pellentesque tellus.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-02.png" alt="">
                </div>
                <h4>Creative Ideas</h4>
                <p>Aenean bibendum consectetur ex eu porttitor. Pellentesque id ultrices metus.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-03.png" alt="">
                </div>
                <h4>Ready Target</h4>
                <p>In non nisi eget magna efficitur ultricies non quis sapien. Pellentesque tellus.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-04.png" alt="">
                </div>
                <h4>Technology</h4>
                <p>Aenean bibendum consectetur ex eu porttitor. Pellentesque id ultrices metus.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-01.png" alt="">
                </div>
                <h4>Useful Tricks</h4>
                <p>In non nisi eget magna efficitur ultricies non quis sapien. Pellentesque tellus.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-02.png" alt="">
                </div>
                <h4>Creative Ideas</h4>
                <p>Aenean bibendum consectetur ex eu porttitor. Pellentesque id ultrices metus.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-03.png" alt="">
                </div>
                <h4>Ready Target</h4>
                <p>In non nisi eget magna efficitur ultricies non quis sapien. Pellentesque tellus.</p>
              </div>
            </div>
            <div class="item">
              <div class="service-item">
                <div class="icon">
                  <img src="assets/images/service-icon-04.png" alt="">
                </div>
                <h4>Technology</h4>
                <p>Praesent accumsan condimentum arcu, id porttitor est semper nec. Nunc diam lorem.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
-->
  <section class="our-courses" id="courses">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 offset-lg-3">
          <div class="section-heading">
            <h6>NOS PROPOSITIONS</h6>
            <h4>Vous pouvez recevoir un guide par le remplissage d'un <em>Questionnaire de diagostique</em></h4>
            <% if (user != null) { %>
         
         <div class="main-button-gradient">
          <a href="diagnosticForm.jsp">Formulaire de diagnostique</a>
            </div>
           <br>
             <div class="main-button-gradient">
          <a href="suivi.jsp">Formulaire de Suivi </a>
            </div>
            <%} %>
          </div>
        </div>
       
        <div class="col-lg-12">
          <div class="naccs">
            <div class="tabs">
              <div class="row">
                <div class="col-lg-3">
                  <div class="menu">
                    <div class="active gradient-border"><span>Sensibilisation et Éducation</span></div>
                    <div class="gradient-border"><span>Suivi des Symptômes</span></div>
                    <div class="gradient-border"><span>Gestion du Traitement</span></div>
                    <div class="gradient-border"><span>Soutien Communautaire</span></div>
                    
                  </div>
                </div>
                <div class="col-lg-9">
                  <ul class="nacc">
                    <li class="active">
                      <div>
                        <div class="left-image">
                          <img src="assets/images/endo.jpg" alt="">
                          
                        </div>
                        <div class="right-content">
                          <h4>Sensibilisation et Éducation</h4>
                          <p>Une application peut fournir des informations détaillées sur l'endométriose, sensibilisant ainsi les utilisateurs à la maladie. Elle peut également offrir des ressources éducatives pour aider les utilisateurs à comprendre les symptômes, les options de traitement et les conseils pour gérer la maladie.
                         <p>Les applications peuvent permettre aux utilisateurs de suivre leurs symptômes quotidiens, ce qui peut aider les professionnels de la santé à mieux comprendre l'évolution de la maladie. Cela peut également aider les patients à identifier des schémas ou des déclencheurs potentiels.</p>
                          
                          <br><br></p>
                         
                        </div>
                      </div>
                    </li>
                    <li>
                      <div>
                        <div class="left-image" >
                          <img src="assets/images/endo.jpg" alt="">
                          
                        </div>
                        <div class="right-content">
                          <h4>Suivi des Symptômes</h4>
                          <p>Les applications peuvent permettre aux utilisateurs de suivre leurs symptômes quotidiens, ce qui peut aider les professionnels de la santé à mieux comprendre l'évolution de la maladie. Cela peut également aider les patients à identifier des schémas ou des déclencheurs potentiels.</p>
                          <p>Les applications peuvent permettre aux utilisateurs de suivre leurs symptômes quotidiens, ce qui peut aider les professionnels de la santé à mieux comprendre l'évolution de la maladie. Cela peut également aider les patients à identifier des schémas ou des déclencheurs potentiels.</p>
                          
                        </div>
                      </div>
                    </li>
                    <li>
                      <div>
                        <div class="left-image">
                          <img src="assets/images/endo.jpg" alt="">
                          
                        </div>
                        <div class="right-content">
                          <h4>Gestion du Traitement</h4>
                          <p>L'application peut inclure des fonctionnalités pour suivre les traitements prescrits, les médicaments et les rendez-vous médicaux. Cela peut aider à assurer une gestion plus efficace du traitement et à favoriser la collaboration entre les patients et les professionnels de la santé.</p>
                          <p>Les applications peuvent permettre aux utilisateurs de suivre leurs symptômes quotidiens, ce qui peut aider les professionnels de la santé à mieux comprendre l'évolution de la maladie. Cela peut également aider les patients à identifier des schémas ou des déclencheurs potentiels.</p>
                          
                        </div>
                      </div>
                    </li>
                    <li>
                      <div>
                        <div class="left-image">
                          <img src="assets/images/endo.jpg" alt="">
                          
                        </div>
                        <div class="right-content">
                          <h4>Soutien Communautaire</h4>
                         <p>Les applications peuvent permettre aux utilisateurs de suivre leurs symptômes quotidiens, ce qui peut aider les professionnels de la santé à mieux comprendre l'évolution de la maladie. Cela peut également aider les patients à identifier des schémas ou des déclencheurs potentiels.</p>
                          <p>Les applications peuvent permettre aux utilisateurs de suivre leurs symptômes quotidiens, ce qui peut aider les professionnels de la santé à mieux comprendre l'évolution de la maladie. Cela peut également aider les patients à identifier des schémas ou des déclencheurs potentiels.</p>
                          
                        </div>
                      </div>
                      </li>
                    </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <section class="simple-cta" id="simple-cta">
    <div class="container">
      <div class="row">
        <div class="col-lg-5 offset-lg-1">
          <div class="left-image">
            <img src="assets/images/fartello.jpg" alt="">
          </div>
        </div>
        <div class="col-lg-5 align-self-center">
          <h6>Qu'offrons-nous!</h6>
          
          <p>Notre application s'engage à prendre soin de votre santé en offrant une solution complète pour le suivi et la gestion de l'endométriose, une maladie qui peut avoir des conséquences sérieuses sur le bien-être des individus. Compte tenu du caractère potentiellement dangereux de l'endométriose, il est essentiel de bénéficier d'un suivi régulier et adapté pour favoriser la guérison. Notre application vous offre un accompagnement personnalisé, vous permettant de suivre facilement l'évolution de votre santé, de gérer les symptômes et de collaborer étroitement avec les professionnels de la santé. Nous sommes déterminés à contribuer à votre bien-être et à vous offrir les outils nécessaires pour prendre en charge votre santé gynécologique de manière proactive.</p>
          <% if (user != null) { %>
          <h6>Joindre notre communauté!</h6>
         <div class="white-button">
            <a href="publish_blog.jsp">Publier Blog</a>
          </div>
          <br>
          <div class="white-button">
            <a href="ViewBlogsServlet">Show Blogs</a>
          </div>
           <br>
          <div class="white-button">
            <a href="UserBlogsServlet">Vos Blogs</a>
          </div>
            <%} %>
          
        </div>
      </div>
    </div>
  </section>

  <section class="testimonials" id="testimonials">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="section-heading">
            <h6>Endometrios Awarness</h6>
            <h4>What You Should <em>Know</em></h4>
          </div>
        </div>
        <div class="col-lg-12">
          <div class="owl-testimonials owl-carousel" style="position: relative; z-index: 5;">
            <div class="item">
            <span>Qu'est-ce que l'endométriose ?</span>
                
              <p>“L'endometriose est une affection dans laquelle des tissus similaires à la muqueuse de l'utérus sont également présents ailleurs dans le corps, principalement dans la cavité abdominale.”</p>
            </div>
            <div class="item">
    <span>Quels facteurs peuvent augmenter mes chances de souffrir d'endométriose ?</span>
    <p id="shortText">
        “Vous avez plus de chances de développer une endométriose si vous avez:
        <br>
            - Jamais eu d'enfants
            
    </p>
    <p id="fullText" style="display: none;">
     <br>
        - Des périodes menstruelles qui durent plus de sept jours
         <br>
            - Des cycles menstruels courts (27 jours ou moins).
             <br>
            - Un membre de la famille (mère, tante, sœur) atteint d'endométriose
             <br>
            - Un problème de santé qui empêche l'écoulement normal du flux menstruel
             <br>
            - Des dommages aux cellules du pelvis causés par une infection”
    </p>
    <span class="read-more"  onclick="toggleText('fullText', 'show-less')">Read More</span>
     <span class="show-less" onclick="toggleText('shortText', 'read-more')">Show Less</span>
    
</div>



            <div class="item">
             <span>Quels sont les symptômes de l'endométriose ?</span>
              <p id="shortText">
        <b>Douleurs pelviennes : </b>La douleur pelvienne est l'un des symptômes les plus fréquents. Elle peut être intense et se manifester avant, pendant ou après les règles.
        <br>
           
            
    </p>
    <p id="fullText" style="display: none;">
     <br>
        

<b>Douleurs menstruelles sévères :</b> Les femmes atteintes d'endométriose ont souvent des crampes menstruelles plus intenses que la moyenne.

<b>Douleurs pendant les rapports sexuels :</b> Les rapports sexuels peuvent être douloureux, en particulier pendant les menstruations.

<b>Douleurs lombaires et abdominales : </b>Des douleurs dans le bas du dos et l'abdomen peuvent survenir.

<b>Troubles intestinaux et urinaires :</b> Certains cas d'endométriose peuvent provoquer des problèmes digestifs, tels que des douleurs pendant les selles, des diarrhées ou des constipations. Des symptômes urinaires, tels que des douleurs lors de la miction, peuvent également survenir.

<b>Fatigue :</b> Certaines femmes atteintes d'endométriose signalent une fatigue persistante.

<b>Saignements menstruels abondants ou irréguliers :</b> Des saignements menstruels plus abondants que la normale ou des cycles menstruels irréguliers peuvent être observés.
    </p>
    
          </div>
        </div>
      </div>
    </div>
  </section>
 <!-- 
  <section class="contact-us" id="contact-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-8">
          <div id="map">
          
            <!-- You just need to go to Google Maps for your own map point, and copy the embed code from Share -> Embed a map section -->
   <!--           <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7151.84524236698!2d-122.19494600413192!3d47.56605883252286!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x5490695e625f8965%3A0xf99b055e76477def!2sNewcastle%20Beach%20Park%20Playground%2C%20Bellevue%2C%20WA%2098006%2C%20USA!5e0!3m2!1sen!2sth!4v1644335269264!5m2!1sen!2sth" width="100%" height="420px" frameborder="0" style="border:0; border-radius: 15px; position: relative; z-index: 2;" allowfullscreen=""></iframe>
            <div class="row">
              <div class="col-lg-4 offset-lg-1">
                <div class="contact-info">
                  <div class="icon">
                    <i class="fa fa-phone"></i>
                  </div>
                  <h4>Phone</h4>
                  <span>010-020-0340</span>
                </div>
              </div>
              <div class="col-lg-4">
                <div class="contact-info">
                  <div class="icon">
                    <i class="fa fa-phone"></i>
                  </div>
                  <h4>Mobile</h4>
                  <span>090-080-0760</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <form id="contact" action="" method="post">
            <div class="row">
              <div class="col-lg-12">
                <div class="section-heading">
                  <h6>Contact us</h6>
                  <h4>Say <em>Hello</em></h4>
                  <p>IF you need a working contact form by PHP script, please visit TemplateMo's contact page for more info.</p>
                </div>
              </div>
              <div class="col-lg-12">
                <fieldset>
                  <input type="name" name="name" id="name" placeholder="Full Name" autocomplete="on" required>
                </fieldset>
              </div>
              <div class="col-lg-12">
                <fieldset>
                  <input type="text" name="email" id="email" pattern="[^ @]*@[^ @]*" placeholder="Your Email" required="">
                </fieldset>
              </div>
              <div class="col-lg-12">
                <fieldset>
                  <textarea name="message" id="message" placeholder="Your Message"></textarea>
                </fieldset>
              </div>
              <div class="col-lg-12">
                <fieldset>
                  <button type="submit" id="form-submit" class="main-gradient-button">Send Message</button>
                </fieldset>
              </div>
            </div>
          </form>
        </div>
        <div class="col-lg-12">
          <ul class="social-icons">
            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
            <li><a href="#"><i class="fa fa-rss"></i></a></li>
            <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
          </ul>
        </div>
        <div class="col-lg-12">
          <p class="copyright">Copyright © 2022 EduWell Co., Ltd. All Rights Reserved. 
          
          <br>Design: <a rel="sponsored" href="https://templatemo.com" target="_blank">TemplateMo</a>
          <br>Distribution: <a rel="sponsored" href="https://themewagon.com" target="_blank">ThemeWagon</a></p>
        </div>
      </div>
    </div>
  </section>
-->
  <!-- Scripts -->
  <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="assets/js/isotope.min.js"></script>
    <script src="assets/js/owl-carousel.js"></script>
    <script src="assets/js/lightbox.js"></script>
    <script src="assets/js/tabs.js"></script>
    <script src="assets/js/video.js"></script>
    <script src="assets/js/slick-slider.js"></script>
    <script src="assets/js/custom.js"></script>
    <script>
        //according to loftblog tut
        $('.nav li:first').addClass('active');

        var showSection = function showSection(section, isAnimate) {
          var
          direction = section.replace(/#/, ''),
          reqSection = $('.section').filter('[data-section="' + direction + '"]'),
          reqSectionPos = reqSection.offset().top - 0;

          if (isAnimate) {
            $('body, html').animate({
              scrollTop: reqSectionPos },
            800);
          } else {
            $('body, html').scrollTop(reqSectionPos);
          }

        };

        var checkSection = function checkSection() {
          $('.section').each(function () {
            var
            $this = $(this),
            topEdge = $this.offset().top - 80,
            bottomEdge = topEdge + $this.height(),
            wScroll = $(window).scrollTop();
            if (topEdge < wScroll && bottomEdge > wScroll) {
              var
              currentId = $this.data('section'),
              reqLink = $('a').filter('[href*=\\#' + currentId + ']');
              reqLink.closest('li').addClass('active').
              siblings().removeClass('active');
            }
          });
        };

        $('.main-menu, .responsive-menu, .scroll-to-section').on('click', 'a', function (e) {
          e.preventDefault();
          showSection($(this).attr('href'), true);
        });

        $(window).scroll(function () {
          checkSection();
        });
    </script>
</body>

</html>