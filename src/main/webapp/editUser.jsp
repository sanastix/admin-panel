<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
    <h2>Edit User</h2>
    <form action="editUser" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${user.username}" required><br>
        <label for="role">Role:</label>
        <select id="role" name="role">
            <option value="USER" <c:if test="${user.role == 'USER'}">selected</c:if>>User</option>
            <option value="ADMIN" <c:if test="${user.role == 'ADMIN'}">selected</c:if>>Admin</option>
        </select><br>
        <input type="submit" value="Save">
    </form>
    <form action="admin" method="get">
        <input type="submit" value="Cancel">
    </form>
    <c:if test="${param.error != null}">
        <p style="color:red;">${param.error}</p>
    </c:if>
</body>
</html>
