$(function($) {
    $.ajax({
        type: "GET",
        dataType: "text",
        url: "http://localhost:8989/getImage",
        success: function(data){
            $("#VerificationCode").attr("src", data);
        }
    });

    $("#VerificationCode").click(function (){
        $.ajax({
            type: "GET",
            dataType: "text",
            url: "http://localhost:8989/getImage",
            success: function(data){
                $("#VerificationCode").attr("src", data);
            }
        });
    })

    $("#register").click(function (){

        var registerDTO = {
            "username": $("#username").val(),
            "password": $("#password").val(),
            "repwd": $("#resetpw").val(),
            "code": $("#code").val()
        }

        $.ajax({
            method: "POST",
            dataType: "json",
            data: "json",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(registerDTO),
            url: "http://localhost:8989/register",
            success: function (data){
                alert(data.message)
            }
        })
    })


});
