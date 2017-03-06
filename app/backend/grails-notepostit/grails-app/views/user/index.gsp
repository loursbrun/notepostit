<%@ page import="com.example.User" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>User list</title>
</head>
<body>

<div id="content" role="main">
    <section class="row colset-2-its">



<h1><asset:image src="apple-touch-icon.png" /></h1>
        <h1>User list !</h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <ul>
            <g:each in="${User.list()}" var="user">
                <li>
                    <g:link controller="user" action="show" id="${user.id}">
                        ${user.name}
                    </g:link>
                </li>
            </g:each>
        </ul>

        <g:form action="updateName" style="margin: 0 auto; width:320px">
            <g:textField name="name" value="" />
            <g:submitButton name="Update name" />
        </g:form>
    </section>
</div>



<asset:javascript src="jquery-2.2.0.min.js" />


</body>
</html>