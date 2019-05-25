/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function  feedBackData()
{
    alert("hello");
    var experience = document.feedback_form.experience.value;
    var comments = document.feedback_form.comments.value;
    var name = document.feedback_form.name.value;
    var email = document.feedback_form.email.value;
    $.ajax({
        type: 'POST',
        url: "DemoServlat",
        data: {
            "experience": experience,
            "comments": comments,
            "name": name,
            "email": email
        },
        success: function (data)
        {
            $("#success_message").append('<div>'+data);
        }
    });
}
