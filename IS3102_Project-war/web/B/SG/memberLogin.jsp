<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@page import="net.tanesha.recaptcha.ReCaptchaImpl"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <script>
            function validatePassword() {
                var password = document.getElementById("password").value;
                var repassword = document.getElementById("repassword").value;
                var ok = true;
                if ((password != null && repassword != null) || (password != "" && repassword != "")) {
                    if (password != repassword) {
                        //alert("Passwords Do not match");
                        document.getElementById("password").style.borderColor = "#E34234";
                        document.getElementById("repassword").style.borderColor = "#E34234";
                        alert("Passwords do not match. Please key again.");
                        ok = false;
                    } else if (password == repassword) {
                        if (password.length < 8) {
                            alert("Passwords too short. At least 8 characters.");
                            ok = false;
                        }
                    }
                } else {
                    return ok;
                }
                return ok;
            }
              
        </script>
        <jsp:include page="menu2.jsp" />
        <div role="main" class="main">
            <section class="page-top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Login / Register</h2>
                        </div>
                    </div>
                </div>
            </section>
            <div class="container">
                <jsp:include page="/displayMessageLong.jsp" />
                <div class="row">
                    <div class="col-md-12">
                        <div class="row featured-boxes login">
                            <div class="col-md-6">
                                <div class="featured-box featured-box-secundary default info-content">
                                    <div class="box-content">
                                        <h4>I'm a Returning Customer</h4>

                                        <form action="../../ECommerce_MemberLoginServlet">
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <label>E-mail Address</label>
                                                        <input type="email" name="email" class="form-control input-lg" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <a class="pull-right" href="forgotPassword.jsp">(Lost Password?)</a>
                                                        <label>Password</label>
                                                        <input type="password" name="password" class="form-control input-lg" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input type="submit" onclick="validateForm()" value="Login" class="btn btn-primary pull-right push-bottom">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="featured-box featured-box-secundary default info-content">
                                    <div class="box-content">
                                        <h4>Register An Account</h4>
                                        <form action="../../ECommerce_MemberRegisterServlet" onsubmit="return validatePassword()">
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <label>E-mail Address</label>
                                                        <input type="email" name="email" class="form-control input-lg" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-6">
                                                        <label>Password</label>
                                                        <input id="password" type="password" value="" name="password" class="form-control input-lg" required>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label>Re-enter Password</label>
                                                        <input id="repassword" type="password" value="" name="repassword" class="form-control input-lg" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-3">
                                                        <%
                                                            ReCaptchaImpl recaptcha = new ReCaptchaImpl();
                                                            recaptcha.setIncludeNoscript(false);
                                                            recaptcha.setPrivateKey("6LdjyvoSAAAAAHnUl50AJU-edkUqFtPQi9gCqDai");
                                                            recaptcha.setPublicKey("6LdjyvoSAAAAAL2m-7sPPZEtz0BNVRb-A_yY0BB_");
                                                            recaptcha.setRecaptchaServer("https://www.google.com/recaptcha/api");
                                                            out.print(recaptcha.createRecaptchaHtml(null, null));
                                                        %>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <input type="submit" value="Register" class="btn btn-primary">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.html" />

    </body>
</html>
