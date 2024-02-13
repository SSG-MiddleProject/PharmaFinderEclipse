<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tile Layout</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div>
    <table>
        <tr>
            <td  style="border-right: solid 1px #e5e5e5">
                <aside>
                    <tiles:insertAttribute name="header"/>
                </aside>
            </td>
            <td>
                <tiles:insertAttribute name="content"/>
            </td>
        </tr>
    </table>

</div>
</body>
</html>
