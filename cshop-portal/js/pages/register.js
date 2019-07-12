$(function() {
    $("#register").on("click",function(){
        var phone = $("#phone").val();
        var email = $("#email").val();
        var username = $("#username").val();
        var password = $("#password").val();
        var passwordConfirm = $("#passwordConfirm").val();

        var code = $("#code").val();

        if(username == null || username == ""){
            alert("用户名不能为空");
            return false;
        }


        if(password == null || password == ""){
            alert("密码不能为空");
            return false;
        }
        if(passwordConfirm == null || passwordConfirm == ""){
            alert("确认密码不能为空");
            return false;
        }

        if(phone == null || phone == ""){
            alert("手机号不能为空");
            return false;
        }

        if(email == null || email == ""){
            alert("邮箱不能为空");
            return false;
        }
        if(code == null || code == ""){
            alert("验证码不能为空");
            return false;
        }
        $.ajax({
            type:"POST",
            url:"http://"+g_host+"/account/register",
            data:{
                "phone": phone,
                "password":password,
                "email":email,
                "passwordConfirm":passwordConfirm,
                "username":username,
                "code": code,
            },
            xhrFields:{withCredentials:true},
            success:function(data){
                if(data.status==true){
                    alert("注册成功");
                    window.location.href="./login.html";
                }else{
                    alert("注册失败，原因为"+JSON.stringify(data.data));
                }
            },
            error:function(data){
                alert("注册失败，原因为"+data.responseText);
            }
        });
        return false;
    });

    $("#createVerifyCode").on("click",function(){
        var phone = $("#phone").val();

        if(phone == null || phone == ""){
            alert("手机号不能为空");
            return false;
        }

        $.get("http://"+g_host+"/account/code?phone="+phone, function(data){
            if(data.status==true){
                alert("验证码已发送到手机");
            }else{
                alert("验证码获取失败，原因为"+JSON.stringify(data.data));
            }
        });
    });
});
