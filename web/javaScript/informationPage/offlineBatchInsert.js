/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function offlineBatchInfoInsert()
{
    alert("hello");
    var batchname = document.offlineBatchInfo.batchname.value;
    var datetime = document.offlineBatchInfo.datetime.value;
    var cpn = document.offlineBatchInfo.cpn.value;
    var number = document.offlineBatchInfo.number.value;
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


