$(function() {
    $('input,textarea').placeholder();

    $("#login").on("click",function(){
        var phone = $("#phone").val();
        var password = $("#password").val();
        if(phone == null || phone == ""){
            alert("手机号不能为空");
            return false;
        }
        if(password == null || password == ""){
            alert("密码不能为空");
            return false;
        }


        $.ajax({
            type:"POST",
            url:"http://"+g_host+"/auth",
            data:{
                "username":phone,
                "password":password
            },
            xhrFields:{withCredentials:true},
            success:function(data){
                if(data.status=="true"){
                    var token = data.data;
                    window.localStorage["token"] = token;
                    window.location.href="index.html";
                }else{
                    alert("登陆失败，原因为"+JSON.stringify(data.data));
                }
            },
            error:function(data){
                alert("登陆失败");
            }
        });
        return false;
    });
});
