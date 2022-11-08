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
            <td><img src=<c:out value="${car.image}"/>></td>
            <td>
                Name car: <c:out value="${car.nameCar}"/><br>
                Type of body: <c:out value="${car.typeOfBody}"/><br>
                Type engine: <c:out value="${car.typeEngine}"/><br>
                Type transmission: <c:out value="${car.typeTransmission}"/><br>
                Year of issue: <c:out value="${car.yearOfIssue}"/><br>
            </td>
            <td><c:out value="${car.price}"/></td>
            <td><a href="">Order</a></td>
        </tr>
    </Table>

</body>
</html>