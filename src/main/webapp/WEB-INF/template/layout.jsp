<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>

    <meta charset="UTF-8">
    <title>Tile Layout</title>

</head>
<body>
<div>

    <form name="template">
        <table>
            <tr colspan="2">
                <td>
                    <aside class="column is-2 is-narrow-mobile is-fullheight section is-hidden-mobile">


                        <tiles:insertAttribute name="header"/>
                    </aside>
                </td>
                <td>
                    <tiles:insertAttribute name="content"/>


                </td>
            </tr>
        </table>
    </form>

</div>
</body>
</html>
