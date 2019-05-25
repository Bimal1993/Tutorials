/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function videoUpload()
{
    alert("hello");
    var videoname = document.uploadvideo.videoname.value;
    var video = document.uploadvideo.video.value;
    $.ajax({
        type: 'POST',
        url: "DemoServlat",
        data: {
            "videoname": videoname,
            "video": video
        },
        success: function (data)
        {
            alert(data);
//            $("#success_message").append('<div>' + data);
        }
    });
}