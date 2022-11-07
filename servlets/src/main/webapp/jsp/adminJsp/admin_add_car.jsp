<html>
    <head>
        <title>Edit car</title>
    </head>
    <body>
        <h1>Edit car</h1>


        <form action="/car/add.do" method="post">

            <label for="brand">Brand: </label><br>
            <input type="text" id="brand" name="brand" required><br>

            <label for="fullName">Full name: </label><br>
            <input type="text" id="fullName" name="fullName" required><br>

            <label for="typeBody">Type body: </label><br>
            <input type="text" id="typeBody" name="typeBody" required><br>

            <label for="classAuto">Class auto: </label><br>
            <input type="text" id="classAuto" name="classAuto" required><br>

            <label for="color">Color: </label><br>
            <input type="text" id="color" name="color" required><br>

            <label for="engineDescription">Engine description: </label><br>
            <input type="text" id="engineDescription" name="engineDescription" required><br>

            <label for="price">Price: </label><br>
            <input type="text" id="price" name="price" required><br>

            <label for="pathToImage">Path to image: </label><br>
            <input type="text" id="pathToImage" name="pathToImage" required><br>

            <br>
            <input type="submit">
        </form>
    </body>

</html>