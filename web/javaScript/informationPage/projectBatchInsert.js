/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function projectBatchInfoInsert()
{
    alert("hello");
    var batchname = document.projectBatchInfo.batchname.value;
    var datetime = document.projectBatchInfo.datetime.value;
    var cpn = document.projectBatchInfo.cpn.value;
    var number = document.projectBatchInfo.number.value;
    $.ajax({
        type: 'POST',
        url: "DemoServlat",
        data: {
            "batchname": batchname,
            "datetime": datetime,
            "cpn": cpn,
            "number": number
        },
        success: function (data)
        {
            alert(data);
//            $("#success_message").append('<div>' + data);
        }
    });
}

