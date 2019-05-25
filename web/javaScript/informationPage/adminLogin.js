/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function  adminloginDta()
 {
    alert("hello");
    var email = document.admin_login.email.value;
    var pass = document.admin_login.pass.value;
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