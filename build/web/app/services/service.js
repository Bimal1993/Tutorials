/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('TutorialModuleServices', [])
        .factory('AjaxCall', ['$http', function ($http)
            {
                /**
                 * To send the ajax get request. which will return the repose to the caller.
                 * 
                 * @param {type} url
                 * @param {type} queryParam
                 * @returns {unresolved}
                 */
                return {
                    get: function (url, queryParam)
                    {
                        return $http.get(url, {
                            params: queryParam,
                            method: 'GET',
                            dataType: "json"
                        });
                    },
                    post: function (url, postdata, headerparam)
                    {
                        return $http.post(url, {
                            data: postdata,
                            method: 'POST',
                            dataType: "json",
                            headers: headerparam
                        });
                    }
                };
            }])
        .factory('logger', ['$http', '$location', function ($http, $location) {
                var logIt;
                return toastr.options = {closeButton: !0, positionClass: "toast-top-right", timeOut: "1300"}, logIt = function (message, type) {
                    return toastr[type](message);
                }, {Info: function (message) {
                        if(message != undefined){
                            logIt(message, "info");
                        }
                    }, Warning: function (message) {
                        if(message != undefined){
                            logIt(message, "warning");
                        }
                    }, Success: function (message) {
                        if(message != undefined){
                            logIt(message, "success");
                        }
                    }, Error: function (message) {
                        if(message != undefined){
                            logIt(message, "error");
                        }
                    }};
            }]);

