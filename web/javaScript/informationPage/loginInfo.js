/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function  loginDta()
 {
    alert("hello");
    var email = document.login_info.email.value;
    var pass = document.login_info.pass.value;
    $.ajax({
        type: 'POST',
        url: "DemoServlat",
        data: {
            "email": email,
            "pass": pass
        },
        success: function (data)
        {
            alert(data);
//            $("#success_message").append('<div>' + data);
        }
    });
}
