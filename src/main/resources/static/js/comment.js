var btn = document.querySelector(".btn"); //获取元素
var eventTouch=document.getElementById("writer");
var mask = document.querySelector(".mask");
var login = document.querySelector(".login");

eventTouch.click = function () {    //绑定点击事件
    var event = event || window.event;      //兼容问题
    stopBuble(event);       //调用阻止事件冒泡函数
    mask.style.display = "block";       //JS动态让元素显示
    login.style.display = "block";

    document.onclick = function () {
        var event = event || window.event;
        var target = event.target || event.srcElement;    //事件委托兼容写法
        if (target.className == "login") {      //事件委托
            location.href = "https://blog.csdn.net/Leejinian888"
        } else {
            mask.style.display = "none";
            login.style.display = "none";
        }
    }
}

function stopBuble(e) {     //阻止事件冒泡函数
    if (e.stopPropagation) {
        e.stopPropagation();
    } else {
        e.cancelBubble = true;
    }
}
