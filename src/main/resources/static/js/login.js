$(function() {
    $("#username").blur(function() {
        var username = $("#username").val();
        if (!(username.length > 5 && username.length < 17)) {
            alert("请输入6-16位用户名!");
        }
    });
    $("#password").blur(function() {
        var password  = $("#password").val();
        if (!(password.length > 5 && password.length < 17)) {
            alert("请输入6-16位密码!");
        }
    });
    $("#login_btn").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        if (!(username.length > 5 && username.length < 17)) {
            $("#username").focus();
        } else if (!(password.length > 5 && password.length < 17)) {
            $("#password").focus();
        } else {
            $.ajax({
                type:"post",
                url:"/user/login",
                data:$("#login_form").serialize(),
                dataType: "json",
                success:function(data) {
                    if (data.status == 200) {
                        window.location.href="/";
                    } else {
                        if (data.desc != null) {
                            alert(data.desc);
                        }
                    }
                }
            });
        }
    });
});