<%@ page import="com.example.GroupPublic" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home Page</title>
</head>
<body>

<div id="content" role="main">
    <section class="row colset-2-its">



<h1><asset:image src="apple-touch-icon.png" /></h1>
        <h1>Bienvenue dans le KabaBoard !</h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <ul>
            <g:each in="${GroupPublic.list()}" var="groupPublic">
                <li>
                    <g:link controller="groupPublic" action="show" id="${groupPublic.id}">
                        ${groupPublic.name} / ${groupPublic.notes.size() }  notes
                    </g:link>
                </li>
            </g:each>
        </ul>



    </section>
</div>



<asset:javascript src="jquery-2.2.0.min.js" />

<script type="text/javascript">
    $( document ).ready(function() {
        console.log( "jQuery 3.1.1 loaded!" );
    });
</script>




</body>
</html>