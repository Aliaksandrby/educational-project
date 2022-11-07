<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Order</title>
    <style>
        table, th, td {
          border: 1px solid black;
          border-radius: 10px;
          text-align: center;
        }
    </style>
</head>
<body>
    <p align="center">Order a Car</p>

    <Table class="table" align="center">
        <tr>
            <th>Car</th>
            <th>Description</th>
            <th>Price (BYN)</th>
            <th>Make order</th>
        </tr>
        <tr>
            <td><img src=<c:out value="${carInfo.pathToImage}"/>></td>
            <td>
                brand: <c:out value="${carInfo.brand}"/><br>
                full name: <c:out value="${carInfo.fullName}"/><br>
                type body: <c:out value="${carInfo.typeBody}"/><br>
                class auto: <c:out value="${carInfo.classAuto}"/><br>
                color: <c:out value="${carInfo.color}"/><br>
                engine description: <c:out value="${carInfo.engineDescription}"/>
            </td>
            <td><c:out value="${carInfo.price}"/></td>
            <td><a href="">Order</a></td>
        </tr>
    </Table>

</body>
</html>
