<%@ page import="com.example.Vehicle" %>
<html>
<head>
    <meta name="layout" content="public"/>
    <title>Home Page</title>
</head>
<body>

<div id="content" role="main">
    <section class="row colset-2-its">


<div id="show-vehicle" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
<h1>Estimated Value: <g:formatNumber number="${estimatedValue}" type="currency" currencyCode="USD" /></h1>

</div>

    </section>
</div>






</body>
</html>