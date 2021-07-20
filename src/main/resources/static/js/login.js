$(function($) {

    $("#login").click(function (){

        var loginDTO = {
            "username": $("#username").val(),
            "password": $("#password").val(),
        }

        $.ajax({
            method: "POST",
            dataType: "json",
            data: "json",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(loginDTO),
            url: "http://localhost:8989/login",
            success: function (data){
                alert(data.message)
                if(data.code == 200){
                    location.href="index.html"
                }
            }
        })
    })


});