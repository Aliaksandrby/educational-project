<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Rent cars</title>
    <style>
        table, th, td {
              border: 1px solid black;
              border-radius: 10px;
              text-align: center;
        }
    </style>
</head>
<body>
    <p border="1" align="center">Rent a Car</p>
    <p><a href="">MAKE LOGOUT</a></p>

    <p border="1" align="center"><a href="/car/jsp/adminJsp/admin_add_car.jsp">add car</a></p>
    <Table class="table" align="center">
        <tr>
            <th>Car</th>
            <th>Description</th>
            <th>Price (BYN)</th>
            <th>Detailed</th>
        </tr>
        <c:forEach items="${carList}" var="car">
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
            <td>
                <a href="/car/edit.do?carId=${car.id}">edit car</a><br>
                <a href="/car/delete.do?carId=${car.id}">delete car</a>
            </td>
        </tr>
        </c:forEach>
    </Table>
</body>
</html>
