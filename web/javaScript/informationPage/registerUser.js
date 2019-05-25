/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function  registerDta()
{
    alert("hello");
    var first_name = document.registerPage.first_name.value;
    var last_name = document.registerPage.last_name.value;
    var work = document.registerPage.work.value;
    var email = document.registerPage.email.value;
    var Area_Code = document.registerPage.Area_Code.value;
    var phone = document.registerPage.phone.value;
    var subject = document.registerPage.subject.value;
    $.ajax({
        type: 'POST',
        url: "DemoServlat",
        data: {
            "first_name": first_name,
            "last_name": last_name,
            "work":work,
            "email":email,
            "Area_Code":Area_Code,
            "phone":phone,
            "subject":subject
            
        },
        success: function (data)
        {
            alert(data);
//            $("#success_message").append('<div>' + data);
        }
    });
}


