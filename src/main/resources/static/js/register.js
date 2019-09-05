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

    $("#verify_password").blur(function() {
        var password  = $("#password").val();
        var verify_password = $("#verify_password").val();
        if (password != verify_password) {
            alert("两次密码输入不一致!");
        }
    });

    $("#email").blur(function() {
        var email = $("#email").val();
        if(!legalEmail(email)) {
            alert("邮箱格式不正确!")
        }
    });

    $("#phone").blur(function() {
        var phone = $("#phone").val();
        if (!legalPhone(phone)) {
            alert("手机号格式不正确!");
        }
    });

    /**
     * 点击注册触发事件
     */
    $("#register_btn").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        var verify_password = $("#verify_password").val();
        var email = $("#email").val();
        var phone = $("#phone").val();
        if (!(username.length > 5 && username.length < 17)) {
            $("#username").focus();
        } else if (!(password.length > 5 && password.length < 17)) {
            $("#password").focus();
        } else if (password != verify_password) {
            $("#verify_password").focus();
        } else if (!legalEmail(email)) {
            $("#email").focus();
        } else if (!legalPhone(phone)) {
            $("#phone").focus();
        } else {
            $.ajax({
                type:"post",
                url:"/user/register",
                data:$("#register_form").serialize(),
                dataType: "json",
                success:function(data) {
                    if (data.status == 200) {
                        window.location.href="/success/0";
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

// 验证邮箱是否合法
function legalEmail(email) {
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    return reg.test(email);
}

// 验证手机号是否合法
function legalPhone(phone) {
    var reg = /(1[3-9]\d{9}$)/;
    return reg.test(phone);
}