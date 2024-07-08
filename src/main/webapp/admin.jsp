<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Admin Page</title>
</head>
<body>
    <h2>Admin Page</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="user : ${userList}">
                <td th:text="${user.id}"></td>
                <td th:text="${user.username}"></td>
                <td th:text="${user.role}"></td>
                <td>
                    <form action="editUser" method="get">
                        <input type="hidden" name="id" th:value="${user.id}">
                        <input type="submit" value="Edit">
                    </form>
                    <form action="deleteUser" method="post">
                        <input type="hidden" name="id" th:value="${user.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </tbody>
    </table>
    <h3>Add New User</h3>
    <form action="addUser" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <input type="submit" value="Add User">
    </form>
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
    <p th:text="${error}" th:if="${error} != null" style="color:red;"></p>
</body>
</html>
