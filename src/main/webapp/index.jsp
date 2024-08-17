<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Basic Form</title>
</head>
<body>
    <h1>Contact Us</h1>
    
    <form action="submit" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br><br>

        <label for="message">Message:</label>
        <textarea id="message" name="message" rows="4" cols="50" required></textarea>
        <br><br>

        <button type="submit">Send</button>
    </form>
    
    <h1>Delete Form</h1>
    
    <form action="delete" method="post">
    <label for="email">Enter Email to Delete:</label>
    <input type="email" id="email" name="email" required>
    <button type="submit">Delete</button>
</form>

  <h2>Update User Information</h2>

    <form action="update" method="post">
        <p>Select the parameter you want to update:</p>

        <label>
            <input type="radio" name="updateParameter" value="name" required>
            Name
        </label><br>

        <label>
            <input type="radio" name="updateParameter" value="message" required>
            Message
        </label><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="newValue">Enter the new value:</label>
        <input type="text" id="updatedValue" name="updatedValue" required><br><br>

        <button type="submit">Update</button>
    </form>

</body>
</html>
