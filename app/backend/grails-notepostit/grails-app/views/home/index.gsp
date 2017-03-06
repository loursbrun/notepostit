<%@ page import="com.example.Vehicle" %>
<html>
<head>
    <meta name="layout" content="public"/>
    <title>Home Page</title>
</head>
<body>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome ${name}!</h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <p>There are ${vehicleTotal} vehicles in the database.</p>

        <ul>
            <g:each in="${Vehicle.list()}" var="vehicle">
                <li>
                    <g:link controller="vehicle" action="show" id="${vehicle.id}">
                        ${vehicle.name} - ${vehicle.year} ${vehicle.make.name} ${vehicle.model.name}
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

<script type="text/javascript">
    $( document ).ready(function() {
        console.log( "jQuery 3.1.1 loaded!" );
    });
</script>

<asset:image src="apple-touch-icon.png" />


</body>
</html>