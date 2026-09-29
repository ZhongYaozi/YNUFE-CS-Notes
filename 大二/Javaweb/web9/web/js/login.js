function pd() {
    var uname = document.getElementById("uname").value;
    var upwd = document.getElementById("upwd").value;
    if (uname.trim() != "" && upwd.trim() != "") {
        document.getElementById("loginform").submit();
    } else {
        if (uname.trim() == "") {
            alert('用户名不能为空!');
            return;
        } else if (upwd.trim() == "") {
            alert('密码不能为空!');
            return;
        }
    }
}
