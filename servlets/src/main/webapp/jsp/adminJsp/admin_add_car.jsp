<html>
    <head>
        <title>Edit car</title>
    </head>
    <body>
        <h1>Add car</h1>

        <form action="/car/add.do" method="post">

            <label for="nameCar">Name Car: </label><br>
            <input type="text" id="nameCar" name="nameCar" required><br>

            <label for="typeOfBody">Type of body: </label><br>
            <input type="text" id="typeOfBody" name="typeOfBody" required><br>

            <label for="typeEngine">Type engine: </label><br>
            <input type="text" id="typeEngine" name="typeEngine" required><br>

            <label for="typeTransmission">Type transmission: </label><br>
            <input type="text" id="typeTransmission" name="typeTransmission" required><br>

            <label for="yearOfIssue">Year of issue: </label><br>
            <input type="text" id="yearOfIssue" name="yearOfIssue" required><br>

            <label for="price">Price: </label><br>
            <input type="text" id="price" name="price" required><br>

            <label for="image">image: </label><br>
            <input type="text" id="image" name="image" required><br>

            <br>
            <input type="submit">
        </form>
    </body>

</html>