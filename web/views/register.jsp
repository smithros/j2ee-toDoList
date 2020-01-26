<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>JavaEE Project</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">

    <h2>User Register Form</h2>
    <div class="col-md-6 col-md-offset-3">
        <div class="alert alert-success center" role="alert">
            <p>${NOTIFICATION}</p>
        </div>

        <form action="<%=request.getContextPath()%>/register" method="post">

            <div class="form-group">
                <label for="first_name">First Name:</label> <input type="text" class="form-control" id="first_name"
                                                                   placeholder="First Name" name="firstName" required>
            </div>

            <div class="form-group">
                <label for="last_name">Last Name:</label> <input type="text" class="form-control" id="last_name"
                                                                 placeholder="last Name" name="lastName" required>
            </div>

            <div class="form-group">
                <label for="user_name">Login:</label> <input type="text" class="form-control" id="user_name"
                                                             placeholder="User Name" name="username" required>
            </div>

            <div class="form-group">
                <label for="user_password">Password:</label> <input type="password" class="form-control"
                                                                    id="user_password" placeholder="Password"
                                                                    name="password" required>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
    </div>
</div>
</body>

</html>