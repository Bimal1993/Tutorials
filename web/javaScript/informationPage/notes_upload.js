/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function notesUpload()
{
    alert("hello");
    var notes_upload = document.uploadNotes.notes_upload.value;
    var notes = document.uploadNotes.notes.value;
    $.ajax({
        type: 'POST',
        url: "DemoServlat",
        data: {
            "notes_upload": notes_upload,
            "notes": notes
        },
        success: function (data)
        {
            alert(data);
//            $("#success_message").append('<div>' + data);
        }
    });
}

