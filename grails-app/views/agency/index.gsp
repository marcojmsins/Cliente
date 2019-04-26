<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'agency.label', default: 'Agency')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-agency" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>

    <div class="nav" role="navigation">
        <ul>


        </ul>
    </div>
    <button type="button" onclick="getAgency()">Show</button>
    <script >
        function getAgency(){
            var URL="${createLink(controller:'Agency',action:'showAgency')}";
            console.log(URL)
            $.ajax({
                url:URL,
                success:function (resp) {
                    console.log(resp.data)


                    Object.keys(resp.data).forEach(function (k) {
                        $("#tabla").append("<tr id=" +resp.data[k].agency_code+ ">" +
                            "<td>" + resp.data[k].agency_code + "</td>")


                    });
                }
            });
        }

    </script>
    <div id="div1" >
        <table id="tabla" style="border:solid">

        </table>
    </div>


    <div id="list-agency" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${agencyList}" />

            <div class="pagination">
                <g:paginate total="${agencyCount ?: 0}" />
            </div>
        </div>
    </body>
</html>