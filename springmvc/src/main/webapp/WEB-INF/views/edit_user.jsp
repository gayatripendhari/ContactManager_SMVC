<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User</title>
<style>
    /* General Styles */
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 0;
    }

    /* Container Styles */
    .container {
        max-width: 500px;
        margin: 50px auto;
        background: #ffffff;
        padding: 20px 30px;
        border-radius: 8px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    /* Heading Styles */
    h1 {
        text-align: center;
        color: #333;
        font-size: 1.8em;
        margin-bottom: 20px;
        font-weight: bold;
    }

    /* Form Styles */
    form {
        display: flex;
        flex-direction: column;
    }

    label {
        margin-bottom: 5px;
        color: #555;
        font-weight: bold;
        font-size: 0.9em;
    }

    input {
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ddd;
        border-radius: 5px;
        font-size: 1em;
        transition: border-color 0.3s ease;
    }

    input:focus {
        border-color: #0078d7;
        outline: none;
        box-shadow: 0 0 5px rgba(0, 120, 215, 0.3);
    }

    button {
        background-color: #0078d7;
        color: white;
        font-size: 1em;
        padding: 10px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease, transform 0.3s ease;
    }

    button:hover {
        background-color: #005bb5;
        transform: scale(1.05);
    }

    /* Link Styles */
    a {
        display: block;
        text-align: center;
        margin-top: 15px;
        text-decoration: none;
        color: #0078d7;
        font-weight: bold;
        transition: color 0.3s ease;
    }

    a:hover {
        color: #005bb5;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Edit User</h1>
        <form action="./edit-user" method="post">
            <input type="hidden" name="id" value="${user.id}" />
            <label for="username">Name:</label>
            <input type="text" id="username" name="username" value="${user.name}" required />
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${user.email}" required />
            <label for="mobile">Mobile:</label>
            <input type="text" id="mobile" name="mobile" value="${user.mobile}" required />
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="${user.password}" required />
            <button type="submit">Update</button>
        </form>
        <a href="./home">Cancel</a>
    </div>
</body>
</html>
