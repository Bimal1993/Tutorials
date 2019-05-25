/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function uploadSlide()
{
    alert("hello");
    var slidename = document.upload_slide.slidename.value;
    var slide = document.upload_slide.slide.value;
    $.ajax({
        type: 'POST',
        url: "DemoServlat",
        data: {
            "slidename": slidename,
            "slide": slide
        },
        success: function (data)
        {
            alert(data);
//            $("#success_message").append('<div>' + data);
        }
    });
}


