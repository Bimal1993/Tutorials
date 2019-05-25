/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function onlineBatchInfoInsert()
{
    alert("hello");
    var batchname = document.onlineBatchInfo.batchname.value;
    var datetime = document.onlineBatchInfo.datetime.value;
    var cpn = document.onlineBatchInfo.cpn.value;
    var number = document.onlineBatchInfo.number.value;
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

