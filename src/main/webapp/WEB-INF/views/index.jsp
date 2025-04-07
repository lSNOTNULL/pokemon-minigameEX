
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>나만의 포켓몬 만들기</title>
</head>
<body>
    <p>나에게 딱 맞는 포켓몬 만들기</p>
    <nav>
        <a href="sign-up">회원가입</a>
    </nav>
    <%
        if(session.getAttribute("pokeUserID") != null){
            %>
    <p>
        <h3> 로그인 됨 </h3>
        <%= request.getAttribute("pokeUserID") %>
    </p>
    <%
        } else {
            %>
    <h3> 로그인 하세요 </h3>
    <section>
    <form method="post">
        <label>
            닉네임 : <input type="text" name="username">
        </label>
        <label>
            비밀번호 :  <input type="password" name="password">
        </label>
        <button>로그인</button>
    </form>
</section>
    <%
        }
    %>
</body>
</html>
